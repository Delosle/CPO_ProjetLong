package n7simulator.modele.evenements;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import n7simulator.database.DatabaseConnection;


public class ApparitionEvenementRegulier {

    private Map<Integer, Map<String, Object>> donneeEvenement; // contient les données de l'événement

    public ApparitionEvenementRegulier() {
        donneeEvenement = new LinkedHashMap<>();
        recupDonneeEvenement();
    }

    public List <Integer> verifEvenementRegulier (LocalDate dateActuelle) {
        System.out.println("Date actuelle : " + dateActuelle);
        List <Integer> listeEvenement = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Object>> entry : donneeEvenement.entrySet()) {
            Map<String, Object> eventDetails = entry.getValue();
            String dateString = eventDetails.get("debut").toString();
            System.out.println("Date de l'événement : " + dateString + "date parametre : " + dateActuelle);
            if (LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(dateActuelle)) {
                listeEvenement.add(entry.getKey());
            }
        }
        return listeEvenement;
    }

    public Map<Integer, Map<String, Object>> getDonneeEvenement() {
        return donneeEvenement;
    }

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
