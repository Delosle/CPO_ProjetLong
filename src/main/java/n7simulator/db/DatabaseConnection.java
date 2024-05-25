package n7simulator.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/my_database.db";
    
    public static Connection getDBConnexion() throws SQLException {
    	return DriverManager.getConnection(DB_URL);
    }

    public static ResultSet effectuerRequete(String query, Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        statement.setQueryTimeout(30); 
        return statement.executeQuery(query);
    }
    
    public static void closeDBConnexion(Connection connexion) throws SQLException {
    	if (connexion != null) {
    		connexion.close();
        }
    }
    
    
}

