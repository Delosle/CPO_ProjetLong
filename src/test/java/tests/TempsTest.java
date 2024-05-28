package tests;

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
		nouveauTemps.setJourneeEnCours(LocalDate.of(2024, 9, 1));
		LocalDate dateNouveauTemps = nouveauTemps.getJourneeEnCours();
		assertEquals(1, dateNouveauTemps.getDayOfMonth());
		assertEquals(Month.SEPTEMBER, dateNouveauTemps.getMonth());
		assertEquals(2024, dateNouveauTemps.getYear());
	}
	
	
	/**
	 * Test l'incrementation de la date dans un cas "classique" (jour = jour + 1)
	 */
	@Test
	public void testIncrementDateClassique() {
		nouveauTemps.setJourneeEnCours(LocalDate.of(2024, 1, 20));
		nouveauTemps.incrementJournee();
		LocalDate dateApres = nouveauTemps.getJourneeEnCours();
		assertEquals(21, dateApres.getDayOfMonth());
		assertEquals(Month.JANUARY, dateApres.getMonth());
		assertEquals(2024, dateApres.getYear());
	}
	
	/**
	 * Test l'incrementation de la date lorsqu'il faut changer de mois
	 */
	@Test
	public void testIncrementDateFinMois() {
		nouveauTemps.setJourneeEnCours(LocalDate.of(2024, 1, 31));
		nouveauTemps.incrementJournee();
		LocalDate dateApres = nouveauTemps.getJourneeEnCours();
		assertEquals(1, dateApres.getDayOfMonth());
		assertEquals(Month.FEBRUARY, dateApres.getMonth());
		assertEquals(2024, dateApres.getYear());
	}
	
	/**
	 * Test l'incrementation de la date lorsqu'il faut changer d'annee
	 */
	@Test
	public void testIncrementDateFinAnnee() {
		nouveauTemps.setJourneeEnCours(LocalDate.of(2024, 12, 31));
		nouveauTemps.incrementJournee();
		LocalDate dateApres = nouveauTemps.getJourneeEnCours();
		assertEquals(1, dateApres.getDayOfMonth());
		assertEquals(Month.JANUARY, dateApres.getMonth());
		assertEquals(2025, dateApres.getYear());
	}

}