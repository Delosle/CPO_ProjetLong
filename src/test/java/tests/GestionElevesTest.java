package tests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import n7simulator.modele.GestionEleves;
import n7simulator.modele.Partie;
import n7simulator.modele.jauges.Jauge;

public class GestionElevesTest {
	
	@Before
	public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
	   Field instance = Partie.class.getDeclaredField("instance");
	   instance.setAccessible(true);
	   instance.set(null, null);
	}
	
	/*
	 * Test vérifiant que les élèves inscrits sont bien ajoutés.
	 */
	@Test
	public void testInscrireEleves() {
		GestionEleves gestElv = new GestionEleves();
		int nbNvxEleves = 39;
		int oldNbEleves = gestElv.getNombreEleves();
		gestElv.inscrireEleves(nbNvxEleves);
		assertEquals(oldNbEleves + nbNvxEleves, gestElv.getNombreEleves());		
	}
	
	/*
	 * Test vérifiant que les élèves désinscrits sont bien supprimés.
	 */
	@Test
	public void testDesinscrireEleves() {
		GestionEleves gestElv = new GestionEleves();
		gestElv.inscrireEleves(20);
		int oldNbEleves = gestElv.getNombreEleves();
		int nbExEleves = 5;
		gestElv.desinscrireEleves(nbExEleves);
		assertEquals(oldNbEleves - nbExEleves, gestElv.getNombreEleves());		
	}
	
	/*
	 * Test vérifiant que les élèves désinscrits sont bien ajoutés.
	 */
	@Test
	public void testDesinscrireElevesInferieur0() {
		GestionEleves gestElv = new GestionEleves();
		int oldNbEleves = gestElv.getNombreEleves();
		int nbExEleves = 13;
		gestElv.desinscrireEleves(oldNbEleves);
		gestElv.desinscrireEleves(nbExEleves);
		assertEquals(0, gestElv.getNombreEleves());		
	}
	
	/*
	 * Test vérifiant que les élèves désinscrits sont bien ajoutés.
	 */
	@Test
	public void testImpactJourSuivant() {
		Partie partie = Partie.getInstance();
		Jauge bonheur = partie.getJaugeBonheur();
		Jauge pedagogie = partie.getJaugePedagogie();
		GestionEleves gstElv = partie.getGestionEleves();
		int niveauBohneur = 92;
		int niveauPedagogie = 34;
		int nbEleves = 98;
		
		// Remise à zéro des jauges
		bonheur.ajouter(-(bonheur.getValue()));
		pedagogie.ajouter(-(pedagogie.getValue()));
		gstElv.desinscrireEleves(gstElv.getNombreEleves());
		
		bonheur.ajouter(niveauBohneur);
		pedagogie.ajouter(niveauPedagogie);		
		partie.getGestionEleves().inscrireEleves(nbEleves);
		
		partie.getGestionEleves().effectuerImpactJourSuivant();
		
		int gainMax = (nbEleves / 5) + 10;
		int totalJauges = (int)(partie.getJaugeBonheur().getValue() + partie.getJaugePedagogie().getValue()) / 2;
		int gain = totalJauges * gainMax / 100;
		
		assertEquals(gain + nbEleves, partie.getGestionEleves().getNombreEleves());
	}
}