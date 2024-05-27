package n7simulator.gestionBdd;
import java.util.*;
import java.sql.*;

public class TesterBd {

    public static void main(String[] args) {
        CreationBddAdmin.initialiserBddAdmin();
        String nomDePartie = args[0];
        List<String> fichiersNom = GestionBddSauvegarde.recupererNomsBddSauvegarde();
        for (String fichierNom : fichiersNom) {
            System.out.println(fichierNom);
        }
        try {
            CreerBddSauvegarde.creerBddSauvegarde(nomDePartie);
        } catch (PartieExisteDejaException e) {
            System.out.println(e.getMessage());
        }
    }
}
