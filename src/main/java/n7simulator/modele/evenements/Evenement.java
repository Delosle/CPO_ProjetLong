package n7simulator.modele.evenements;

import java.time.LocalDate;

import n7simulator.modele.Partie;

/**
 * Classe qui représente un événement
 */
public abstract class Evenement{
	
	/**
	 * Id en BD
	 */
    protected int id;
    
    /**
     * Date d'apparition
     */
    protected LocalDate dateApparition;
    
    /**
     * Impact jauge bohneur
     */
    protected int impactBonheur;
    /**
     * Impact jauge argent
     */
    protected int impactArgent;
    /**
     * Impact jauge pédagogie
     */
    protected int impactPedagogie;
    
    /**
     * Description de l'événement
     */
    protected String description;
    /**
     * Titre de l'événement
     */
    protected String titre;

    /**
     * Constructeur
     * 
     * @param id l'identifiant de l'événement
     * @param dateApparition la date d'apparition de l'événement
     */
    protected Evenement(int id, LocalDate dateApparition, String titre, String description,
    		int impactBohneur, int impactArgent, int impactPedagogie){
        this.id = id;
        this.dateApparition = dateApparition;
        this.titre = titre;
        this.description = description;
        this.impactBonheur = impactBohneur;
        this.impactArgent = impactArgent;
        this.impactPedagogie = impactPedagogie;
    }

   /*getters*/

    /**
    * @return l'identifiant de l'événement
    */
    public int getId(){
        return id;
    }

    /**
    * @return la description de l'événement
     */
    public String getDescription(){
        return description;
    }

    /**
    * @return le titre de l'événement
    */
    public String getTitre(){
        return titre;
    }


    /**
    * @return l'impact sur le bonheur de l'événement
    */
    public int getImpactBonheur(){
        return impactBonheur;
    }

    /**
    * @return l'impact sur l'argent de l'événement
    */
    public int getImpactArgent(){
        return impactArgent;
    }

    /**
    * @return l'impact sur la pédagogie de l'événement
    */
    public int getImpactPedagogie(){
        return impactPedagogie;
    }

    /** Appliquer l'impact décrit dans l'evenement sur la partie.
    * La méthode d'application de l'impact change selon la nature de l'evenement
    * @param p la partie sur laquelle appliquer l'impact
    */
    public abstract void appliquerImpact(Partie p, boolean choix);

}

