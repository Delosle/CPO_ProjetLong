package n7simulator.modele.evenements;

import java.time.LocalDate;


import n7simulator.modele.Partie;

/**
 * Classe qui représente un événement irrégulier
 */
public class EvenementIrregulier extends Evenement{
	
	/**
	 * Indique s'il sagit d'un bonus
	 */
    private boolean bonus;

    /**
     * Constructeur
     */
    public EvenementIrregulier(int id, LocalDate dateApparition, String titre, String description,
    		int impactBohneur, int impactArgent, int impactPedagogie, boolean bonus){
        super(id, dateApparition, titre, description, impactBohneur, impactArgent, impactPedagogie);
        this.bonus = bonus;
    }
    
    /**Contient la date d'apparition de l'événement
     *  @return la date d'apparition de l'événement
     */
    public LocalDate getDateApparition(){
        return dateApparition;
    }

    /** Indique si l'événement est une bonus
     * @return si l'événement est un bonus
     */
    public boolean isBonus() {
        return bonus;
    }


    /**
     * Appliquer l'impact de l'événement sur la partie
     * @param p la partie sur laquelle appliquer l'impact
     */
    public void appliquerImpact(Partie p, boolean choix){
        p.getJaugeArgent().ajouter(impactArgent);
        p.getJaugeBonheur().ajouter(impactBonheur);
        p.getJaugePedagogie().ajouter(impactPedagogie);
    }
}
