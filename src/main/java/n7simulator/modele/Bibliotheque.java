package n7simulator.modele;

import java.util.Observable;

import n7simulator.joursuivant.ImpactJourSuivant;
import n7simulator.joursuivant.JourSuivant;

public class Bibliotheque extends Observable implements ImpactJourSuivant {
	
	private static Bibliotheque instance;
	private static final int PRIX_LIVRE = 15;
	private int nbLivre;
	private int ancienNbLivre;
	
	/**
	 * Permet de Créer la Bibliotheque à partir d'un nombre de livre initial
	 * ancien livre sert à caculer l'impact
	 */
	private Bibliotheque() {
		JourSuivant.getInstance().addImpact(this);
	}
	
	/**
	 * Permet de récupérer la Bibliotheque.
	 * @return la bibliotheque
	 */
	public static Bibliotheque getInstance() {
		if (instance == null) {
			instance = new Bibliotheque();
		}
		return instance;
	}

	/**
	 * getter du nombre de livre
	 * @return le nombre de livre présent dans la bibliotheque
	 */
	public int getNbLivre() {
		return nbLivre;
	}

	/**
	 * Modifie le nombre de livres le nombre de livres.
	 */
	public void setNbLivre(int nbLivre) {
		this.ancienNbLivre = this.nbLivre;
		this.nbLivre = nbLivre;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Récupère le prix d'un livre
	 * @return le prix d'achat ou de revente d'un livre
	 */
	public int getPrixLivre() {
		return PRIX_LIVRE;
	}

	@Override
	public void effectuerImpactJourSuivant() {
		Partie instancePartie = Partie.getInstance();
		instancePartie.getJaugeArgent().ajouter((double)(this.ancienNbLivre - this.nbLivre) * PRIX_LIVRE);
		if (nbLivre > 5) {
			instancePartie.getJaugePedagogie().ajouter(0.5 * nbLivre + 5);
			instancePartie.getJaugeBonheur().ajouter(5);
		}
		else {
			instancePartie.getJaugePedagogie().ajouter(-5);
		}		
	}
	
	
}
