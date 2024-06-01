package n7simulator.database;

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
			// La bdd ne doit pas être modifié, il est donc préférable de supprimer
			// le fichier de base de données s'il existe
			File dbFichier = new File("src/main/resources/baseDeDonnee/admin.db");
			if (dbFichier.exists()) {
				dbFichier.delete();
			}

			// Charger la classe de driver SQLite
			Class.forName("org.sqlite.JDBC");
			// Établir une connexion à la base de données nommée admin.bd
			connection = CreationBddUtilitaire.creerBDD("src/main/resources/baseDeDonnee/admin.db");
			// Créer et peupler la base de données admin
			CreationBddUtilitaire.creerPeuplerDatabase(connection, "src/main/resources/Creer_Tables.sql");
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
	}
}
