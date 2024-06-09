package n7simulator.database;

import java.io.File;
import java.sql.Connection;

/*
 * Classe créant la base de donnée initiale du jeu.
 */
public class CreationBddAdmin {
	// variable donnant les chemins pour la maintenabilité
	private static final String cheminBDD = "src/main/resources/baseDeDonnee/admin.db";
	private static final String nomSQL = "Creer_Tables.sql";

	/**
	 * initialise la bdd admin
	 * @return un boolean indiquant si la bdd c'est créée correctement
	 */
	public static boolean initialiserBddAdmin() {
		Connection connection = null;
		try {
			// La bdd ne doit pas être modifié, il est donc préférable de supprimer
			// le fichier de base de données s'il existe
			File dbFichier = new File(cheminBDD);
			if (dbFichier.exists()) {
				dbFichier.delete();
			}

			// Charger la classe de driver SQLite
			Class.forName("org.sqlite.JDBC");
			// Établir une connexion à la base de données nommée admin.bd
			connection = CreationBddUtilitaire.creerBDD(cheminBDD);
			// Créer et peupler la base de données admin
			CreationBddUtilitaire.creerPeuplerDatabase(connection, nomSQL);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally { // pour être sûr de fermer les ressources
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
