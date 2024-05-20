package n7simulator.modele;

import java.time.LocalDate;
import java.util.Observable;

/**
 * Classe modélisant une partie du jeu N7Simulator.
 * La partie peut être chargée à partir d'une sauvegarde, ou 
 */
public class Partie extends Observable {
	
	/**
	 * La date par defaut du début du jeu.
	 */
	private static final LocalDate DATE_DEBUT = LocalDate.of(2024, 9, 1);
	
	/**
	 * La date de la journée en cours pour la partie
	 */
	private LocalDate journeeEnCours;
	
	/**
	 * Permet de creer une nouvelle partie sans partir d'une sauvegarde
	 * Utilise donc les valeurs par defauts de debut de partie.
	 */
	public Partie() {
		this.journeeEnCours = DATE_DEBUT;
	}
	
	/**
	 * Permet de creer une partie à partir des données sauvegardées.
	 * @param dateEnCoursSauvegarde : la date de la journée en cours pour la partie
	 */
	public Partie(LocalDate dateEnCoursSauvegarde) {
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
