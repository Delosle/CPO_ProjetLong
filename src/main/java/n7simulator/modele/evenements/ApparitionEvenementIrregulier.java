package n7simulator.modele.evenements;

import java.sql.*;
import java.util.*;
import n7simulator.database.DatabaseConnection;
import n7simulator.modele.jauges.Jauge;

public class ApparitionEvenementIrregulier {

    /**
     * Contient les données de l'événement
     */
    private Map<Integer, Map<String, Object>> donneeEvenement; // contient les données de l'événement

    /**
     * Constructeur de la classe ApparitionEvenementIrregulier
     */
    public ApparitionEvenementIrregulier() {
        donneeEvenement = new LinkedHashMap<>();
        recupDonneeEvenement();
    }

    /**
     * Récupère les données de l'événement dans la base de données
     */
    private void recupDonneeEvenement(){
        try {
            Connection conn = DatabaseConnection.getDBConnexion();
            //int nbEvenement = DatabaseConnection.effectuerRequete("SELECT MAX(idPartie) as maxId FROM Partie;", conn).getInt("maxId");
            String query = "SELECT id_eve_irre, impactBonheur, impactPedagogie, frequence, bonus FROM evenement_irregulier";
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
                donneeEvenement.put(idEveIrre, eventDetails);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des données " +
                    "de l'evenement irregulier dans la base de données.");
            e.printStackTrace();
        }
    }

    /**
     * Récupère les valeurs des jauges
     * @param bonheur la jauge de bonheur
     * @param pedagogie la jauge de pédagogie
     * @return un map contenant les valeurs des jauges
     */
    private Map<String, Object> recupValeurJauges(Jauge bonheur, Jauge pedagogie){
        Map<String, Object> valeurJauges = new HashMap<>();
        valeurJauges.put("bonheur", bonheur.getValue());
        valeurJauges.put("pedagogie", pedagogie.getValue());
        return valeurJauges;
    }

    /**
     * Calcule l'apparition d'un événement irrégulier
     * le calcul est :
     * Bonus = 100 - val de jauges * frequence
     * Malus = val de jauges * frequence
     * ce qui nous donne une probabilité d'apparition de l'événement entre 0 et 1
     * On tire un nombre entre 0 et 1 si ce nombre est inférieur à la probabilité
     * on renvoie l'événement
     * @param bonheur la jauge de bonheur
     * @param pedagogie la jauge de pédagogie
     * @return la liste des événements irréguliers
     */
    public List <Integer> calculApparitionEvenementIrregulier (Jauge bonheur, Jauge pedagogie){
        Map<String, Object> valeurJauges = recupValeurJauges(bonheur, pedagogie);
        List <Integer> listeEvenement = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Object>> entry : donneeEvenement.entrySet()) {
            Map<String, Object> eventDetails = entry.getValue();
            double frequence = (double) eventDetails.get("frequence");
            //bonus 100 - val de jauges * frequence
            //malus val de jauges *  frequence
            boolean pasEncoreTire = true;
            if ((int) eventDetails.get("impactBonheur") == 1) {
                frequence *= (((100.0 - (double) valeurJauges.get("bonheur")) / 100.0)/2);
                pasEncoreTire = false;
                if (Math.random() < frequence) {
                    listeEvenement.add(entry.getKey());
                }
            } else if ((int)eventDetails.get("impactBonheur") == -1) {
                frequence *= (((double) valeurJauges.get("bonheur") /100.0)/2);
                pasEncoreTire = false;
                if (Math.random() < frequence) {
                    listeEvenement.add((int)entry.getKey());
                }
                // ajout du boolean pasEncoreTire car si impact 2 jauges ne doit pas être tiré 2 fois
            } else if ((int) eventDetails.get("impactPedagogie") == 1 && pasEncoreTire) {
                frequence *= (((100.0 - (double) valeurJauges.get("pedagogie")) / 100.0)/2);;
                if (Math.random() < frequence) {
                    listeEvenement.add((int)entry.getKey());
                }
            } else if ((int) eventDetails.get("impactPedagogie") == -1 && pasEncoreTire) {
                frequence *= (((double) valeurJauges.get("pedagogie") /100.0)/2);
                if (Math.random() < frequence) {
                    listeEvenement.add((int)entry.getKey());
                }
            }
        }
        return listeEvenement;
    }

    /**public void appelerEvenementJournalier (Jauge bonheur, Jauge pedagogie){
        List <Integer> listeEvenement = calculApparitionEvenementIrregulier(bonheur, pedagogie);
        for (int idEveIrre : listeEvenement) {

        }
    }**/

}
