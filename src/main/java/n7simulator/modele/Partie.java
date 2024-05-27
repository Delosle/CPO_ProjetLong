package n7simulator.modele;

import java.time.LocalDate;
import java.util.Observable;

import n7simulator.db.ValDebPartieDAO;

/**
 * Classe modélisant une partie du jeu N7Simulator.
 * La partie peut être chargée à partir d'une sauvegarde, ou 
 */
public final class Partie extends Observable {
	
	private static Partie instance;
	/**
	 * Le nombre d'élèves inscrits à l'N7.
	 */
	private int nombreEleves;
	
	private Partie() {}
	
	/**
	 * Permet de récupérer la partie.
	 * @return
	 */
	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
			ValDebPartieDAO.initialiserDonneesDebutPartie();
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
		
		// TODO calcul lendemain :
		// gainMax = nombreEleves / 5 --ex : 100 / 5 = 20
		// totalJauges = (jaugeBohneur + jaugePegagogie) / 2
		// si totalJauges >=25 : gain = totalJauges * gainMax / 100
		// sinon : gain = (totalJauges * gainMax / 100) - gainMax
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
