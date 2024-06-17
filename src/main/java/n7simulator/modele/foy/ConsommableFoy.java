package n7simulator.modele.foy;

import java.util.Observable;

/**
 * Classe representant un consommable du batiment foy
 */
public class ConsommableFoy extends Observable {

    /**
     * L'identifiant du consommable.
     */
    private int id;

    /**
     * Le nom pour la description du repas
     */
    private String nom;

    /**
     * Le prix du consommable
     */
    private double prix;
    /**
     * Le prix à partir duquel le consommable est considéré
     * cher par les étudiants
     */
    private double prixLimite;
    /**
     * le nom de l'image à charger pour le consommableFoy
     */
    private String image;

    /**
     * Initialiser un consommable au foy.
     * @param nom le nom du consommable
     * @param prix le prix du consommable
     * @param prixLimite le prixLimite du consommable
     * @param image le nom de l'image pour le repas
     */
    public ConsommableFoy(int id, String nom, double prix, double prixLimite, String image){
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixLimite = prixLimite;
        this.image = image;
    }

    /**
     * Retourne le prix du consommable.
     * @return
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Retourne le prix limite du consommable.
     * @return
     */
    public double getPrixLimite() {
        return prixLimite;
    }

    /**
     * modifier le prix du consommable
     * @param prix le nouveau prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
        setChanged();
        notifyObservers(prix);
    }

    /**
     * Retourne le nom de l'image du consommable.
     * @return
     */
    public String getImage(){
        return image;
    }

    /**
     * Retourne le nom du consommable.
     * @return
     */
    public String getNom(){
        return nom;
    }

    /**
     * Retourne l'identifiant en base de données du consommable.
     */
    public int getId() {
        return id;
    }

    /**
     * Crée une copie de l'objet.
     * @param consommable le consommable à copier
     * @return le nouvel objet
     */
    public static ConsommableFoy copieFoy(ConsommableFoy consommable){
        return new ConsommableFoy(consommable.getId(),consommable.getNom(), consommable.getPrix(), consommable.prixLimite, consommable.getImage()) ;
    }

    /**
     * Obtenir la différence entre le prix limite et le prix du consommable
     * @return la différence.
     */
    public double getDifferencePrixLimite(){
        return getPrixLimite() - getPrix();
    }

    /**
     * la marge est de 60% du prix de vente d'un consommable
     * @return les 60% du prix de vente.
     */
    public double getMarge(){
        return getPrix()*6/10;
    }
}
