package n7simulator.modele.jauges;
/**
 * Cette classe définie la jauge bornée dont la valeur 
 * est inférieure à la borne max: On veuillera à ce que cette 
 * condition soit respectée.
 */
public class JaugeBornee extends Jauge {
    /**
     * La valeur maximale de la jauge.
     */
    public static final int BORNE_MAX = 100;

    /**
     * Initialiser une jauge
     * @param nom le nom de la jauge bornée
     */
    public JaugeBornee(String nom){
        super(nom);
    }
    
    @Override
    public void ajouter(int valeur){
        if(super.getValue() + valeur >= BORNE_MAX) super.ajouter(BORNE_MAX - super.getValue());
        else super.ajouter(valeur);
    }

    @Override
    public String toString() {
        
        return this.getNom() + " : " + getValue() + " / " + JaugeBornee.BORNE_MAX;
    }
}