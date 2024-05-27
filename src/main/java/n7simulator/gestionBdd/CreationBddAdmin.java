package n7simulator.gestionBdd;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class CreationBddAdmin {

    public static void initialiserBddAdmin() {
        Connection connection = null;
        try {
        	// Supprimer le fichier de base de données existant
            File dbFile = new File("admin.db");
            if (dbFile.exists()) {
                dbFile.delete();
            }
        	
            // Charger la classe de driver SQLite
            Class.forName("org.sqlite.JDBC");
            // Établir une connexion à la base de données
            connection = DriverManager.getConnection("jdbc:sqlite:admin.db");
            System.out.println("Connexion réussie à la base de données SQLite!");

            // Créer et peupler la base de données
            createAndPopulateDatabase(connection);

            // Afficher le contenu de la base de données
            displayDatabaseContents(connection);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Méthode pour lire le fichier SQL depuis les ressources
    private static String readSqlFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = CreationBddAdmin.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // Méthode pour créer et peupler la base de données
    private static void createAndPopulateDatabase(Connection connection) {
        try {
            // Lire le fichier SQL
            String sql = readSqlFile("Creer_Tables.sql");

            // Exécuter les requêtes SQL
            Statement statement = connection.createStatement();
            String[] queries = sql.split(";");

            for (String query : queries) {
                if (query.trim().length() > 0) {
                    statement.execute(query.trim() + ";");
                }
            }

            System.out.println("Base de données créée et configurée avec succès!");

            // Fermer les ressources
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher le contenu de la base de données
    private static void displayDatabaseContents(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table';");

            while (rs.next()) {
                String tableName = rs.getString("name");
                System.out.println("Table: " + tableName);
                displayTableContents(connection, tableName);
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher le contenu d'une table spécifique
    private static void displayTableContents(Connection connection, String tableName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Afficher les en-têtes des colonnes
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Afficher les lignes de la table
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
