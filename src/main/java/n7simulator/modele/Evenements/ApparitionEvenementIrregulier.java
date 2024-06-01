package n7simulator.modele.Evenements;

import java.sql.*;
import java.util.*;
import n7simulator.database.DatabaseConnection;
import n7simulator.modele.jauges.Jauge;

public class ApparitionEvenementIrregulier {

        private Map<String, Map<String, Object>> donneeEvenement; // contient les données de l'événement

        public ApparitionEvenementIrregulier() {
            donneeEvenement = new LinkedHashMap<>();
            recupDonneeEvenement();
        }

        private Map<String, Map<String, Object>> recupDonneeEvenement(){
            try {
                Connection conn = DatabaseConnection.getDBConnexion();
                Map<String, Map<String, Object>> infoTable = new LinkedHashMap<>();
                //int nbEvenement = DatabaseConnection.effectuerRequete("SELECT MAX(idPartie) as maxId FROM Partie;", conn).getInt("maxId");
                String query = "SELECT id_eve_irre, impactBonheur, impactArgent, impactPedagogie, frequence, bonus FROM evenement_irregulier";
                ResultSet result = DatabaseConnection.effectuerRequete(query, conn);
                while (result.next()) {
                    String idEveIrre = result.getString("id_eve_irre");

                    Map<String, Object> eventDetails = new HashMap<>();
                    eventDetails.put("impactBonheur", result.getObject("impactBonheur"));
                    eventDetails.put("impactArgent", result.getObject("impactArgent"));
                    eventDetails.put("impactPedagogie", result.getObject("impactPedagogie"));
                    eventDetails.put("frequence", result.getObject("frequence"));
                    eventDetails.put("bonus", result.getObject("bonus"));
                    donneeEvenement.put(idEveIrre, eventDetails);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la récupération des données " +
                        "de l'evenement irregulier dans la base de données.");
                e.printStackTrace();
            }
        }


        private Map<String, Object> recupValeurJauges(Jauge argent, Jauge bonheur, Jauge pedagogie){
            Map<String, Object> valeurJauges = new HashMap<>();
            valeurJauges.put("argent", argent.getValue());
            valeurJauges.put("bonheur", bonheur.getValue());
            valeurJauges.put("pedagogie", pedagogie.getValue());
            return valeurJauges;
        }

        public int calculApparitionEvenementIrregulier(Jauge argent, Jauge bonheur, Jauge pedagogie){
            Map<String, Object> valeurJauges = recupValeurJauges(argent, bonheur, pedagogie);
            for (Map.Entry<String, Map<String, Object>> entry : donneeEvenement.entrySet()) {
                Map<String, Object> eventDetails = entry.getValue();
                if (Math.random() < (double) eventDetails.get("frequence")) {
                    return Integer.parseInt(entry.getKey());
                }
            }
            return -1;
        }


}
