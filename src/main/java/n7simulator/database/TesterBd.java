package n7simulator.database;
import java.util.*;
import java.sql.*;

public class TesterBd {

    public static void main(String[] args) {
        /**List<String> fichiersNom = GestionBddSauvegarde.recupererNomsBddSauvegarde();
         for (String fichierNom : fichiersNom) {
         System.out.println(fichierNom);
         }**/
        CreerBddSauvegarde.initialiserBddSauvegarde();
        /**ArrayList<String> nomPartie = GestionBddSauvegarde.recupererNomPartie();
         for (String nom : nomPartie) {
         System.out.println(nom);
         }**/
        Map<String, Object> infoBdd = new HashMap<>();
        infoBdd =  GestionBddSauvegarde.recupererInfoBddSauvegarde (1);
        for (Map.Entry<String, Object> entry : infoBdd.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        try {
            GestionBddSauvegarde.nomPartieDisponible("Partie1");
        } catch (PartieExisteDejaException e) {
            System.out.println(e.getMessage());
        }
    }
}