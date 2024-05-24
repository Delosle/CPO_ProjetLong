package n7simulator.modele.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import n7simulator.modele.Partie;
import n7simulator.modele.Temps;

public class TempsTest {
	
	Temps nouveauTemps;
	
	@Before public void setUp() {
		//Creer un nouveau temps
		nouveauTemps = new Temps();
	}
	
	/*
	 * Test vérifiant la date par defaut du temps lorsque celui-ci
	 * est créé de zéro (nouveau temps et non chargement à partir 
	 * d'une sauvegarde)
	 */
	@Test
	public void testInitialisationNouveauTemps() {
		LocalDate dateNouveauTemps = nouveauTemps.getJourneeEnCours();
		assertEquals(1, dateNouveauTemps.getDayOfMonth());
		assertEquals(Month.SEPTEMBER, dateNouveauTemps.getMonth());
		assertEquals(2024, dateNouveauTemps.getYear());
	}
	
	/*
	 * Test vérifiant la date du temps lorsque celle-ci est créée
	 * à partir d'une sauvegarde.
	 */
	@Test
	public void testInitialisationTempsSauvegarde() {
		int anneeTest = 2025;
		int moisTest = 4;
		int jourTest = 30;
		Month mois = Month.of(moisTest);
		Temps tempsSauvegarde = new Temps(LocalDate.of(anneeTest, moisTest, jourTest));
		LocalDate dateCreee = tempsSauvegarde.getJourneeEnCours();
		assertEquals(jourTest, dateCreee.getDayOfMonth());
		assertEquals(mois, dateCreee.getMonth());
		assertEquals(anneeTest, dateCreee.getYear());
	}
	
	/**
	 * Test l'incrementation de la date dans un cas "classique" (jour = jour + 1)
	 */
	@Test
	public void testIncrementDateClassique() {
		Temps tempsDateClassique = new Temps(LocalDate.of(2024, 1, 20));
		tempsDateClassique.incrementJournee();
		LocalDate dateApres = tempsDateClassique.getJourneeEnCours();
		assertEquals(21, dateApres.getDayOfMonth());
		assertEquals(Month.JANUARY, dateApres.getMonth());
		assertEquals(2024, dateApres.getYear());
	}
	
	/**
	 * Test l'incrementation de la date lorsqu'il faut changer de mois
	 */
	@Test
	public void testIncrementDateFinMois() {
		Temps dateTempsClassique = new Temps(LocalDate.of(2024, 1, 31));
		dateTempsClassique.incrementJournee();
		LocalDate dateApres = dateTempsClassique.getJourneeEnCours();
		assertEquals(1, dateApres.getDayOfMonth());
		assertEquals(Month.FEBRUARY, dateApres.getMonth());
		assertEquals(2024, dateApres.getYear());
	}
	
	/**
	 * Test l'incrementation de la date lorsqu'il faut changer d'annee
	 */
	@Test
	public void testIncrementDateFinAnnee() {
		Temps dateTempsClassique = new Temps(LocalDate.of(2024, 12, 31));
		dateTempsClassique.incrementJournee();
		LocalDate dateApres = dateTempsClassique.getJourneeEnCours();
		assertEquals(1, dateApres.getDayOfMonth());
		assertEquals(Month.JANUARY, dateApres.getMonth());
		assertEquals(2025, dateApres.getYear());
	}

}
