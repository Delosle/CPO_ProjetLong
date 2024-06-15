package n7simulator.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import n7simulator.modele.Partie;
import n7simulator.modele.Temps;

/**
 * Classe permettant d'accéder aux données concernant les valeurs de début de
 * partie dans la base de données
 */
public class ValDebPartieDAO {
	
	private ValDebPartieDAO() {}

	public static void initialiserPartieSauvegardee() {
		initialiserDonneesDebutPartie();
		Connection connexionDB = null;

		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
		} catch (SQLException e) {
			System.err
					.println("Erreur lors de la récupération des données de début de partie dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Récupère les valeurs en base de données et set les données de la partie.
	 */
	public static void initialiserDonneesDebutPartie() {

		Partie partie = Partie.getInstance();
		Temps temps = partie.getTemps();
		Connection connexionDB = null;

		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();

			// requête à la base de données
			String query = "SELECT * FROM ValDebPartie";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);

			// parcours du résultat pour instancier la partie
			while (resultDB.next()) {
				partie.getGestionEleves().inscrireEleves(resultDB.getInt("NbEleve"));
				partie.getJaugeArgent().ajouter(resultDB.getInt("Argent"));
				partie.getJaugeBonheur().ajouter(resultDB.getInt("Bonheur"));
				partie.getJaugePedagogie().ajouter(resultDB.getInt("Pedagogie"));

				// transformation de la date
				String dateString = resultDB.getString("dateDeb");
				temps.setJourneeEnCours(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}

		} catch (SQLException e) {
			System.err
					.println("Erreur lors de la récupération des données de début de partie dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
	}

}