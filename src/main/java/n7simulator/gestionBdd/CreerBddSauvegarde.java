package n7simulator.gestionBdd;
import java.sql.*;
import java.util.*;


public class CreerBddSauvegarde {

    /**
     * Crée une base de données de sauvegarde pour une partie
      * @param nomDePartie
     * @throws PartieExisteDejaException
     */
    public static void creerBddSauvegarde (String nomDePartie) {
        //verifierPartieExiste(nomDePartie);
        Connection conn = creerBDD();
        creerTables(conn);
        fermerDatabase(conn);
    }

    /**
     * Vérifie si une partie existe déjà
     * @param nomDePartie
     * @throws PartieExisteDejaException

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
    }**/

    /**
     * Crée une connexion à la base de données
     * @param nomDePartie
     * @return
     */
    private static Connection creerBDD() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/baseDeDonnee/SauvegardePartie.db";
            //String url = "jdbc:sqlite:src/main/resources/baseDeDonnee/SauvegardePartie_" + nomDePartie + ".db";
            // create a connection to the database src/main/resources/baseDeDonnee
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * Crée les tables de la base de données
     * @param conn
     */
    private static void creerTables(Connection conn) {
        Statement stmt = null;
        try {
            // Créer un objet Statement pour exécuter les requêtes SQL
            stmt = conn.createStatement();

            // Commande SQL pour créer la table de sauvegarde des parties
            String sqlSauvegardePartie = " CREATE TABLE SauvegardePartie (\n" +
                    "    idPartieNom INTEGER PRIMARY KEY,\n" +
                    "    nomPartie TEXT)";

            // Exécuter la commande SQL
            stmt.execute(sqlSauvegardePartie);

            // Commande SQL pour créer la table ProfEmbauches
            String sqlProfEmbauches = "CREATE TABLE ProfEmbauches " +
                    "(idprof INTEGER PRIMARY KEY, salaire INTEGER, nbheure INTEGER, idPartieNom INTEGER REFERENCES Partie(idPartieNom));";

            // Exécuter la commande SQL
            stmt.execute(sqlProfEmbauches);

            // Commande SQL pour créer la table EvenementEnCours
            String sqlEvenementEnCours = " CREATE TABLE EvenementEnCours (" +
                    "idEvenement INTEGER PRIMARY KEY, jourDebut DATE, idPartieNom INTEGER REFERENCES Partie(idPartieNom));";

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
                    "    prixVenteRepascrous FLOAT,\n" +
                    "idPartieNom INTEGER REFERENCES Partie(idPartieNom));\n";

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

    /**
     * Ferme la connexion à la base de données
     * @param conn
     */
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