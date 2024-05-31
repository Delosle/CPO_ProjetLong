package n7simulator.modele;

import java.util.Observable;

import n7simulator.ImpactJourSuivantCourtTerme;
import n7simulator.database.ValDebPartieDAO;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;

/**
 * Classe modélisant une partie du jeu N7Simulator.
 * La partie peut être chargée à partir d'une sauvegarde, ou 
 */
public final class Partie extends Observable {
	
	private static Partie instance;
	/**
	 * Le nombre d'élèves inscrits à l'N7.
	 */
	private static int nombreEleves;

	/**
	 * La jauge d'argent
	 */
	private static Jauge jaugeArgent;
	
	/**
	 * La jauge de Bonheur;
	 */
	private static Jauge jaugeBonheur;

	/**
	 * La jauge de Pédagigie
	 */
	private static Jauge jaugePedagogie;
	
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
		}
		return instance;
	}
	
	/**
	 * Obtenir le nombre d'élèves inscrits à l'N7.
	 * @return le nombre d'élèves inscrits.
	 */
	public int getNombreEleves() {
		return nombreEleves;
	}
	
	/**
	 * Permet d'inscrire de nouveaux élèves.
	 * @param nbEleves les nouveaux élèves
	 */
	public void inscrireEleves(int nbEleves) {
		nombreEleves += nbEleves;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Permet de désinscrire des élèves.
	 * @param nbEleves 
	 */
	public void desinscrireEleves(int nbEleves) {
		nombreEleves = nombreEleves - nbEleves < 0 ? 0 : nombreEleves - nbEleves;
		this.setChanged();
		this.notifyObservers(this);
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
	
	private void inscriptionsElevesJourSuivant() {
		// TODO set limite nombre élèves ?
		int gainMax = nombreEleves / (nombreEleves < 200 ? 5 : 50);		
		int totalJauges = (jaugeBonheur.getValue() + jaugePedagogie.getValue()) / 2;
		int gain = totalJauges * gainMax / 100;
		
		if (totalJauges >= 25) {
			inscrireEleves(gain);
		} else {
			gain = gain - gainMax;
			gain = gain > 0 ? gain : ( -gain);
			desinscrireEleves(gain);
		}		
	}	
}
