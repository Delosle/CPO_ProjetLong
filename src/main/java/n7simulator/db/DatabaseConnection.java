package n7simulator.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe permettant d'effectuer des requêtes à la base de données
 */
public class DatabaseConnection {
	// emplacement du fichier database admin
    private static final String DB_URL = "jdbc:sqlite:src/main/java/my_database.db";

    private DatabaseConnection() {}
    
    /**
     * Effectue une connexion a la base de données admin
     * @return : la connexion
     * @throws SQLException : si la connexion echoue
     */
    public static Connection getDBConnexion() throws SQLException {
    	return DriverManager.getConnection(DB_URL);
    }

    /**
     * Effectue une requete sur la connexion à la bd
     * @param query : la requête SQL
     * @param connexion : la connexion a la base de données
     * @return : le résultat de la requête
     * @throws SQLException : si la requête échoue
     */
    public static ResultSet effectuerRequete(String query, Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        statement.setQueryTimeout(30); 
        return statement.executeQuery(query);
    }
    
    /**
     * Permet de fermer une connexion à la base de données
     * @param connexion : la connexion qui doit être fermée
     * @throws SQLException : si la fermeture échoue
     */
    public static void closeDBConnexion(Connection connexion) throws SQLException {
    	if (connexion != null) {
    		connexion.close();
        }
    }
    
    
}

