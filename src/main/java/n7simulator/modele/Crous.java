package n7simulator.modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import n7simulator.database.CrousDAO;
import n7simulator.database.DatabaseConnection;
import n7simulator.joursuivant.ImpactJourSuivant;
import n7simulator.joursuivant.JourSuivant;

/**
 * Classe permettant la simulation du Crous
 */
public class Crous extends Observable implements ImpactJourSuivant {
	public static final String[] QUALITE_STR = {"Mauvaise", "Acceptable", "Bonne", "Très bonne", "Excellente"};
	private static Crous instance;
	private int qualite;
	private double prixVente;
	
	/**
	 * Permet de Créer le crous à partir d'un qualité et d'un prix de vente
	 * @param qualite
	 * @param prixVente
	 */
	private Crous() {
		this.qualite = 0;
		this.prixVente = 0;
		JourSuivant.getInstance().addImpact(this);
	}
	
	/**
	 * Permet de récupérer le Crous.
	 * @return le Crous
	 */
	public static Crous getInstance() {
		if (instance == null) {
			instance = new Crous();
		}
		return instance;
	}

	/**
	 * Getter de la qualité
	 * @return la qualité actuelle des repas
	 */
	public int getQualite() {
		return qualite;
	}

	/**
	 * Setter de la qualité
	 * notifie les observateurs du changement
	 * @param qualite souhaitée
	 */
	public void setQualite(int qualite) {
		this.qualite = qualite;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Geter du prix de vente
	 * @return le prix de Vente actuel
	 */
	public double getPrixVente() {
		return prixVente;
	}

	/**
	 * Setter du prix de vente
	 * notifie les observateurs du changement
	 * @param prix de vente souhaitée
	 */
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * permet de retourner la marge sur la vente des repas
	 * @return la marge sous forme X.XX comme les centimes en euros
	 */
	public double getMarge() {
		//Afin de multiplier la marge d'un repas par le nombre d'élève
		int nbEleves = Partie.getInstance().getGestionEleves().getNombreEleves();
		double marge = this.prixVente - CrousDAO.getPrixVente(this.qualite + 1);
		// round afin d'être sous la forme 2 centimes maximum.
		return Math.round(marge * 100.0 ) / 100.0 * nbEleves;
	}

	/**
	 * Recupere la liste de qualites avec leur prix associe sous forme de chaine de caractères
	 * @return liste des qualités
	 */
	public String[] getListeQualites() {
		HashMap<Integer, Double> mapQualitePrix = CrousDAO.getListeQualitesPrix();
		List<String> listeRetour = new ArrayList<>();

		for(Map.Entry<Integer, Double> elementQualitePrix : mapQualitePrix.entrySet()) {
			listeRetour.add(QUALITE_STR[elementQualitePrix.getKey() - 1] + " : " + "%.2f".formatted(elementQualitePrix.getValue()));
		}
		
		String[] retour = new String[listeRetour.size()];
		for (int i = 0; i < listeRetour.size(); i ++) {
			retour[i] = listeRetour.get(i);
		}
		return retour;
	}
	

	@Override
	public void effectuerImpactJourSuivant() {
		double marge = getMarge();
		Partie instancePartie = Partie.getInstance();
		instancePartie.getJaugeArgent().ajouter((int)marge);
		double margeIndividuelle = getMarge() / instancePartie.getGestionEleves().getNombreEleves();
		if (margeIndividuelle > 1) {
			instancePartie.getJaugeBonheur().ajouter(- 10);
		}
		else if(margeIndividuelle < 1) {
			instancePartie.getJaugeBonheur().ajouter(2.0 * qualite);
		}
	}
	
	
}
