package n7simulator.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import n7simulator.database.ProfesseurDAO;
import n7simulator.joursuivant.JourSuivant;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe modélisant une partie du jeu N7Simulator.
 * La partie peut être chargée à partir d'une sauvegarde, ou 
 */
public final class Partie extends Observable {
	
	/**
	 * L'instance unique de la partie.
	 */
	private static Partie instance;

	/**
	 * La jauge d'argent
	 */
	private static Jauge jaugeArgent;
	
	/**
	 * La jauge de Bonheur
	 */
	private static Jauge jaugeBonheur;

	/**
	 * La jauge de Pédagigie
	 */
	private static Jauge jaugePedagogie;
	
	/**
	 * Le nom de la partie (nom de la sauvegarde)
	 */
	private static String nomPartie;
	
	/**
	 * La gestion des professeurs de la partie
	 */
	private static GestionProfesseurs gestionProfesseurs;
	
	/**
	 * Les élèves
	 */
	private static GestionEleves gestionEleves;
	
	/**
	 * Le temps
	 */
	private static Temps temps;
	
	private Partie() {}
	
	/**
	 * Permet de récupérer la partie.
	 * @return la partie
	 */
	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
			jaugeArgent = new Jauge("Argent");
			jaugeBonheur = new JaugeBornee("Bonheur");
			jaugePedagogie = new JaugeBornee("Pedagogie");
			gestionProfesseurs = new GestionProfesseurs((List<Professeur>)new ArrayList<Professeur>(), ProfesseurDAO.getAllProfesseurs());
			temps = new Temps();
			// to delete
			temps.setJourneeEnCours(LocalDate.now());
			gestionEleves = new GestionEleves();
			
			// Ajout dans JourSuivant
			JourSuivant jourSuivant = JourSuivant.getInstance();
			jourSuivant.addImpactCourtTerme(gestionProfesseurs);
			jourSuivant.addImpactCourtTerme(temps);
			jourSuivant.addImpactCourtTerme(gestionEleves);
		}
		return instance;
	}

	/**
	 * Obtenir la jauge Argent
	 * @return la jauge d'argent
	 */
	public Jauge getJaugeArgent(){
		return jaugeArgent;
	}

	/**
	 * Obtenir la jauge de Bonheur
	 * @return la jauge de Bonheur
	 */
	public Jauge getJaugeBonheur(){
		return jaugeBonheur;
	}

	/**
	 * Obtenir la jauge de Pédagogie
	 * @return la jauge de Pédagogie
	 */
	public Jauge getJaugePedagogie(){
		return jaugePedagogie;
	}
	
	/**
	 * Permet de récupérer la gestion des élèves.
	 * @return la gestion des élèves.
	 */
	public GestionEleves getGestionEleves() {
		return gestionEleves;
	}
	
	/**
	 * Obtenir la gestion des professeurs de la partie.
	 * @return : la gestion des professeurs de la partie
	 */
	public GestionProfesseurs getGestionProfesseurs() {
		return gestionProfesseurs;
	}
	
	/**
	 * Obtenir le temps de la partie.
	 * @return le temps.
	 */
	public Temps getTemps() {
		return temps;
	}
}
