package n7simulator.modele.professeur;

/**
 * Classe représentant une matière pouvant être enseignée par un professeur
 */
public class Matiere {
	// L'id de la matière en base de données
	private int id;

	// Le nom de la matière
	private String nom;

	// L'apport en bonheur de la matière
	private int bonheur;

	// L'apport en pédagogie de la matière
	private int pedagogie;

	/**
	 * Obtenir une matière à partir de son id, nom, apport en bonheur, apport en
	 * pédagogie
	 * 
	 * @param id        : l'id de la matière
	 * @param nom       : le nom de la matière
	 * @param bonheur   : l'apport en bonheur de la matière
	 * @param pedagogie : l'apport en pédagogie de la matière
	 */
	public Matiere(int id, String nom, int bonheur, int pedagogie) {
		this.id = id;
		this.nom = nom;
		this.bonheur = bonheur;
		this.pedagogie = pedagogie;
	}

	/**
	 * Récupérer l'id de la matière
	 * 
	 * @return : l'id de la matière
	 */
	public int getId() {
		return id;
	}

	/**
	 * Récupérer le nom de la matière
	 * 
	 * @return : le nom de la matière
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Récupérer l'apport en bonheur de la matière
	 * 
	 * @return : l'apport en bonheur de la matière
	 */
	public int getBonheur() {
		return bonheur;
	}

	/**
	 * Récupérer l'apport pédagogique de la matière
	 * 
	 * @return : l'apport pédagogique de la matière
	 */
	public int getPedagogie() {
		return pedagogie;
	}

}
