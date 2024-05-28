package n7simulator.vue.professeur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import n7simulator.controller.professeur.BoutonLicencierProfesseur;
import n7simulator.controller.professeur.BoutonModifierContratProfesseur;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant l'affichage de la liste des professeurs embauchés
 */
public class ProfesseursEmbauchesGUI extends ListeProfesseursGUI {

	// la liste des professeurs embauchés
	List<Professeur> professeursEmbauches = null;

	/**
	 * Creation de la vue des professeurs embauches
	 * 
	 * @param gestionProfesseurs : l'objet permettant la gestion de tous les
	 *                           professeurs
	 */
	public ProfesseursEmbauchesGUI(GestionProfesseurs gestionProfesseurs) {
		super(gestionProfesseurs);
		this.professeursEmbauches = gestionProfesseurs.getProfesseursEmbauches();
		this.afficherListeProfesseurs();

	}

	@Override
	protected void afficherListeProfesseurs() {
		this.add(Box.createRigidArea(new Dimension(0, 20))); // espacement
		this.add(new JSeparator()); // séparateur entre les profs
		for (Professeur prof : professeursEmbauches) {
			ProfesseurGUI professeurGUI = new ProfesseurGUI(prof, true);
			prof.addObserver(professeurGUI);
			this.add(professeurGUI);

			this.add(new BoutonModifierContratProfesseur(prof, gestionProfesseurs));
			this.add(Box.createRigidArea(new Dimension(0, 10))); // espacement
			this.add(new BoutonLicencierProfesseur(prof, gestionProfesseurs));
			this.add(Box.createRigidArea(new Dimension(0, 20))); // espacement
			this.add(new JSeparator()); // séparateur entre les profs
		}
	}

	@Override
	protected void setTitre() {
		JLabel titleLabel = new JLabel("<html><h1>Professeurs embauchés</h1></html>");
		this.add(titleLabel);
	}

	@Override
	protected void setDescription() {
		JLabel descriptionLabel = new JLabel("<html><p style='width: 600px; text-align: justify;'>"
				+ "Voici la liste des professeurs que vous avez choisi d'embaucher. Vous pouvez gérer ici leur salaire et le nombre d'heures travaillées."
				+ "<br>Attention, si vous augmentez trop le nombre d'heures, la jauge de bonheur diminuera.</p></html>");
		this.add(descriptionLabel);
		this.add(Box.createRigidArea(new Dimension(0, 20))); // espacement

		// affichage du nombre de professeurs embauches
		String nombreProfesseurs;
		if (professeursEmbauches != null) {
			nombreProfesseurs = "Nombre total de professeurs embauchés : " + professeursEmbauches.size();
		} else {
			nombreProfesseurs = "Nombre total de professeurs embauchés : 0";
		}

		this.add(new JLabel(nombreProfesseurs));
	}

	@Override
	public void update(Observable o, Object arg) {
		this.professeursEmbauches = ((GestionProfesseurs) arg).getProfesseursEmbauches();
		this.updateListeProfesseurs();

	}

}
