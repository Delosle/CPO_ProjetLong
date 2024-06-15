package n7simulator.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import n7simulator.modele.Partie;

/**
 * Classe permettant d'accéder aux données concernant le crous dans la
 * base de données
 */
public class CrousDAO {
	
	/**
	 * Obtenir le prix de vente en fonction d'une qualite choisie
	 * @param idQualite : l'id de la qualite
	 * @return : le prix de vente
	 */
	public static double getPrixVente(int idQualite) {
		double result = 0;
		Connection connexionDB = null;
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
			
			// requête à la base de données
			String query = "SELECT prix FROM RepasCrous WHERE id_repas = " + idQualite;
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
			
			// résultat
			result = resultDB.getDouble("prix");
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération du prix d'achat des repas dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Obtenir la liste des qualites des repas crous et de leur prix associé
	 * en base de données
	 * @return : un dictionnaire associant idQualite et prix
	 */
	public static HashMap<Integer, Double> getListeQualitesPrix() {
		Connection connexionDB = null;
		HashMap<Integer, Double> mapQualitePrix = new HashMap<>();
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
			// requête à la base de données
			String query = "SELECT * FROM RepasCrous";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
			while (resultDB.next()) {
				double prix = resultDB.getDouble("prix");
				int indexQualite = resultDB.getInt("qualite");
				mapQualitePrix.put(indexQualite, prix);
			}
			
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération des repas dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		return mapQualitePrix;
	}

}
