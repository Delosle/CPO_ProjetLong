package n7simulator.database;
import java.io.*;
import java.sql.*;
import java.util.*;


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

    /** Exécute une requête de mise à jour (INSERT, UPDATE, DELETE)
     * @param query : la requête SQL
     * @param connexion : la connexion à la base de données
     * @throws SQLException : si la requête échoue
     */
    private static void effectuerMiseAJour(String query, Connection connexion) throws SQLException {
        try (Statement statement = connexion.createStatement()) {
            statement.setQueryTimeout(30);
            statement.executeUpdate(query);
        }
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

    /**
     * Récupère les informations de la base de données SauvegardePartie
     * @param idPartie : l'identifiant de la partie
     * @return : les informations de la base de données
     */
    public static Map<String, Map<String, Object>> recupererInfoBddSauvegarde(int idPartie) {
        // Créez un HashMap pour stocker les informations
        Map<String, Map<String, Object>> infoBdd = new HashMap<>();
        try (Connection conn = getDBConnexion()){;
            List<String> tables = Arrays.asList("ProfEmbauches", "EvenementEnCours", "Partie");
            for (String table : tables) {
                String query = "SELECT * FROM " + table + " WHERE idPartie = " + idPartie;
                infoBdd.put(table, peuplerDico(conn, query));
            }
            closeDBConnexion(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // Renvoyez le HashMap contenant les informations
        return infoBdd;
    }

    /**
     * Peuple un dictionnaire avec les informations de la base de données
     * @param conn : la connexion à la base de données
     * @param query : la requête SQL
     * @return : les informations de la base de données
     */
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

    /**
     * Vérifie si le nom de la partie est disponible
     * @param nomPartie : le nom de la partie
     * @return : true si le nom de la partie est disponible, false sinon
     * @throws PartieExisteDejaException : si la partie existe déjà
     */
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

    /**
     * Récupère l'identifiant de la partie
     * @param nomPartie : le nom de la partie
     * @param conn : la connexion à la base de données
     * @return : l'identifiant de la partie
     */
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
        try {
            conn = getDBConnexion();
            Object[] idPartie = recupererIdPartie(nomPartie, conn);
            int maxidPartie = (int) idPartie[0];
            boolean existeDeja = (boolean) idPartie[1];

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
                        StringBuilder updateValues = new StringBuilder();
                        for (Map.Entry<String, Object> field : tableData.entrySet()) {
                            updateValues.append(field.getKey()).append(" = '").append(field.getValue()).append("', ");
                        }
                        updateValues.setLength(updateValues.length() - 2); // Supprime la dernière virgule et espace
                        query = "UPDATE " + tableName + " SET " + updateValues + " WHERE " + clePrimaire + " = " + maxidPartie + ";";
                    } else {
                        query = "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + values.toString() + ")";;
                    }
                    effectuerMiseAJour(query, conn);
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