package n7simulator.modele;

import java.time.LocalDate;
import java.util.Observable;


import n7simulator.db.ValDebPartieDAO;

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
}
