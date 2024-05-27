package n7simulator.gestionBdd;
import java.sql.*;
import java.util.*;


public class CreerBddSauvegarde {

    public static void creerBddSauvegarde (String nomDePartie) throws PartieExisteDejaException {
        verifierPartieExiste(nomDePartie);
        Connection conn = creerBDD(nomDePartie);
        creerTables(conn);
        fermerDatabase(conn);
    }

    private static void verifierPartieExiste(String nomDePartie) throws PartieExisteDejaException {
        List<String> fichiersNom = GestionBddSauvegarde.recupererNomsBddSauvegarde();
        String nomBdd = "SauvegardePartie_" + nomDePartie + ".db";
        System.out.println(nomBdd);
        for (String fichierNom : fichiersNom) {
            System.out.println(fichierNom);
            if (nomBdd.equals(fichierNom)) {
                //throw new IllegalArgumentException("La partie existe déjà");
                throw new PartieExisteDejaException("La partie " + nomDePartie + " existe déjà");
            }
        }
    }

    private static Connection creerBDD(String nomDePartie) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/baseDeDonnee/SauvegardePartie_" + nomDePartie + ".db";
            // create a connection to the database src/main/resources/baseDeDonnee
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void creerTables(Connection conn) {
        Statement stmt = null;
        try {
            // Créer un objet Statement pour exécuter les requêtes SQL
            stmt = conn.createStatement();

            // Commande SQL pour créer la table ProfEmbauches
            String sqlProfEmbauches = "CREATE TABLE ProfEmbauches " +
                    "(idprof INTEGER PRIMARY KEY, salaire INTEGER, nbheure INTEGER)";

            // Exécuter la commande SQL
            stmt.execute(sqlProfEmbauches);

            // Commande SQL pour créer la table EvenementEnCours
            String sqlEvenementEnCours = " CREATE TABLE EvenementEnCours (" +
                    "idEvenement INTEGER PRIMARY KEY, jourDebut DATE)";

            // Exécuter la commande SQL
            stmt.execute(sqlEvenementEnCours);

            // Commande SQL pour créer la table partie
            String sqlpartie = " CREATE TABLE Partie (\n" +
                    "    id INTEGER PRIMARY KEY,\n" +
                    "    nomPartie TEXT,\n" +
                    "    estPerdue BOOLEAN,\n" +
                    "    nbJours INT,\n" +
                    "    nbEleves INT,\n" +
                    "    argent FLOAT,\n" +
                    "    bonheur INT,\n" +
                    "    pedagogie INT,\n" +
                    "    idQualiteRepasCrous INT,\n" +
                    "    prixVenteRepascrous FLOAT\n" +
                    ");\n";

            // Exécuter la commande SQL
            stmt.execute(sqlpartie);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void fermerDatabase(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}