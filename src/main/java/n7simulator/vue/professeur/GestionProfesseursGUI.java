package n7simulator.vue.professeur;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import n7simulator.db.ProfesseurDAO;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

public class GestionProfesseursGUI extends JPanel {
	
	public GestionProfesseursGUI() {
		this.setLayout(new BorderLayout());

		JTabbedPane tabbedPane = new JTabbedPane();
		//TODO : ici ?
		List<Professeur> professeursNonEmbauches = ProfesseurDAO.getAllProfesseurs();
	        
		List<Professeur>  profsEmbauches = new ArrayList<Professeur>();
		
		GestionProfesseurs gestion = new GestionProfesseurs(profsEmbauches, professeursNonEmbauches);
	    ProfesseursEmbauchesGUI professeursEGUI = new ProfesseursEmbauchesGUI(gestion);
		gestion.addObserver(professeursEGUI);
		tabbedPane.addTab("Embauchés", professeursEGUI);
	        
	   ProfesseursNonEmbauchesGUI professeursNEGUI = new ProfesseursNonEmbauchesGUI(gestion);
	   gestion.addObserver(professeursNEGUI);
	        tabbedPane.addTab("Non embauchés", professeursNEGUI );

	        // Ajouter le JTabbedPane au contenu de la fenêtre
	        this.add(tabbedPane, BorderLayout.CENTER);
	        
	}

}
