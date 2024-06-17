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
		Field biblioField;
		try {
			biblioField = Bibliotheque.class.getDeclaredField("instance");
			biblioField.setAccessible(true);
			biblioField.set(null, null);
			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Test vérifiant que le nombre de livre est bien initialisé
	 */
	@Test
	public void testInitialisationBiblio() {
		biblio = Bibliotheque.getInstance();
		biblio.setNbLivre(10);
		assertEquals(10, biblio.getNbLivre());
		assertEquals(15, biblio.getPrixLivre());
	}
	
	@Test
	public void testSetLivre() {
		biblio = Bibliotheque.getInstance();
		biblio.setNbLivre(10);
		assertEquals(10, biblio.getNbLivre());
		biblio.setNbLivre(5);
		assertEquals(5, biblio.getNbLivre());
	}

	/*
	 * Test vérifiant que le nombre de livre a un impact positif
	 */	
	@Test
	public void testImpactPos() {
		biblio = Bibliotheque.getInstance();
		biblio.setNbLivre(10);
		Partie instancePartie = Partie.getInstance();
		instancePartie.getJaugeArgent().ajouter(50);
		instancePartie.getJaugeBonheur().ajouter(50);
		instancePartie.getJaugePedagogie().ajouter(50);
		Double valeurAvantPeda = instancePartie.getJaugePedagogie().getValue();
		Double valeurAvantBonheur = instancePartie.getJaugeBonheur().getValue();
		
		//On fait modifier les jauges
		biblio.effectuerImpactJourSuivant();
		assertEquals(valeurAvantPeda + 0.5 * biblio.getNbLivre() + 5, instancePartie.getJaugePedagogie().getValue(), EPSILON);
		assertEquals(valeurAvantBonheur + 5, instancePartie.getJaugeBonheur().getValue(), EPSILON);
	}
	
	/*
	 * Test vérifiant que le nombre de livre à un impact négatif
	 */	
	@Test
	public void testImpactNeg() {
		biblio = Bibliotheque.getInstance();
		biblio.setNbLivre(5);
		Partie instancePartie = Partie.getInstance();
		instancePartie.getJaugeArgent().reinitialiserValeur(1500);
		instancePartie.getJaugeBonheur().reinitialiserValeur(20);
		instancePartie.getJaugePedagogie().reinitialiserValeur(20);
		Double valeurAvantPeda = instancePartie.getJaugePedagogie().getValue();
		
		//On fait modifier les jauges
		biblio.effectuerImpactJourSuivant();
		assertEquals(valeurAvantPeda - 5, instancePartie.getJaugePedagogie().getValue(), EPSILON);
	}
	
	/*
	 * Test vérifiant que le nombre de livre à un impact sur l'argent
	 */	
	@Test
	public void testImpactArgent() {
		biblio = Bibliotheque.getInstance();
		biblio.setNbLivre(5);
		Partie instancePartie = Partie.getInstance();
		instancePartie.getJaugeArgent().ajouter(50);
		instancePartie.getJaugeBonheur().ajouter(20);
		instancePartie.getJaugePedagogie().ajouter(20);
		Double valeurAvantArgent = instancePartie.getJaugeArgent().getValue();
		int ancienNbLivre = biblio.getNbLivre();
		//On fait modifier les jauges
		biblio.setNbLivre(5);
		biblio.effectuerImpactJourSuivant();
		assertEquals(valeurAvantArgent + (ancienNbLivre - biblio.getNbLivre()) * biblio.getPrixLivre(), instancePartie.getJaugeArgent().getValue(), EPSILON);
	}

}
