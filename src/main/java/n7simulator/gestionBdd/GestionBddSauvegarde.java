package n7simulator.gestionBdd;
import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


    public class GestionBddSauvegarde {

        /**
         * Récupère les noms des fichiers de base de données de sauvegarde
         * @return : la liste des noms des fichiers

        public static List<String> recupererNomsBddSauvegarde() {
            // Spécifiez le chemin du répertoire
            File repertoire = new File("src/main/resources/baseDeDonnee");

            // Créez un filtre pour ne sélectionner que les fichiers commençant par "SauvegardePartie_"
            FilenameFilter filtre = (dir, name) -> name.startsWith("SauvegardePartie_");

            // Obtenez tous les fichiers du répertoire qui satisfont le filtre
            File[] fichiers = repertoire.listFiles(filtre);

            // Créez une liste pour stocker les noms des fichiers
            List<String> listeFichier = new ArrayList<>();

            // Vérifiez si le répertoire est vide
            if (fichiers != null && fichiers.length > 0) {
                // Parcourez tous les fichiers
                for (File fichier : fichiers) {
                    // Ajoutez le nom du fichier à la liste
                    listeFichier.add(fichier.getName());
                }
            } else {
                System.out.println("Le répertoire est vide ou le chemin n'est pas un répertoire");
            }

            // Renvoyez la liste des noms de fichiers
            return listeFichier;
        }*/


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



        public static Map recupererInfoBddSauvegarde(int idPartie) {
            // Créez un HashMap pour stocker les informations
            Map<String, Object> infoBdd = new HashMap<>();
            try {
                Connection conn = getDBConnexion();
                String requeteTable1 = "SELECT * FROM ProfEmbauches WHERE idPartieNom = " + idPartie;
                infoBdd = peuplerDico (conn, requeteTable1, infoBdd);
                String requeteTable2 = "SELECT * FROM EvenementEnCours WHERE idPartieNom = " + idPartie;
                infoBdd = peuplerDico (conn, requeteTable2, infoBdd);
                String requeteTable3 = "SELECT * FROM Partie WHERE idPartieNom = " + idPartie;
                infoBdd = peuplerDico (conn, requeteTable3, infoBdd);
                closeDBConnexion(conn);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            // Renvoyez le HashMap contenant les informations
            return infoBdd;
        }


        private static Map peuplerDico (Connection conn, String query, Map infoBdd) {
            try {
                ResultSet result = effectuerRequete(query, conn);
                System.out.println( result.getMetaData().getColumnCount());
                if (result.next()) {
                    int columnCount = result.getMetaData().getColumnCount();
                    System.out.println(columnCount);
                    for (int i = 1; i <= columnCount; i++) {

                        String columnName = result.getMetaData().getColumnName(i);
                        Object value = result.getObject(i);
                        infoBdd.put(columnName, value);
                    }
                }
                result.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return infoBdd;
        }

        /**
         * Récupère les noms des parties sauvegardées
         * @return : la liste des noms des parties sauvegardées
         * @throws SQLException : si la requête échoue
         */
        public static ArrayList<String> recupererNomPartie() {
            String query = "SELECT idPartieNom, nomPartie FROM SauvegardePartie";
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
}