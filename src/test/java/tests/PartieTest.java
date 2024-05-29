package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import n7simulator.modele.Partie;

public class PartieTest {	
	/*
	 * Test vérifiant que la partie est bien un singleton.
	 */
	@Test
	public void testPartieSingleton() {
		Partie partie1 = Partie.getInstance();
		Partie partie2 = Partie.getInstance();
		assertEquals(partie1, partie2);
	}
	
	/*
	 * Test vérifiant que les élèves inscrits sont bien ajoutés.
	 */
	@Test
	public void testInscrireEleves() {
		Partie partie = Partie.getInstance();
		int nbNvxEleves = 39;
		int oldNbEleves = partie.getNombreEleves();
		partie.inscrireEleves(nbNvxEleves);
		assertEquals(oldNbEleves + nbNvxEleves, partie.getNombreEleves());		
	}
	
	/*
	 * Test vérifiant que les élèves désinscrits sont bien ajoutés.
	 */
	@Test
	public void testDesinscrireEleves() {
		Partie partie = Partie.getInstance();
		partie.inscrireEleves(20);
		int oldNbEleves = partie.getNombreEleves();
		int nbExEleves = 5;
		partie.desinscrireEleves(nbExEleves);
		assertEquals(oldNbEleves - nbExEleves, partie.getNombreEleves());		
	}
	
	/*
	 * Test vérifiant que les élèves désinscrits sont bien ajoutés.
	 */
	@Test
	public void testDesinscrireElevesInferieur0() {
		Partie partie = Partie.getInstance();
		int oldNbEleves = partie.getNombreEleves();
		int nbExEleves = 13;
		partie.desinscrireEleves(oldNbEleves);
		partie.desinscrireEleves(nbExEleves);
		assertEquals(0, partie.getNombreEleves());		
	}
}