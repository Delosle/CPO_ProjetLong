package n7simulator.vue.professeur;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.Partie;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe générique permettant de visualiser une liste de professeurs La liste
 * est mise à jour en fonction du modèle
 */
public abstract class ListeProfesseursGUI extends JPanel implements Observer {

	// Objet permettant la gestion des professeurs embauchés ou non
	protected GestionProfesseurs gestionProfesseurs;

	// La liste des professeurs à afficher dans la vue
	protected List<Professeur> professeursAffiches;

	/**
	 * Création de la vue générique
	 * 
	 */
	protected ListeProfesseursGUI() {
		this.gestionProfesseurs = Partie.getInstance().getGestionProfesseurs();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Titre de l'onglet
		this.setTitre();
	}

	/**
	 * Affiche le titre de la vue
	 */
	protected abstract void setTitre();

	/**
	 * Affiche la description (explications) de la vue
	 */
	protected abstract void setDescription();

	/**
	 * Affiche la liste des professeurs
	 */
	protected abstract void afficherListeProfesseurs();

	/**
	 * Met à jour la vue lorsque le modèle change
	 */
	protected void updateListeProfesseurs() {
		// supprime tous les composants actuels
		this.removeAll();

		// recrée l'interface utilisateur
		this.setTitre();
		this.setDescription();
		this.afficherListeProfesseurs();

		// réinitialise l'affichage
		this.revalidate();
		this.repaint();
	}

}
