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

    public static String readSqlFile(String fileName) {
        StringBuilder sqlString = new StringBuilder();
        System.out.println("Loading SQL file: " + fileName);
        try (InputStream inputStream = CreationBddUtilitaire.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sqlString.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlString.toString();
    }

    public static void creerPeuplerDatabase(Connection connection, String sqlFileName) {
        try {
            String sql = readSqlFile(sqlFileName);
            Statement statement = connection.createStatement();
            String[] requetes = sql.split(";");
            for (String requete : requetes) {
                if (requete.trim().length() > 0) {
                    statement.execute(requete.trim() + ";");
                }
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
