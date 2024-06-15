package n7simulator.joursuivant;

import n7simulator.modele.Partie;

/**
 * Classe representant l'impact des decisions concernant les consommables
 * du foy
 */
public class ImpactConsommableFoy implements ImpactJourSuivant{

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

    private static ImpactConsommableFoy instance;

    private ImpactConsommableFoy(){}

    @Override
    public void effectuerImpactJourSuivant() {
        Partie laPartie = Partie.getInstance();
        laPartie.getJaugeArgent().ajouter(impactArgent*laPartie.getGestionEleves().getNombreEleves());
        laPartie.getJaugeBonheur().ajouter(impactBonheur);
    }

    /**
     * Modifie l'impact sur la jauge bonheur
     * @param newImpactB : le nouvel impact sur la jauge bonheur
     */
    public void setImpactBonheur(double newImpactB){
        impactBonheur = newImpactB;
    }

    /**
     * Modifie l'impact sur la jauge argent
     * @param newImpactA : le nouvel impact sur la jauge argent
     */
    public void setImpactArgent(double newImpactA){
        impactArgent = newImpactA;
    }

    /**
     * Permet de récupérer l'impact des consommables du foy
     * @return : l'impact des consommables du foy
     */
    public static ImpactConsommableFoy getInstance(){
        if(instance == null){
            instance = new ImpactConsommableFoy();
        }
        return instance;
    }
}
