package n7simulator.modele.professeur;

public class Professeur {
	
    private int id;
    private String nom;
    private String prenom;
    private int salaireActuel;
    private int salaireMin;
    private Matiere matiere;
    private String niveau;
    private int nbHeuresTravailles;

    /**
     * Permet de creer un professeur a partir de ses caracteristiques.
     * Par defaut le professeur n'est pas embauché (= début de la partie).
     * @param id
     * @param nom
     * @param prenom
     * @param salaire
     * @param matiere
     * @param niveau
     * @param tauxHoraireMin
     * @param nbHeuresMax
     */
    public Professeur(int id, String nom, String prenom,
    		int salaire, Matiere matiere, String niveau,
    		int tauxHoraireMin) {
    	this.id = id;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.salaireMin = salaire;
    	this.salaireActuel = salaire;
    	this.matiere = matiere;
    	this.niveau = niveau;
    	this.salaireMin = tauxHoraireMin;
    	this.nbHeuresTravailles = 0;
    }
    
    public Professeur() {
    	
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
    	return prenom;
    }
    
    public void setPrenom(String prenom) {
    	this.prenom = prenom;
    }

    public int getSalaireActuel() {
        return salaireActuel;
    }

    public void setSalaireActuel(int salaire) {
        this.salaireActuel = salaire;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public int getSalaireMin() {
    	return this.salaireMin;
    }
    
    public String getNiveau() {
    	return this.niveau;
    }
    
    public int getNbHeuresTravaillees() {
    	return this.nbHeuresTravailles;
    }
    
    public void setNbHeuresTravaillees(int nbHeuresTravailles) {
    	this.nbHeuresTravailles = nbHeuresTravailles;
    }
}
