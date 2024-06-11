package n7simulator.modele.evenements;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import n7simulator.database.DatabaseConnection;


public class ApparitionEvenementRegulier {

    /**
     * Contient les données de l'événement
     */
    private Map<Integer, Map<String, Object>> donneeEvenement; // contient les données de l'événement

    /**
     * Constructeur de la classe ApparitionEvenementRegulier
     */
    public ApparitionEvenementRegulier() {
        donneeEvenement = new LinkedHashMap<>();
        recupDonneeEvenement();
    }

    /**
     * Vérifie si un événement régulier doit se produire
     */
    public List <Integer> verifEvenementRegulier (LocalDate dateActuelle) {
        //System.out.println("Date actuelle : " + dateActuelle);
        List <Integer> listeEvenement = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Object>> entry : donneeEvenement.entrySet()) {
            Map<String, Object> eventDetails = entry.getValue();
            String dateString = eventDetails.get("debut").toString();
            //System.out.println("Date de l'événement : " + dateString + " date parametre : " + dateActuelle);
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

    public Map<Integer, Map<String, Object>> getDonneeEvenement() {
        return donneeEvenement;
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
            Map<String, Object> event = donneeEvenement.get(idEveReg);
            event.put("debut", eventDetails.get("dateEvenement"));
            donneeEvenement.put(idEveReg, event);
        }
    }

    /**
     * Récupère les données de l'événement dans la base de données
     */
    private void recupDonneeEvenement() {
        try {
            Connection conn = DatabaseConnection.getDBConnexion();
            String query = "SELECT id_eve_reg, periode, debut FROM evenement_regulier";
            ResultSet result = DatabaseConnection.effectuerRequete(query, conn);
            while (result.next()) {
                int idEveReg = result.getInt("id_eve_reg");
                Map<String, Object> eventDetails = new HashMap<>();
                eventDetails.put("periode", result.getObject("periode"));
                eventDetails.put("debut", result.getObject("debut"));
                donneeEvenement.put(idEveReg, eventDetails);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des données " +
                    "de l'evenement régulier dans la base de données.");
            e.printStackTrace();

        }
    }
}
