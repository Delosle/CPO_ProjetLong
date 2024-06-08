package n7simulator.modele;

import java.util.Observable;

import n7simulator.joursuivant.ImpactJourSuivantCourtTerme;

@SuppressWarnings("deprecation")
public class Bibliotheque extends Observable implements ImpactJourSuivantCourtTerme{
	private static Bibliotheque instance;
	private final int prixLivre = 15;
	private int nbLivre;
	
	/**
	 * Permet de Créer le crous à partir d'un qualité et d'un prix de vente
	 * @param qualite
	 * @param prixVente
	 */
	private Bibliotheque (int nbLivre) {
		this.nbLivre = nbLivre;
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
		this.nbLivre = nbLivre;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public int getPrixLivre() {
		return prixLivre;
	}

	@Override
	public void effectuerImpactJourSuivantCourtTerme() {
		// TODO Auto-generated method stub
		
	}
	
	
}
