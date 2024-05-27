package n7simulator.gestionBdd;

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
			File dbFichier = new File("admin.db");
			if (dbFichier.exists()) {
				dbFichier.delete();
			}

			// Charger la classe de driver SQLite
			Class.forName("org.sqlite.JDBC");
			// Établir une connexion à la base de données nommée admin.bd
			connection = DriverManager.getConnection("jdbc:sqlite:admin.db");

			// Créer et peupler la base de données admin
			CreerPeuplerDatabase(connection);

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

	/**
	 * Lire le fichier SQL depuis /main/ressources et le renvoyer sous forme de String
	 * @param fileName
	 * @return contenu du .sql sous forme d'une grande chaine de caractère
	 */
	private static String readSqlFile(String fileName) {
		StringBuilder sqlString = new StringBuilder();
		// try avec ressources : permet de savoir que ces ressources seront fermées à la fin du try
		// InputStream : obtient le flux d'entrée
		// BufferedReader : permet de lire des caractères
		try (InputStream inputStream = CreationBddAdmin.class.getClassLoader().getResourceAsStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			// Lit chaque ligne du fichier sql jusqu'à ce qu'il n'y en ait plus
			while ((line = reader.readLine()) != null) {
				sqlString.append(line);
				sqlString.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlString.toString();
	}

	/**
	 * Permet de lire le .sql et de remplir notre bdd admin avec les requêtes
	 * @param connection
	 */
	private static void CreerPeuplerDatabase(Connection connection) {
		try {
			// Lire le fichier SQL
			String sql = readSqlFile("Creer_Tables.sql");

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
