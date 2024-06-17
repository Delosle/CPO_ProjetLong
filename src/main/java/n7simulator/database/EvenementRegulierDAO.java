package n7simulator.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import n7simulator.modele.evenements.EvenementRegulier;

/**
 * Classe permettant d'accéder aux données concernant les événements réguliers dans la
 * base de données
 */
public class EvenementRegulierDAO {

	/**
	 * Récupère la liste des événements réguliers dont les ids sont passés
	 * en paramètre
	 * 
	 * @return : la liste des événements
	 */
	public static List<EvenementRegulier> getEvenementsReguliersById(List<Integer> ids, LocalDate journeeEnCours) {
		List<EvenementRegulier> evenements = new ArrayList<>();
		
		if (!ids.isEmpty()) {
			Connection connexion = null;
			try {
	            connexion = DatabaseConnection.getDBConnexion();
	
	            /* Requête pour récupérer les événements en bd */
	            String query = "SELECT id_eve_reg, titre, description, impactBonheurPos, impactArgentPos, " +
	                    "impactPedagogiePos, impactBonheurNeg, impactArgentNeg, impactPedagogieNeg " +
	                    "FROM evenement_regulier " +
	                    "WHERE id_eve_reg IN (" + ids.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
	
	            ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexion);
	
	            // Lecture des résultats et création des événements
	            while (resultDB.next()) {
	            	int id = resultDB.getInt("id_eve_reg");
	                String titre = resultDB.getString("titre");
	                String description = resultDB.getString("description");
	                int impactBonheur = resultDB.getInt("impactBonheurPos");
	                int impactArgent = resultDB.getInt("impactArgentPos");
	                int impactPedagogie = resultDB.getInt("impactPedagogiePos");
	                int impactBonheurNeg = resultDB.getInt("impactBonheurNeg");
	                int impactArgentNeg = resultDB.getInt("impactArgentNeg");
	                int impactPedagogieNeg = resultDB.getInt("impactPedagogieNeg");
	                
	                // Création de l'événement et ajout dans la liste d'événements
	                EvenementRegulier evenement = new EvenementRegulier(id, journeeEnCours, titre,
	                		description, impactBonheur, impactArgent, impactPedagogie, impactBonheurNeg,
	                		impactArgentNeg, impactPedagogieNeg);
	                evenements.add(evenement);
	            }
	
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la récupération des données " +
	                    "de l'evenement irregulier dans la base de données.");
	            e.printStackTrace();
	
	        } finally {
	            try {
	                DatabaseConnection.closeDBConnexion(connexion);
	
	            } catch (Exception e) {
	                System.err.println("Erreur lors de la fermeture de la connexion");
	                e.printStackTrace();
	            }
	        }
		}
        return evenements;
	}
	
	/**
	 * Permet de récupérer les données de tous les événements réguliers.
	 * @return
	 */
	public static Map<Integer, Map<String, Object>> recupererDonneesEvenementsReguliers() {
		Map<Integer, Map<String, Object>> donnees = new LinkedHashMap<>();
		try {
            Connection conn = DatabaseConnection.getDBConnexion();
            String query = "SELECT id_eve_reg, periode, debut FROM evenement_regulier";
            ResultSet result = DatabaseConnection.effectuerRequete(query, conn);
            while (result.next()) {
                int idEveReg = result.getInt("id_eve_reg");
                Map<String, Object> eventDetails = new HashMap<>();
                eventDetails.put("periode", result.getObject("periode"));
                eventDetails.put("debut", result.getObject("debut"));
                donnees.put(idEveReg, eventDetails);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des données " +
                    "de l'evenement régulier dans la base de données.");
            e.printStackTrace();

        }
		return donnees;
	}
}