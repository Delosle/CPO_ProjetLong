package n7simulator.vue.professeur;

import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import n7simulator.controller.professeur.BoutonChoixProfesseur;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant l'affichage de la liste des professeurs non embauchés
 */
public class ProfesseursNonEmbauchesGUI extends ListeProfesseursGUI {

	// la liste des professeurs non embauchés
	List<Professeur> professeursNonEmbauches;

	/**
	 * Creation de la vue des professeurs non embauches
	 * 
	 */
	public ProfesseursNonEmbauchesGUI(GestionProfesseurs gestionProfesseurs) {
		super();
		this.professeursNonEmbauches = gestionProfesseurs.getProfesseursNonEmbauches();
		// Description de l'onglet
		this.setDescription();
		this.afficherListeProfesseurs();
	}

	@Override
	protected void afficherListeProfesseurs() {
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(new JSeparator());
		for (Professeur professeur : this.professeursNonEmbauches) {
			this.add(new ProfesseurGUI(professeur, false));
			this.add(new BoutonChoixProfesseur(professeur));
			this.add(Box.createRigidArea(new Dimension(0, 20)));// espacement
			this.add(new JSeparator());// séparation entre les professeurs
		}
	}

	@Override
	protected void setTitre() {
		JLabel titleLabel = new JLabel("<html><h1>Liste de professeurs à embaucher</h1></html>");
		this.add(titleLabel);
	}

	@Override
	protected void setDescription() {
		JLabel descriptionLabel = new JLabel("<html><p style='width: 600px; text-align: justify;'>"
				+ "Voici la liste des professeurs que vous pouvez embaucher. Lorsque vous embauchez un professeur, vous devez le payer de manière journalière en fonction de son salaire et du nombre d'heure que vous choisissez. Attention, chaque professeur a un salaire minimum imposé. La description de chacun vous donne un indice concernant son impact sur votre école."
				+ "</p></html>");
		this.add(descriptionLabel);

	}

	@Override
	public void update(Observable o, Object arg) {
		this.professeursNonEmbauches = ((GestionProfesseurs) arg).getProfesseursNonEmbauches();
		updateListeProfesseurs();
	}

}
