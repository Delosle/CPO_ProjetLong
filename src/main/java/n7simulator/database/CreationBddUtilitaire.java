package n7simulator.database;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreationBddUtilitaire {

    public static Connection creerBDD(String dbPath) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Lire le fichier SQL depuis /main/ressources et le renvoyer sous forme de String
     * @param fileName
     * @return contenu du .sql sous forme d'une grande chaine de caractère
     */
    public static String readSqlFile(String fileName) throws Exception {
        StringBuilder sqlString = new StringBuilder();
        // try avec ressources : permet de savoir que ces ressources seront fermées à la fin du try
        // InputStream : obtient le flux d'entrée
        // BufferedReader : permet de lire des caractères
        InputStream inputStream = CreationBddUtilitaire.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        // Lit chaque ligne du fichier sql jusqu'à ce qu'il n'y en ait plus
        while ((line = reader.readLine()) != null) {
            sqlString.append(line).append("\n");
        }
        return sqlString.toString();
    }

    /**
     * Permet de lire le .sql et de remplir notre bdd admin avec les requêtes
     * @param connection
     */
    public static void creerPeuplerDatabase(Connection connection, String sqlFileName) throws Exception {
        // Lire le fichier SQL
        String sql = readSqlFile(sqlFileName);

        // Exécuter les requêtes SQL
        Statement statement = connection.createStatement();

        // tableau où chaque case est une requête
        String[] requetes = sql.split(";");

        // parcours les requêtes et les exécutes
        for (String requete : requetes) {
            if (requete.trim().length() > 0) {
                statement.execute(requete.trim() + ";");
            }
        }
        // Ferme les ressources
        statement.close();
    }
}
