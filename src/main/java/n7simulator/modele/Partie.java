package n7simulator.modele;

import java.time.LocalDate;
import java.util.Observable;

/**
 * Classe modélisant une partie du jeu N7Simulator.
 * La partie peut être chargée à partir d'une sauvegarde, ou 
 */
public final class Partie extends Observable {
	
	private static Partie instance;
	/**
	 * Le nombre d'élèves inscrits à l'N7.
	 */
	private int nombreEleves = 100;
	
	private Partie() {}
	
	/**
	 * Permet de récupérer la partie.
	 * @return
	 */
	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
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
	 * @param nouveauxEleves le nombre d'élèves à inscrire
	 */
	public void inscrireEleves(int nouveauxEleves) {
		nombreEleves += nouveauxEleves;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Permet de désinscrire de nouveaux élèves.
	 * @param exEleves le nombre d'élèves à désinscrire
	 */
	public void desinscrireEleves(int exEleves) {
		nombreEleves -= exEleves;
		this.setChanged();
		this.notifyObservers(this);
	}
}
