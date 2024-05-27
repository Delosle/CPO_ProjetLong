package n7simulator.modele;

import java.time.LocalDate;
import java.util.Observable;

/**
 * Classe modélisant le temps.
 */
public class Temps extends Observable {
	
	/**
	 * La date par defaut du début du jeu.
	 */
	private static final LocalDate DATE_DEBUT = LocalDate.of(2024, 9, 1);
	
	/**
	 * La date de la journée en cours pour la partie.
	 */
	private LocalDate journeeEnCours;
	
	/**
	 * Permet de creer un nouvel objet Temps.
	 */
	public Temps() {
		this.journeeEnCours = DATE_DEBUT;
	}
	
	/**
	 * Permet de creer un objet Temps à partir d'une date sauvegardée.
	 * @param dateEnCoursSauvegarde : la date de la journée en cours pour la partie
	 */
	public Temps(LocalDate dateEnCoursSauvegarde) {
		this.journeeEnCours = dateEnCoursSauvegarde;
	}
	
	/**
	 * Passer au jour suivant en incrementant la date d'un jour.
	 */
	public void incrementJournee() {
		this.journeeEnCours = this.journeeEnCours.plusDays(1);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	
	/**
	 * Obtenir la date de la journee en cours pour la partie.
	 * @return la date de la journee en cours
	 */
	public LocalDate getJourneeEnCours() {
		return this.journeeEnCours;
	}
}