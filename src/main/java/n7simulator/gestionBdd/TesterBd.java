package n7simulator.gestionBdd;
import java.util.*;
import java.sql.*;

public class TesterBd {

    public static void main(String[] args) {
        String nomDePartie = args[0];
        /**List<String> fichiersNom = GestionBddSauvegarde.recupererNomsBddSauvegarde();
        for (String fichierNom : fichiersNom) {
            System.out.println(fichierNom);
        }**/
        //CreerBddSauvegarde.creerBddSauvegarde(nomDePartie);
        ArrayList<String> nomPartie = GestionBddSauvegarde.recupererNomPartie();
        for (String nom : nomPartie) {
            System.out.println(nom);
        }
        Map<String, Object> infoBdd = new HashMap<>();
        infoBdd =  GestionBddSauvegarde.recupererInfoBddSauvegarde (1);
        for (Map.Entry<String, Object> entry : infoBdd.entrySet()) {
            System.out.println("yeyeyeyyey");
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
