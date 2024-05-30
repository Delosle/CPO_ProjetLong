package n7simulator.database;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;


public class CreerBddSauvegarde {


    /**
     * Permet d'initialiser la base de données SauvegardePartie
     */
    public static void initialiserBddSauvegarde() {
        Connection connection = null;
        try {
            // La bdd ne doit pas être modifié, il est donc préférable de supprimer
            // le fichier de base de données s'il existe
            File dbFichier = new File("src/main/resources/baseDeDonnee/SauvegardePartie.db");
            if (!dbFichier.exists()) {
                // Charger la classe de driver SQLite
                Connection conn = CreationBddUtilitaire.creerBDD("src/main/resources/baseDeDonnee/SauvegardePartie.db");
                // Créer et peupler la base de données admin
                try {
                    CreationBddUtilitaire.creerPeuplerDatabase(conn, "Creer_Tables_Sauvegarde.sql");
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean verifierCreationTables (){
        Connection connection = null;
        boolean tableCreer = false;
        try {
            // Charger la classe de driver SQLite
            Class.forName("org.sqlite.JDBC");
            // Établir une connexion à la base de données nommée admin.bd
            connection = CreationBddUtilitaire.creerBDD("src/main/resources/baseDeDonnee/admin.db");
            // Créer et peupler la base de données admin
            List<String> nomTable = new ArrayList<>();
            nomTable.add("Partie");
            nomTable.add("EvenementEnCours");
            nomTable.add("ProfEmbauches");
            try {
                for (String table : nomTable) {
                    ResultSet result = null;
                    result = DatabaseConnection.effectuerRequete("SELECT name FROM sqlite_master WHERE type='table' AND name='" + table + "';", connection);
                    if (result == null) {
                        tableCreer = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // pour être sûr de fermer les ressources
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return tableCreer;
    }
}