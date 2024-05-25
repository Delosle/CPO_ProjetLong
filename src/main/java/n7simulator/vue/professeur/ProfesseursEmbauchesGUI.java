package n7simulator.vue.professeur;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

public class ProfesseursEmbauchesGUI extends ListeProfesseursGUI{
	
	List<Professeur> professeursEmbauches;

	public ProfesseursEmbauchesGUI(GestionProfesseurs gestionProfesseurs) {
		super(gestionProfesseurs);
		this.professeursEmbauches = gestionProfesseurs.getProfesseursEmbauches();        
        this.afficherListeProfesseurs();
		
	}
	
	@Override
	protected void afficherListeProfesseurs() {
		for(Professeur prof : professeursEmbauches) {
			this.add(new ProfesseurGUI(prof, true));
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			this.add(new JSeparator());
		}
	}
	
	@Override
	protected void setTitre() {
		JLabel titleLabel = new JLabel("<html><h1>Professeurs embauchés</h1></html>");
        titleLabel.setBorder(new EmptyBorder(5, 10, 10, 5));
		this.add(titleLabel);
	}
	
	@Override
	protected void setDescription() {
		JLabel descriptionLabel = new JLabel("<html><p style='text-align: justify;'>"+
				"Voici la liste des professeurs que vous avez choisi d'embaucher. Vous pouvez gérer ici leur salaire et le nombre d'heures travaillé."
				+ "</p></html>");
		descriptionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(descriptionLabel);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.professeursEmbauches = ((GestionProfesseurs) arg).getProfesseursEmbauches();
		this.updateListeProfesseurs();
		
	}

}
