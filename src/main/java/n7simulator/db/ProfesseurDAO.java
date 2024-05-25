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

public class ProfesseurDAO {
	
	public static List<Professeur> getAllProfesseurs() {
		Map<Integer, Matiere> matieres = getAllMatieres();
		List<Professeur> professeurs = new ArrayList<>();
		Connection connexionDB = null;
		try {
			connexionDB = DatabaseConnection.getDBConnexion();

            String query = "SELECT * FROM prof";
            ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);

            while (resultDB.next()) {
            	int idProf = resultDB.getInt("id_prof");
                String nom = resultDB.getString("nom");
                String prenom = resultDB.getString("prenom");
                String niveau = resultDB.getString("niveau");
                int tauxHoraireMin = resultDB.getInt("taux_horaire_minute");
                int idMatiere = resultDB.getInt("id_matiere");

                Matiere matiere = matieres.get(idMatiere);
                Professeur prof = new Professeur(idProf, nom, prenom, 0, matiere, niveau, tauxHoraireMin);

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
	
	private static Map<Integer, Matiere> getAllMatieres() {
		Map<Integer, Matiere> matieres = new HashMap<Integer, Matiere>();
		Connection connexionDB = null;
		try {
			connexionDB = DatabaseConnection.getDBConnexion();
			String query = "SELECT * FROM matiere";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
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
