package n7simulator.modele.professeur;

import java.util.Observable;

/**
 * Classe représentant un professeur
 */
public class Professeur extends Observable {
	// L'id en base de données du professeur
	private int id;

	// Le nom du professeur
	private String nom;

	// Le prénom du professeur
	private String prenom;

	// La description du professeur
	private String description;

	// Le salaire actuel (choisi par le joueur) du professeur
	// unité : €/h
	private int salaireActuel;

	// Le salaire minimum du professeur (salaireActuel >= salaireMin)
	// unité : €/h
	private int salaireMin;

	// La matière enseignée par le professeur
	private Matiere matiere;

	// Le niveau de compétence/d'enseignement du professeur
	// (permet de calculer l'apport pédagogique)
	private String niveau;

	// Le nombre d'heures travaillées par jour par le professeur en fonction du
	// contrat
	// choisi par le joueur
	private int nbHeuresTravailles;

	/**
	 * Permet de creer un professeur a partir de ses caracteristiques. Par defaut le
	 * professeur n'est pas embauché (= début de la partie). Par defaut le
	 * salaireActuel = salaireMin (= début de la partie).
	 * 
	 * @param id          : l'id du professeur en base de données
	 * @param nom         : le nom du professeur
	 * @param prenom      : le prenom du professeur
	 * @param description : la description du professeur
	 * @param matiere     : la matiere enseignée par le professeur
	 * @param niveau      : le niveau du professeur
	 * @param salaireMin  : le salaire minimum imposé par le professeur
	 */
	public Professeur(int id, String nom, String prenom, String description, Matiere matiere, String niveau,
			int salaireMin) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
		this.salaireMin = salaireMin;
		this.salaireActuel = salaireMin;
		this.matiere = matiere;
		this.niveau = niveau;
		this.salaireMin = salaireMin;
		this.nbHeuresTravailles = 0;
	}

	/**
	 * Récupérer l'id du professeur
	 * 
	 * @return : l'id du professeur
	 */
	public int getId() {
		return id;
	}

	/**
	 * Récupérer le nom du professeur
	 * 
	 * @return : le nom du professeur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Récupérer le prénom du professeur
	 * 
	 * @return : le prénom du professeur
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Récupérer la description du professeur
	 * @return : la description du professeur
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Récupérer le salaire actuel (choisi par le joueur) du professeur
	 * 
	 * @return : le salaire actuel du professeur
	 */
	public int getSalaireActuel() {
		return salaireActuel;
	}

	/**
	 * Modifie le salaire actuel du professeur choisi par le joueur
	 * 
	 * @param nouveauSalaire : le nouveau salaire du professeur
	 */
	public void setSalaireActuel(int nouveauSalaire) {
		this.salaireActuel = nouveauSalaire;
	}

	/**
	 * Récupérer la matière enseignée par le professeur
	 * 
	 * @return : la matière enseignée par le professeur
	 */
	public Matiere getMatiere() {
		return matiere;
	}

	/**
	 * Récupérer le salaire minimum imposé par le professeur
	 * 
	 * @return : le salaire minimum
	 */
	public int getSalaireMin() {
		return this.salaireMin;
	}

	/**
	 * Récupérer le niveau du professeur
	 * 
	 * @return : le niveau du professeur
	 */
	public String getNiveau() {
		return this.niveau;
	}

	/**
	 * Récupérer le nombre d'heures travaillées par jour du professeur
	 * 
	 * @return : le nombre d'heures travaillées par jour du professeur
	 */
	public int getNbHeuresTravaillees() {
		return this.nbHeuresTravailles;
	}

	/**
	 * Modifie le nombre d'heures travaillees par le professeur
	 * 
	 * @param nouveauNbHeuresTravaillees : le nouveau nombre d'heures travaillees
	 */
	public void setNbHeuresTravaillees(int nouveauNbHeuresTravaillees) {
		this.nbHeuresTravailles = nouveauNbHeuresTravaillees;
	}

	/**
	 * Modifier le contrat du professeur (notifie les observateurs du changement)
	 * 
	 * @param nouveauSalaire  : le salaire choisi par le joueur
	 * @param nouveauNbHeures : le nombre d'heures travaillées par le professeur
	 *                        choisir par le joueur
	 */
	public void modifierContrat(int nouveauSalaire, int nouveauNbHeures) {
		this.setNbHeuresTravaillees(nouveauNbHeures);
		this.setSalaireActuel(nouveauSalaire);
		this.setChanged();
		this.notifyObservers(this);
	}
}
