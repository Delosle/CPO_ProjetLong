package tests;

import static org.junit.Assert.assertEquals;

import n7simulator.modele.Evenements.Evenement_Irregu;
import n7simulator.modele.Partie;

import n7simulator.modele.Temps;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class EventIrregTest {

    Partie nouvellePartie;

    @Before public void setUp() {
        //Creer une nouvelle partie
        nouvellePartie = Partie.getInstance();
    }

    @Test public void testCreationEventIrreg() {
        //Tester la creation d'un evenement irregulier
        LocalDate dateapparition = new Temps(LocalDate.now()).getJourneeEnCours();
        Evenement_Irregu event = new Evenement_Irregu(1, dateapparition);
        assertEquals(1, event.getId());
        assertEquals(dateapparition, event.getDateApparition());
        assertEquals("Grèves des Enseignants", event.getTitre());
        assertEquals("Imposer des grèves des enseignants pour challenger l’utilisateur et ajouter des difficultés dans le jeu.", event.getDescription());
        assertEquals(-15, event.getImpactBonheur());
        assertEquals(0, event.getImpactArgent());
        assertEquals(0, event.getImpactPedagogie());
    }

    @Test public void testAppliquerImpact() {
        //Tester l'applicaiton de l'impact d'un evenement irregulier
        //(Verifier si les valeurs de jauge ont bien ete modifiees)
        LocalDate dateapparition = new Temps(LocalDate.now()).getJourneeEnCours();
        Evenement_Irregu event2 = new Evenement_Irregu(4, dateapparition);
        event2.appliquerImpact(nouvellePartie);
        nouvellePartie.getJaugeArgent().ajouter(500);
        nouvellePartie.getJaugePedagogie().ajouter(15);
        nouvellePartie.getJaugeBonheur().ajouter(10);
        /*voir avec franck pour rajouter les tests*/
        assertEquals(10, nouvellePartie.getJaugeBonheur().getValue());
        assertEquals(500, nouvellePartie.getJaugeArgent().getValue());
        assertEquals(15, nouvellePartie.getJaugePedagogie().getValue());
    }



}
