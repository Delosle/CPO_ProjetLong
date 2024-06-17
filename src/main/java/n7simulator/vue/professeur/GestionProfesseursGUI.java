package n7simulator.vue.professeur;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import n7simulator.modele.Partie;
import n7simulator.modele.professeur.GestionProfesseurs;

/**
 * Classe représentant l'interface de gestion des professeurs Vue comportant 2
 * onglets : professeurs embauchés et non embauchées
 */
public class GestionProfesseursGUI extends JPanel {

	/**
	 * Création de la vue de la gestion des professeurs
	 */
	public GestionProfesseursGUI() {
		this.setLayout(new BorderLayout());

		// Creation d'un pane avec onglets
		JTabbedPane tabbedPane = new JTabbedPane();

		// onglet 1 : professeurs embauches
		GestionProfesseurs gestionProfesseurs = Partie.getInstance().getGestionProfesseurs();
		ProfesseursEmbauchesGUI professeursEGUI = new ProfesseursEmbauchesGUI();
		gestionProfesseurs.addObserver(professeursEGUI);
		tabbedPane.addTab("Embauchés", professeursEGUI);

		// onglet 2 : professeurs non embauches
		ProfesseursNonEmbauchesGUI professeursNEGUI = new ProfesseursNonEmbauchesGUI(gestionProfesseurs);
		gestionProfesseurs.addObserver(professeursNEGUI);
		tabbedPane.addTab("Non embauchés", professeursNEGUI);

		// ajout du JTabbedPane au contenu de la fenêtre
		this.add(tabbedPane, BorderLayout.CENTER);
	}
}
