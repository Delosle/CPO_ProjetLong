package n7simulator.modele.evenements;

import java.time.LocalDate;

import n7simulator.modele.Partie;

/**
 * Classe qui représente un événement régulier
 */
public class EvenementRegulier extends Evenement{
	
	/**
	 * Impact négatif jauge bohneur
	 */
    private int impactBonheurNeg;
    
    /**
     * Impact négatif jauge argent
     */
    private int impactArgentNeg;
    
    /**
     * Impact négatid jauge pédagogie
     */
    private int impactPedagogieNeg;

    /**
     * Constructeur
     */
    public EvenementRegulier(int id, LocalDate dateApparition, String titre, String description, int impactBohneur,
    		int impactArgent, int impactPedagogie, int impactBohneurNeg, int impactArgentNeg, int impactPedagogieNeg){
    	super(id, dateApparition, titre, description, impactBohneur, impactArgent, impactPedagogie);
        this.impactBonheurNeg = impactBohneurNeg;
        this.impactArgentNeg = impactArgentNeg;
        this.impactPedagogieNeg = impactPedagogieNeg;
    }
    
    /** @return la date d'apparition de l'événement
     */
    public LocalDate getDateApparition(){
        return dateApparition;
    }

    /**
     * Appliquer l'impact de l'événement sur la partie
     * Si choix est vrai, l'impact correspond au choix Oui, sinon il correspond au choix Non
     * @param p la partie sur laquelle appliquer l'impact
     */
    public void appliquerImpact(Partie p, boolean choix) {
        if (choix) {
            p.getJaugeArgent().ajouter(impactArgent);
            p.getJaugeBonheur().ajouter(impactBonheur);
            p.getJaugePedagogie().ajouter(impactPedagogie);
        } else {
            p.getJaugeArgent().ajouter(impactArgentNeg);
            p.getJaugeBonheur().ajouter(impactBonheurNeg);
            p.getJaugePedagogie().ajouter(impactPedagogieNeg);
        }
    }
}
