package n7simulator.modele.Evenements;
import n7simulator.modele.jauges.Jauge;
import n7simulator.database.CreerBddSauvegarde;
import n7simulator.database.CreationBddAdmin;

import java.util.List;

public class testerApparitionEvenement {

        public static void main(String[] args) {
            //CreationBddAdmin.initialiserBddAdmin();
            //CreerBddSauvegarde.initialiserBddSauvegarde();
            List<Integer> listeEvenement;
            Jauge bonheur = new Jauge("Bonheur", 80);
            Jauge pedagogie = new Jauge("Pedagogie", 70);
            ApparitionEvenementIrregulier calculatorEvenmentIrregulier = new ApparitionEvenementIrregulier();
            listeEvenement = calculatorEvenmentIrregulier.calculApparitionEvenementIrregulier(bonheur, pedagogie);
            System.out.println("Liste des événements : " + listeEvenement);
        }
    }