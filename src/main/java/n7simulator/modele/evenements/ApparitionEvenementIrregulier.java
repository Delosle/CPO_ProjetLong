package n7simulator.modele.evenements;

import java.util.*;
import n7simulator.database.EvenementIrregulierDAO;
import n7simulator.modele.jauges.Jauge;

public class ApparitionEvenementIrregulier {

    /**
     * Contient les données des événements
     */
    private Map<Integer, Map<String, Object>> donneesEvenements;

    /**
     * Constructeur de la classe ApparitionEvenementIrregulier
     */
    public ApparitionEvenementIrregulier() {
        donneesEvenements = EvenementIrregulierDAO.recupererDonneesEvenementsIrreguliers();
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
        for (Map.Entry<Integer, Map<String, Object>> entry : donneesEvenements.entrySet()) {
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
}
