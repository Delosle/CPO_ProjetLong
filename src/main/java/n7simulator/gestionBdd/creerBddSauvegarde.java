import java.sql.*;

public class Creer_bdd_sauvegarde {

    public static void main(String[] args) {
        String nomDePartie = args[0];
        Connection conn = null;
        ///String nomDePartie = partie_;
        conn = creerBDD(nomDePartie);
        creerTables(conn);
        fermerDatabase(conn);
    }

    private static Connection creerBDD(String nomDePartie) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:src/main/resources/baseDeDonnee/sauvegarde_partie_" + nomDePartie + ".db";
            // create a connection to the database
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