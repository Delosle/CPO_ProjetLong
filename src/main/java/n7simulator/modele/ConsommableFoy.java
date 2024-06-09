package n7simulator.modele;

import java.util.Observable;

public class ConsommableFoy extends Observable {
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
    public ConsommableFoy(String nom, double prix, double prixLimite, String image){
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

    public static ConsommableFoy copieFoy(ConsommableFoy foy){
        return new ConsommableFoy(foy.getNom(), foy.getPrix(), foy.prixLimite, foy.getImage()) ;
    }

    /**
     * Obteinr la différence entre le prix limite et le prix du consommable
     * @return la différence.
     */
    public double getDiff(){
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
