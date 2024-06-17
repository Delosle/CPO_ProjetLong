package n7simulator.modele.evenements;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import n7simulator.database.EvenementRegulierDAO;


public class ApparitionEvenementRegulier {

    /**
     * Contient les données de l'événement
     */
    private Map<Integer, Map<String, Object>> donneesEvenements;

    /**
     * Constructeur de la classe ApparitionEvenementRegulier
     */
    public ApparitionEvenementRegulier() {
        donneesEvenements = EvenementRegulierDAO.recupererDonneesEvenementsReguliers();
    }

    /**
     * Retourne la liste des événements qui doivent se produire à cette date.
     */
    public List <Integer> recupererEvenementsReguliersDate(LocalDate dateActuelle) {
        List <Integer> listeEvenement = new ArrayList<>();

        for (Map.Entry<Integer, Map<String, Object>> entry : donneesEvenements.entrySet()) {
            Map<String, Object> eventDetails = entry.getValue();
            String dateString = eventDetails.get("debut").toString();
            LocalDate dateDebut = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            if (dateDebut.equals(dateActuelle)) {
                listeEvenement.add(entry.getKey());
                int periode = (int) eventDetails.get("periode");
                if (periode < 30)
                    dateDebut = dateDebut.plusDays(periode);
                else if (periode == (365)) {
                    dateDebut = dateDebut.plusYears(1);
                } else {
                    periode = periode / 30;
                    dateDebut = dateDebut.plusMonths(periode);
                }
                eventDetails.put("debut", dateDebut);
            }
        }
        return listeEvenement;
    }

    /**
     * Retourne les données des événements
     * @return
     */
    public Map<Integer, Map<String, Object>> getDonneesEvenements() {
        return donneesEvenements;
    }

    /**
     * Met à jour les données de l'événement
     * @param donneeSauvegarde
     */
    public void setDonneeEvenement(List<Map<String, Object>> donneeSauvegarde) {
        for (Map<String, Object> eventDetails : donneeSauvegarde) {
            //recup id de l'événement
            int idEveReg = (int) eventDetails.get("idEvenementRegulier");
            //recup dico avec les données de l'événement
            Map<String, Object> event = donneesEvenements.get(idEveReg);
            event.put("debut", eventDetails.get("dateEvenement"));
            donneesEvenements.put(idEveReg, event);
        }
    }
}
