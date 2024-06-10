package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import n7simulator.modele.GestionEleves;
import n7simulator.modele.Partie;

public class GestionElevesTest {
	
	/*
	 * Test vérifiant que les élèves inscrits sont bien ajoutés.
	 */
	@Test
	public void testInscrireEleves() {
		GestionEleves gestElv = Partie.getInstance().getGestionEleves();
		int nbNvxEleves = 39;
		int oldNbEleves = gestElv.getNombreEleves();
		gestElv.inscrireEleves(nbNvxEleves);
		assertEquals(oldNbEleves + nbNvxEleves, gestElv.getNombreEleves());		
	}
	
	/*
	 * Test vérifiant que les élèves désinscrits sont bien ajoutés.
	 */
	@Test
	public void testDesinscrireEleves() {
		GestionEleves gestElv = Partie.getInstance().getGestionEleves();
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
		GestionEleves gestElv = Partie.getInstance().getGestionEleves();
		int oldNbEleves = gestElv.getNombreEleves();
		int nbExEleves = 13;
		gestElv.desinscrireEleves(oldNbEleves);
		gestElv.desinscrireEleves(nbExEleves);
		assertEquals(0, gestElv.getNombreEleves());		
	}
}