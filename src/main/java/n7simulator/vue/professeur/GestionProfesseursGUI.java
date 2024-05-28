package n7simulator.vue.professeur;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import n7simulator.database.ProfesseurDAO;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant l'interface de gestion des professeurs
 * Vue comportant 2 onglets : professeurs embauchés et non embauchées
 */
public class GestionProfesseursGUI extends JPanel {

	/**
	 * Création de la vue de la gestion des professeurs
	 */
	public GestionProfesseursGUI() {
		this.setLayout(new BorderLayout());

		// recuperation des professeurs en BD a l'initialisation
		GestionProfesseurs gestionProfesseurs = this.recuperationProfesseursBD();

		// Creation d'un pane avec onglets
		JTabbedPane tabbedPane = new JTabbedPane();

		// onglet 1 : professeurs embauches
		ProfesseursEmbauchesGUI professeursEGUI = new ProfesseursEmbauchesGUI(gestionProfesseurs);
		gestionProfesseurs.addObserver(professeursEGUI);
		tabbedPane.addTab("Embauchés", professeursEGUI);

		// onglet 2 : professeurs non embauches
		ProfesseursNonEmbauchesGUI professeursNEGUI = new ProfesseursNonEmbauchesGUI(gestionProfesseurs);
		gestionProfesseurs.addObserver(professeursNEGUI);
		tabbedPane.addTab("Non embauchés", professeursNEGUI);

		// ajout du JTabbedPane au contenu de la fenêtre
		this.add(tabbedPane, BorderLayout.CENTER);

	}

	/**
	 * Récupère la liste des professeurs depuis la base de données
	 * @return : la gestion des professeurs
	 */
	private GestionProfesseurs recuperationProfesseursBD() {
		List<Professeur> professeursNonEmbauches = ProfesseurDAO.getAllProfesseurs();
		List<Professeur> profsEmbauches = new ArrayList<>();
		return new GestionProfesseurs(profsEmbauches, professeursNonEmbauches);
	}

}
