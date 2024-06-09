package n7simulator.modele;

import java.util.Observable;

import n7simulator.joursuivant.ImpactJourSuivantCourtTerme;
import n7simulator.joursuivant.JourSuivant;

@SuppressWarnings("deprecation")
public class Bibliotheque extends Observable implements ImpactJourSuivantCourtTerme{
	private static Bibliotheque instance;
	private final int prixLivre = 15;
	private int nbLivre;
	private int ancienNbLivre;
	
	/**
	 * Permet de Créer le crous à partir d'un qualité et d'un prix de vente
	 * @param qualite
	 * @param prixVente
	 */
	private Bibliotheque (int nbLivre) {
		this.nbLivre = nbLivre;
		this.ancienNbLivre = nbLivre;
		JourSuivant.getInstance().addImpactCourtTerme(this);
	}
	
	/**
	 * Permet de récupérer la Bibliotheque.
	 * @return la bibliotheque
	 */
	public static Bibliotheque getInstance(int nbLivre) {
		if (instance == null) {
			instance = new Bibliotheque(nbLivre);
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
	 * setter du nombre de livre
	 * notifie les observateurs du changement
	 */
	public void setNbLivre(int nbLivre) {
		this.ancienNbLivre = this.nbLivre;
		this.nbLivre = nbLivre;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public int getPrixLivre() {
		return prixLivre;
	}

	@Override
	public void effectuerImpactJourSuivantCourtTerme() {
		Partie instancePartie = Partie.getInstance();
		instancePartie.getJaugeArgent().ajouter((this.ancienNbLivre - this.nbLivre) * this.prixLivre);
		if (nbLivre > 5) {
			instancePartie.getJaugePedagogie().ajouter(0.5 * nbLivre + 5);
			instancePartie.getJaugeBonheur().ajouter(5);
		}
		else {
			instancePartie.getJaugePedagogie().ajouter(-5);
		}
		
	}
	
	
}
