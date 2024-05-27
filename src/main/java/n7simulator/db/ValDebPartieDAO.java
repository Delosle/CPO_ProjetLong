package n7simulator.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import n7simulator.modele.Partie;

/**
 * Classe permettant d'accéder aux données concernant les valeurs de début de partie
 * dans la base de données
 */
public class ValDebPartieDAO {

	/**
	 * Récupère les valeurs en base de données et set les données de la partie.
	 */
	public static void initialiserDonneesDebutPartie() {
		
		Partie partie = Partie.getInstance();		
		Connection connexionDB = null;
		
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();

			// requête à la base de données
			String query = "SELECT * FROM ValDebPartie";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);

			// parcours du résultat pour instancier la partie
			while (resultDB.next()) {
				partie.inscrireEleves(resultDB.getInt("NbEleve"));
				// TODO ajouter les autres champs
			}

		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération des données de début de partie dans la base de données.");
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