package n7simulator.gestionBdd;
import java.io.*;
import java.sql.*;
import java.util.*;

    public class GestionBddSauvegarde {

import java.util.HashMap;
import java.util.Map;

        public class Main {
            public static void main(String[] args) {
                // Créer un nouveau HashMap
                Map<String, Integer> monDictionnaire = new HashMap<>();

                // Ajouter des éléments au HashMap
                monDictionnaire.put("un", 1);
                monDictionnaire.put("deux", 2);
                monDictionnaire.put("trois", 3);

                // Accéder à un élément du HashMap
                int valeur = monDictionnaire.get("deux"); // valeur est maintenant 2

                // Parcourir le HashMap
                for (Map.Entry<String, Integer> entry : monDictionnaire.entrySet()) {
                    System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
                }
            }
        }

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
        }

        public static Map recupererInfoBddSauvegarde(String nomBdd) {
            // Créez un HashMap pour stocker les informations
            Map<String, Object> infoBdd = new HashMap<>();

            // Spécifiez le chemin du fichier de base de données
            String cheminBdd = "src/main/resources/baseDeDonnee/" + nomBdd;

            // Créez un objet File pour le fichier de base de données
            File fichierBdd = new File(cheminBdd);

            // Vérifiez si le fichier existe
            if (fichierBdd.exists()) {
                // Obtenez la taille du fichier
                long taille = fichierBdd.length();

                // Obtenez la date de dernière modification du fichier
                long derniereModif = fichierBdd.lastModified();

                // Ajoutez les informations au HashMap
                infoBdd.put("Taille", taille);
                infoBdd.put("Dernière modification", derniereModif);
            } else {
                System.out.println("Le fichier de base de données n'existe pas");
            }

            // Renvoyez le HashMap contenant les informations
            return infoBdd;
        }


        /**
         * Effectue une connexion a la base de données admin
         * @return : la connexion
         * @throws SQLException : si la connexion echoue
         */
        private static Connection getDBConnexion(String nomBdd) throws SQLException {
            return DriverManager.getConnection("jdbc:sqlite:src/main/resources/baseDeDonnee/" + nomBdd + ".db");
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
}