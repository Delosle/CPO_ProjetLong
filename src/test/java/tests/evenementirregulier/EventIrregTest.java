package tests.evenementirregulier;

import static org.junit.Assert.assertEquals;

import n7simulator.modele.evenements.Evenement_Irregu;
import n7simulator.modele.Partie;

import n7simulator.modele.Temps;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class EventIrregTest {
	

	public final static double EPSILON = 0.001;

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
        assertEquals("Les enseignants sont en grève pour protester contre l absentéisme rampant et la pénurie chronique de tampons pour effacer les tableaux à craie. Fatigués de jongler entre classes désertes et tableaux illisibles, ils réclament des soutions pour redonner vie à leurs salles de classe!.", event.getDescription());
        assertEquals(-10, event.getImpactBonheur());
        assertEquals(0, event.getImpactArgent());
        assertEquals(-10, event.getImpactPedagogie());
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
        assertEquals(10, nouvellePartie.getJaugeBonheur().getValue(), EPSILON);
        assertEquals(500, nouvellePartie.getJaugeArgent().getValue(), EPSILON);
        assertEquals(15, nouvellePartie.getJaugePedagogie().getValue(), EPSILON);
    }



}
