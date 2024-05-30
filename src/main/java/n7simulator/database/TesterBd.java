package n7simulator.database;
import java.util.*;
import java.sql.*;

public class TesterBd {

    /**public static void main(String[] args) {
        CreerBddSauvegarde.initialiserBddSauvegarde();
        Map<String, Map<String, Object>> infoBdd = new HashMap<>();
        infoBdd =  GestionBddSauvegarde.recupererInfoBddSauvegarde (1);
        for (Map.Entry<String, Map<String, Object>> entry : infoBdd.entrySet()) {  //pour chaque table
            String tableName = entry.getKey(); //recupérer le nom de la table
        }
        Map<String, Map<String, Object>> testSauvegarde = new HashMap<>();
        testSauvegarde = peuplerDico();
        try {
            GestionBddSauvegarde.nomPartieDisponible("Partie1");
        } catch (PartieExisteDejaException e) {
            System.out.println(e.getMessage());
        }
        //GestionBddSauvegarde.sauvegarderDonnee(testSauvegarde, "Partie1");
    }**/
    public static void main(String[] args) {
        CreationBddAdmin.initialiserBddAdmin();
        System.out.println(CreerBddSauvegarde.verifierCreationTables());
    }

    public static Map<String, Map<String, Object>> peuplerDico() {
        // Créer les données pour la table EvenementEnCours
        Map<String, Object> evenementEnCours = new LinkedHashMap<>(); //Linkhashmap
        evenementEnCours.put("idEvenement", 1);
        evenementEnCours.put("idPartie", 1);
        evenementEnCours.put("jourDebut", "2024-07-01");

        // Créer les données pour la table ProfEmbauches
        Map<String, Object> profEmbauches = new LinkedHashMap<>();
        profEmbauches.put("idprof", 1);
        profEmbauches.put("idPartie", 1);
        profEmbauches.put("nbheure", 40);
        profEmbauches.put("salaire", 2000);

        // Créer les données pour la table Partie
        Map<String, Object> partie = new LinkedHashMap<>();
        partie.put("idPartie", 1);
        partie.put("bonheur", 80);
        partie.put("nbJours", 30);
        partie.put("pedagogie", 70);
        partie.put("idQualiteRepasCrous", 1);
        partie.put("prixVenteRepascrous", 4.5);
        partie.put("nbEleves", 50);
        partie.put("argent", 10000.0);
        partie.put("nomPartie", "Partie1");
        partie.put("estPerdue", 0);

        // Créer la map principale avec le nom des tables comme clés
        Map<String, Map<String, Object>> data = new LinkedHashMap<>();
        data.put("EvenementEnCours", evenementEnCours);
        data.put("ProfEmbauches", profEmbauches);
        data.put("Partie", partie);

        // renvoyer la map
        System.out.println(data);
        return data;
    }
}