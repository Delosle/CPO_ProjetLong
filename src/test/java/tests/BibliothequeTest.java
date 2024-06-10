package tests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import n7simulator.modele.Bibliotheque;
import n7simulator.modele.Partie;

/**
 * classe de test de la classe Bibliotheque
 */

public class BibliothequeTest {
	public final static double EPSILON = 0.001;
	Bibliotheque biblio;

	@Before
	public void setUp() {
		//Creer la bilbiotheque
		Field biblio;
		try {
			biblio = Bibliotheque.class.getDeclaredField("instance");
			biblio.setAccessible(true);
			biblio.set(null, null);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Test vérifiant que le nombre de livre est bien initialisé
	 */
	@Test
	public void testInitialisationBiblio() {
		biblio = Bibliotheque.getInstance(10);
		System.out.println(biblio.getNbLivre());
		assertEquals(10, biblio.getNbLivre());
		assertEquals(15, biblio.getPrixLivre());
	}

	/*
	 * Test vérifiant que la bibliothèque est une instance
	 */
	@Test
	public void testInvariantInstance() {
		biblio = Bibliotheque.getInstance(10);
		biblio = Bibliotheque.getInstance(20);
		assertEquals(10, biblio.getNbLivre());
		assertEquals(15, biblio.getPrixLivre());
	}

	/*
	 * Test vérifiant que le nombre de livre peut être modifié
	 */
	@Test
	public void testSetLivre() {
		biblio = Bibliotheque.getInstance(10);
		assertEquals(10, biblio.getNbLivre());
		biblio.setNbLivre(5);
		assertEquals(5, biblio.getNbLivre());
	}

	/*
	 * Test vérifiant que le nombre de livre peut être modifié
	 */	
	@Test
	public void testImpactPos() {
		biblio = Bibliotheque.getInstance(10);
		Partie instancePartie = Partie.getInstance();
		Double valeurAvantPeda = instancePartie.getJaugePedagogie().getValue();
		Double valeurAvantBonheur = instancePartie.getJaugeBonheur().getValue();
		//On check que valeur bien là
		assertEquals(valeurAvantPeda, instancePartie.getJaugePedagogie().getValue(), EPSILON);
		assertEquals(valeurAvantBonheur, instancePartie.getJaugeBonheur().getValue(), EPSILON);
		//On fait modifier les jauges
		biblio.effectuerImpactJourSuivantCourtTerme();
		assertEquals(valeurAvantPeda + 0.5 * biblio.getNbLivre() + 5, instancePartie.getJaugePedagogie().getValue(), EPSILON);
		assertEquals(valeurAvantBonheur + 5, instancePartie.getJaugeBonheur().getValue(), EPSILON);
	}

}
