package n7simulator.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.controller.GestionBibliothequeController;
import n7simulator.modele.Bibliotheque;

/**
 * Vue bibliothèque
 */
public class BibliothequeGUI extends JPanel implements Observer {

	/**
	 * Nombre de livres de la bibliothèque
	 */
	private Integer nbLivre;
	
	/**
	 * Label contenant le nombre de livres
	 */
	private JLabel labelLivre;

	/**
	 * Création de la vue de la Bibliotheque
	 */
	public BibliothequeGUI() {
		Bibliotheque instanceBiblio = Bibliotheque.getInstance();
		nbLivre = instanceBiblio.getNbLivre();

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		// Zone titre
		Box title = Box.createHorizontalBox();
		title.add(new JLabel("Bibliothèque : "));
		title.add(Box.createHorizontalGlue());
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		this.add(title);

		// Zone contenu
		JPanel contenu = new JPanel(new GridLayout(1,2));

		// Zone informations
		JPanel informations = new JPanel();
		informations.setLayout(new BoxLayout(informations, BoxLayout.Y_AXIS));

		// Ligne nombre de livre déjà acheté
		Box nbLivreAcquis = Box.createHorizontalBox();
		nbLivreAcquis.add(new JLabel("Nombre de livre dans la bibliothèque : "));
		labelLivre = new JLabel(String.valueOf(nbLivre));
		nbLivreAcquis.add(labelLivre);
		nbLivreAcquis.add(Box.createHorizontalGlue());
		informations.add(nbLivreAcquis);

		contenu.add(informations, BorderLayout.WEST);

		// Zone bouton modification
		contenu.add(GestionBibliothequeController.getBoutonOuverture(), BorderLayout.EAST);

		this.add(contenu);

		instanceBiblio.addObserver(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bibliotheque) {
			Bibliotheque biblio = (Bibliotheque) o;
			// Récupération de la nouvelle valeur du nombre de livres
	        int newNbLivre = biblio.getNbLivre();
	        // Mise à jour avec la nouvelle valeur
	        labelLivre.setText(String.valueOf(newNbLivre));
		}
	}
}
