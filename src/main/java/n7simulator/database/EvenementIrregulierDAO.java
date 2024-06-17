package n7simulator.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import n7simulator.modele.evenements.EvenementIrregulier;


/**
 * Classe permettant d'accéder aux données concernant les événements irréguliers dans la
 * base de données
 */
public class EvenementIrregulierDAO {

	/**
	 * Récupère la liste des événements réguliers dont les ids sont passés
	 * en paramètre
	 * 
	 * @return : la liste des événements
	 */
	public static List<EvenementIrregulier> getEvenementsReguliersById(List<Integer> ids, LocalDate journeeEnCours) {
		List<EvenementIrregulier> evenements = new ArrayList<>();

		if (!ids.isEmpty()) {
			Connection connexion = null;
			try {
				connexion = DatabaseConnection.getDBConnexion();

				/* Requête pour récupérer les événements en bd */
				String query = "SELECT id_eve_irre, titre, description, impactBonheur, impactArgent, " +
	                    "impactPedagogie, bonus " +
	                    "FROM evenement_irregulier " +
						"WHERE id_eve_irre IN (" + ids.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";

				ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexion);

				// Lecture des résultats et création des événements
				while (resultDB.next()) {
					int id = resultDB.getInt("id_eve_irre");
					String titre = resultDB.getString("Titre");
					String description = resultDB.getString("description");
					int impactBonheur = resultDB.getInt("impactBonheur");
					int impactArgent = resultDB.getInt("impactArgent");
					int impactPedagogie = resultDB.getInt("impactPedagogie");
					boolean bonus = resultDB.getBoolean("bonus");

					// Création de l'événement et ajout dans la liste d'événements
					EvenementIrregulier evenement = new EvenementIrregulier(id, journeeEnCours, titre,
							description, impactBonheur, impactArgent, impactPedagogie, bonus);
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
	 * Permet de récupérer les données de tous les événements irréguliers.
	 * @return
	 */
	public static Map<Integer, Map<String, Object>> recupererDonneesEvenementsIrreguliers() {
		Map<Integer, Map<String, Object>> donnees = new LinkedHashMap<>();
		try {
            Connection conn = DatabaseConnection.getDBConnexion();
            String query = "SELECT id_eve_irre, impactBonheur, impactPedagogie, frequence, bonus"
            		     + " FROM evenement_irregulier";
            ResultSet result = DatabaseConnection.effectuerRequete(query, conn);
            while (result.next()) {
                int idEveIrre = result.getInt("id_eve_irre");
                Map<String, Object> eventDetails = new HashMap<>();
                List<String> liste = Arrays.asList("impactBonheur", "impactPedagogie");
                for (String key : liste) {
                    int value = (int) result.getObject(key);
                    if (value > 0) {
                        eventDetails.put(key, 1);
                    } else if (value < 0) {
                        eventDetails.put(key, -1);
                    } else if (value == 0   ) {
                        eventDetails.put(key, 0);
                    }
                }
                eventDetails.put("frequence", result.getObject("frequence"));
                eventDetails.put("bonus", result.getObject("bonus"));
                donnees.put(idEveIrre, eventDetails);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des données " +
                    "de l'evenement irregulier dans la base de données.");
            e.printStackTrace();
        }
		return donnees;
	}
}