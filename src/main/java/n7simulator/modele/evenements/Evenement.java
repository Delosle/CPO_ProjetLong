package n7simulator.modele.evenements;

import n7simulator.modele.Partie;

/**
 * Classe qui représente un événement
 */
public abstract class Evenement{
	
	/**
	 * Id de l'événement en 
	 */
    protected int id;
    
    protected int impactBonheur;
    protected int impactArgent;
    protected int impactPedagogie;
    
    protected String description;
    protected String titre;

    /**
     * Constructeur
     */
    protected Evenement(int id){
        this.id = id;
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

