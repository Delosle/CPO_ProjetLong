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
}