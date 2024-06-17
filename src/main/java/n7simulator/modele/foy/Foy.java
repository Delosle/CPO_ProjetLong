package n7simulator.modele.foy;

import java.util.ArrayList;
import java.util.List;

import n7simulator.joursuivant.ImpactJourSuivant;
import n7simulator.modele.Partie;

/**
 * Classe representant le foy.
 */
public class Foy implements ImpactJourSuivant {

	/**
	 * L'impact sur la jauge bonheur des prises de decisions
	 * concernant les consommables du foy
	 */
    private double impactBonheur;

    /**
	 * L'impact sur la jauge argent des prises de decisions
	 * concernant les consommables du foy
     */
    private double impactArgent;
	
	/**
	 * Liste des consommables
	 */
    private List<ConsommableFoy> consommables;
    
    public Foy() {
    	this.consommables = new ArrayList<>();
    }
    
    /**
     * Set la liste des consommables
     * @param newConsommables la nouvelle listes de consommables
     */
    public void setConsommablesListe(List<ConsommableFoy> newConsommables) {
    	this.consommables = newConsommables;
    }
    
    /**
     * Retourne la liste des consommables du foy.
     * @return liste de consommables
     */
    public List<ConsommableFoy> getConsommables() {
        return this.consommables;
    }

	@Override
	public void effectuerImpactJourSuivant() {
		Partie laPartie = Partie.getInstance();
        laPartie.getJaugeArgent().ajouter(impactArgent*laPartie.getGestionEleves().getNombreEleves());
        laPartie.getJaugeBonheur().ajouter(impactBonheur);
	}
	
	/**
     * Modifie l'impact sur la jauge bonheur
     * @param newImpactBohneur : le nouvel impact sur la jauge bonheur
     */
    public void setImpactBonheur(double newImpactBohneur){
        impactBonheur = newImpactBohneur;
    }

    /**
     * Modifie l'impact sur la jauge argent
     * @param newImpactArgent : le nouvel impact sur la jauge argent
     */
    public void setImpactArgent(double newImpactArgent){
        impactArgent = newImpactArgent;
    }
}
