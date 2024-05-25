package n7simulator.modele.professeur;

public class Matiere {
	private int id;
    private String nom;
    private int bonheur;
    private int pedagogie;

    public Matiere(int id, String nom, int bonheur, int pedagogie) {
        this.id = id;
        this.nom = nom;
        this.bonheur = bonheur;
        this.pedagogie = pedagogie;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getBonheur() {
        return bonheur;
    }

    public int getPedagogie() {
        return pedagogie;
    }

}

