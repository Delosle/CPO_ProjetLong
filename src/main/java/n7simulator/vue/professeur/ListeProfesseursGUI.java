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

public abstract class ListeProfesseursGUI extends JPanel implements Observer {
	
	GestionProfesseurs gestionProfesseurs;
	List<Professeur> professeursAffiches;
	
	public ListeProfesseursGUI(GestionProfesseurs gestionProfesseurs) {
		this.gestionProfesseurs = gestionProfesseurs;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Titre de l'onglet
		this.setTitre();

		// Description de l'onglet
		this.setDescription();

	}
	
	protected abstract void setTitre();

	protected abstract void setDescription();
	
	protected abstract void afficherListeProfesseurs();
	
	protected void updateListeProfesseurs() {
		// Supprimer tous les composants actuels
		this.removeAll();
		
		// Recréer l'interface utilisateur
		this.setTitre();
		this.setDescription();
		this.afficherListeProfesseurs();
		
		// Réinitialiser l'affichage
		this.revalidate();
		this.repaint();
	}
	

}
