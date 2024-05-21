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
     * Initialiser une jaugeBornee à partir de ses attributs
     * @param nom le nom de la jauge bornée
     * @param initValue la valeur initiale de la jauge
     */
    public JaugeBornee(String nom, int initValue){
        super(nom,initValue);
    }

    /**
     * Initialiser une jauge
     * @param nom le nom de la jauge bornée
     */
    public JaugeBornee(String nom){
        super(nom);
    }
    
    @Override
    public void ajouter(int valeur){
        if(super.valeur + valeur >= BORNE_MAX) super.valeur = BORNE_MAX;
        else super.ajouter(valeur);
    }
}
