package n7simulator.modele.repas;

import java.util.Observable;
import java.util.Observer;

public class RepasFoy extends Observable {
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
     * le nom de l'image à charger pour le repasFoy
     */
    private String image;

    /**
     * Initialiser un consommable au foy.
     * @param nom le nom du consommable
     * @param prix le prix du consommable
     * @param prixLimite le prixLimite du consommable
     * @param image le nom de l'image pour le repas
     */
    public RepasFoy(String nom, double prix, double prixLimite, String image){
        this.nom = nom;
        this.prix = prix;
        this.prixLimite = prixLimite;
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

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

    public String getImage(){
        return image;
    }

    public String getNom(){
        return nom;
    }
}
