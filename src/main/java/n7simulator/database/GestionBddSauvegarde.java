package n7simulator.database;
import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class GestionBddSauvegarde {

    /**
     * Effectue une connexion a la base de données admin
     * @return : la connexion
     * @throws SQLException : si la connexion echoue
     */
    private static Connection getDBConnexion() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/baseDeDonnee/SauvegardePartie.db");
    }

    /**
     * Effectue une requete sur la connexion à la bd
     * @param query : la requête SQL
     * @param connexion : la connexion a la base de données
     * @return : le résultat de la requête
     * @throws SQLException : si la requête échoue
     */
    private static ResultSet effectuerRequete(String query, Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        statement.setQueryTimeout(30);
        return statement.executeQuery(query);
    }

    /**
     * Permet de fermer une connexion à la base de données
     * @param connexion : la connexion qui doit être fermée
     * @throws SQLException : si la fermeture échoue
     */
    private static void closeDBConnexion(Connection connexion) throws SQLException {
        if (connexion != null) {
            connexion.close();
        }
    }

    public static Map<String, Map<String, Object>> recupererInfoBddSauvegarde(int idPartie) {
        // Créez un HashMap pour stocker les informations
        Map<String, Map<String, Object>> infoBdd = new HashMap<>();
        try {
            Connection conn = getDBConnexion();
            // Récupération des données de la table ProfEmbauches
            String requeteTable1 = "SELECT * FROM ProfEmbauches WHERE idPartie = " + idPartie;
            Map<String, Object> tableProfEmbauches = peuplerDico(conn, requeteTable1);
            infoBdd.put("ProfEmbauches", tableProfEmbauches);

            // Récupération des données de la table EvenementEnCours
            String requeteTable2 = "SELECT * FROM EvenementEnCours WHERE idPartie = " + idPartie;
            Map<String, Object> tableEvenementEnCours = peuplerDico(conn, requeteTable2);
            infoBdd.put("EvenementEnCours", tableEvenementEnCours);

            // Récupération des données de la table Partie
            String requeteTable3 = "SELECT * FROM Partie WHERE idPartie = " + idPartie;
            Map<String, Object> tablePartie = peuplerDico(conn, requeteTable3);
            infoBdd.put("Partie", tablePartie);
            closeDBConnexion(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // Renvoyez le HashMap contenant les informations
        return infoBdd;
    }


    private static Map peuplerDico (Connection conn, String query) {
        Map<String, Object> infoTable = new HashMap<>();
        try {
            ResultSet result = effectuerRequete(query, conn);
            if (result.next()) {
                int columnCount = result.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = result.getMetaData().getColumnName(i);
                    Object value = result.getObject(i);
                    infoTable.put(columnName, value);
                }
            }
            result.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return infoTable;
    }

    /**
     * Récupère les noms des parties sauvegardées
     * @return : la liste des noms des parties sauvegardées
     * @throws SQLException : si la requête échoue
     */
    public static ArrayList<String> recupererNomPartie() {
        String query = "SELECT nomPartie FROM Partie";
        ArrayList<String> nomPartie = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = getDBConnexion ();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (ResultSet result = effectuerRequete(query, conn)) {
            while (result.next()) {
                nomPartie.add(result.getString("nomPartie"));
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            closeDBConnexion(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nomPartie;
    }

    public static boolean nomPartieDisponible(String nomPartie) throws PartieExisteDejaException {
        ArrayList<String> listNomPartie = new ArrayList<String>();
        listNomPartie = recupererNomPartie();
        for (String nom : listNomPartie) {
            if (nom.equals(nomPartie)) {
                throw new PartieExisteDejaException("La partie " + nomPartie + " existe déjà");
            }
        }
        return true;
    }

    private  static Object[] recupererIdPartie(String nomPartie, Connection conn) {
        int maxidPartie = 0;
        boolean existeDeja = false;
        try {
            if (nomPartieDisponible(nomPartie)) {
                maxidPartie = effectuerRequete("SELECT MAX(idPartie) as maxId FROM Partie;", conn).getInt("maxId");
                maxidPartie++;
            }
        } catch (PartieExisteDejaException e) {
            try {
                maxidPartie = effectuerRequete("SELECT idPartie FROM Partie WHERE nomPartie ='" + nomPartie + "';", conn).getInt("idPartie");
                existeDeja = true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[]{maxidPartie, existeDeja};
    }

    /**
     *  Sauvegarde les données de la partie
     *  Mettre la clé primaire de la table en premier dans la map
     *  correspond à chaque table
     *  Exemple :
     * @param data
     * @param nomPartie
     */
    public static void sauvegarderDonnee(Map<String, Map<String, Object>> data, String nomPartie) {
        Connection conn = null;
        int maxidPartie = 0;
        boolean existeDeja = false;
        try {
            conn = getDBConnexion();
            Object[] idPartie = recupererIdPartie(nomPartie, conn);
            maxidPartie = (int) idPartie[0];
            existeDeja = (boolean) idPartie[1];
            try { //essayer de se connecter à la base de données
                for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {  //pour chaque table
                    String tableName = entry.getKey(); //recupérer le nom de la table
                    Map<String, Object> tableData = entry.getValue(); //recupérer les données de la table
                    StringBuilder columns = new StringBuilder();
                    StringBuilder values = new StringBuilder();
                    boolean isFirst = true;
                    String clePrimaire =""; //cle primaire de la table
                    for (Map.Entry<String, Object> field : tableData.entrySet()) {
                        columns.append(field.getKey()).append(", ");
                        if (isFirst) {
                            values.append(maxidPartie).append(", ");
                            isFirst = false;
                            clePrimaire = field.getKey();
                        } else {
                            values.append("'").append(field.getValue()).append("', ");
                        }
                    }
                    columns.delete(columns.length() - 2, columns.length());
                    values.delete(values.length() - 2, values.length());
                    String query;
                    if (existeDeja) {
                        String value = "";
                        for (Map.Entry<String, Object> field : tableData.entrySet()) {
                            value = value + field.getKey() + " = '" + field.getValue() + "',";
                        }
                        value = value.substring(0, value.length() - 1);
                        query = "UPDATE " + tableName + " SET " + value + " WHERE " + clePrimaire + " = " + maxidPartie + ";";
                    } else {
                        query = "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + values.toString() + ")";;
                    }
                    Statement statement = conn.createStatement();
                    statement.executeUpdate(query);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}