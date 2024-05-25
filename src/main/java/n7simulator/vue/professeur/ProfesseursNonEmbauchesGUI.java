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

import n7simulator.controller.BoutonChoixProfesseur;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

public class ProfesseursNonEmbauchesGUI extends ListeProfesseursGUI {
	
	List<Professeur> professeursNonEmbauches;
	
	
	// TODO : interface pour titre description
	public ProfesseursNonEmbauchesGUI(GestionProfesseurs gestionProfesseurs) {
		super(gestionProfesseurs);
		this.professeursNonEmbauches = gestionProfesseurs.getProfesseursNonEmbauches();
		this.afficherListeProfesseurs();
        
	}
	
	@Override
	protected void afficherListeProfesseurs() {
		for (Professeur professeur : this.professeursNonEmbauches) {
			this.add(new ProfesseurGUI(professeur, false));
			this.add(new BoutonChoixProfesseur(professeur, gestionProfesseurs));
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			this.add(new JSeparator());
		}
	}

	@Override
	protected void setTitre() {
		JLabel titleLabel = new JLabel("<html><h1>Liste de professeurs à embaucher</h1></html>");
		titleLabel.setBorder(new EmptyBorder(5, 10, 10, 5));
		this.add(titleLabel);
	}

	@Override
	protected void setDescription() {
		JLabel descriptionLabel = new JLabel("<html><p style='text-align: justify;'>" + "TODO" + "</p></html>");
		descriptionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(descriptionLabel);

	}

	@Override
	public void update(Observable o, Object arg) {
		this.professeursNonEmbauches = ((GestionProfesseurs) arg).getProfesseursNonEmbauches();
		updateListeProfesseurs();
		
	}

}
