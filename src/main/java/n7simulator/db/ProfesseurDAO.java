package n7simulator.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import n7simulator.modele.professeur.Matiere;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe permettant d'accéder aux données concernant les professeurs dans la
 * base de données
 */
public class ProfesseurDAO {

	/**
	 * Récupère la liste de tous les professeurs présents en base de données
	 * 
	 * @return : la liste des professeurs
	 */
	public static List<Professeur> getAllProfesseurs() {
		// récupération de toutes les matières des profs
		Map<Integer, Matiere> matieres = getAllMatieres();
		List<Professeur> professeurs = new ArrayList<>();
		Connection connexionDB = null;
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();

			// requête à la base de données
			String query = "SELECT * FROM prof";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);

			// parcours du résultat pour instancier les objets profs
			while (resultDB.next()) {
				int idProf = resultDB.getInt("id_prof");
				String nom = resultDB.getString("nom");
				String prenom = resultDB.getString("prenom");
				String niveau = resultDB.getString("niveau");
				String description = resultDB.getString("description");
				int tauxHoraireMin = resultDB.getInt("taux_horaire_min");
				int idMatiere = resultDB.getInt("id_matiere");

				Matiere matiere = matieres.get(idMatiere);
				Professeur prof = new Professeur(idProf, nom, prenom, description, matiere, niveau, tauxHoraireMin);

				professeurs.add(prof);
			}

		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération des professeurs dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		return professeurs;

	}

	/**
	 * Récupère les matières des professeurs dans la base de données
	 * 
	 * @return : les matières associées à leur id
	 */
	private static Map<Integer, Matiere> getAllMatieres() {
		Map<Integer, Matiere> matieres = new HashMap<Integer, Matiere>();
		Connection connexionDB = null;
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();

			// requête à la base de données
			String query = "SELECT * FROM matiere";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);

			// parcours des résultats afin d'instancier les objets
			while (resultDB.next()) {
				int idMatiere = resultDB.getInt("id_matiere");
				String nom = resultDB.getString("nom");
				int bonheur = resultDB.getInt("bonheur");
				int pedagogie = resultDB.getInt("pedagogie");

				Matiere matiere = new Matiere(idMatiere, nom, bonheur, pedagogie);
				matieres.put(idMatiere, matiere);
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération des professeurs dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		return matieres;
	}

}
