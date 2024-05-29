package n7simulator.modele;

import java.time.LocalDate;
import java.util.Observable;

/**
 * Classe modélisant le temps.
 */
public class Temps extends Observable {
	
	/**
	 * La date de la journée en cours pour la partie.
	 */
	private LocalDate journeeEnCours;
	
	/**
	 * Permet de creer un nouvel objet Temps.
	 * @param dateDebut : la date de debut de la partie
	 */
	public Temps() {
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
	
	public void setJourneeEnCours(LocalDate dateEnCours) {
		this.journeeEnCours = dateEnCours;
	}
}