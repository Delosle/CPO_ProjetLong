package n7simulator.modele;

import java.util.Observable;

import n7simulator.database.ValDebPartieDAO;
import n7simulator.joursuivant.JourSuivant;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;

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
	 * Les élèves
	 */
	private static GestionEleves gestionEleves;
	
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
			gestionEleves = new GestionEleves();
			
			// Ajout dans JourSuivant
			JourSuivant.getInstance().addImpactCourtTerme(gestionEleves);
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
}
