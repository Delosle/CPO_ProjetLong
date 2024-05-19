package n7simulator.modele.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import n7simulator.modele.Partie;

public class PartieTest {
	
	Partie nouvellePartie;
	
	@Before public void setUp() {
		//Creer une nouvelle partie
		nouvellePartie = new Partie();
	}
	
	/*
	 * Test vérifiant la date par defaut de la partie lorsque celle-ci 
	 * est créée de zéro (nouvelle partie et non chargement à partir 
	 * d'une sauvegarde)
	 */
	@Test
	public void testInitialisationNouvellePartieDate() {
		LocalDate dateNouvellePartie = nouvellePartie.getJourneeEnCours();
		assertEquals(1, dateNouvellePartie.getDayOfMonth());
		assertEquals(Month.SEPTEMBER, dateNouvellePartie.getMonth());
		assertEquals(2024, dateNouvellePartie.getYear());
	}
	
	/*
	 * Test vérifiant la date de la partie lorsque celle-ci est créée
	 * à partir d'une sauvegarde.
	 */
	@Test
	public void testInitialisationPartieSauvegardeeDate() {
		int anneeTest = 2025;
		int moisTest = 4;
		int jourTest = 30;
		Month mois = Month.of(moisTest);
		Partie partieSauvegardee = new Partie(LocalDate.of(anneeTest, moisTest, jourTest));
		LocalDate dateCreee = partieSauvegardee.getJourneeEnCours();
		assertEquals(jourTest, dateCreee.getDayOfMonth());
		assertEquals(mois, dateCreee.getMonth());
		assertEquals(anneeTest, dateCreee.getYear());
	}
	
	/**
	 * Test l'incrementation de la date dans un cas "classique" (jour = jour + 1)
	 */
	@Test
	public void testIncrementDateClassique() {
		Partie partieDateClassique = new Partie(LocalDate.of(2024, 1, 20));
		partieDateClassique.incrementJournee();
		LocalDate dateApres = partieDateClassique.getJourneeEnCours();
		assertEquals(21, dateApres.getDayOfMonth());
		assertEquals(Month.JANUARY, dateApres.getMonth());
		assertEquals(2024, dateApres.getYear());
	}
	
	/**
	 * Test l'incrementation de la date lorsqu'il faut changer de mois
	 */
	@Test
	public void testIncrementDateFinMois() {
		Partie partieDateClassique = new Partie(LocalDate.of(2024, 1, 31));
		partieDateClassique.incrementJournee();
		LocalDate dateApres = partieDateClassique.getJourneeEnCours();
		assertEquals(1, dateApres.getDayOfMonth());
		assertEquals(Month.FEBRUARY, dateApres.getMonth());
		assertEquals(2024, dateApres.getYear());
	}
	
	/**
	 * Test l'incrementation de la date lorsqu'il faut changer d'annee
	 */
	@Test
	public void testIncrementDateFinAnnee() {
		Partie partieDateClassique = new Partie(LocalDate.of(2024, 12, 31));
		partieDateClassique.incrementJournee();
		LocalDate dateApres = partieDateClassique.getJourneeEnCours();
		assertEquals(1, dateApres.getDayOfMonth());
		assertEquals(Month.JANUARY, dateApres.getMonth());
		assertEquals(2025, dateApres.getYear());
	}

}
