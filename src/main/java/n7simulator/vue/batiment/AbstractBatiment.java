package n7simulator.vue.batiment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;

/**
 * 
 */
public abstract class AbstractBatiment extends JPanel{

	protected JPanel contenuBatiment; // Le JPanel dans lequel on place les actions
	
	protected JButton boutonFermer; // Le bouton qui annule les actions
	
	protected double width; // La largeur du bâtiment
	
	/** On créé un bâtiment abstrait
	 *
	 */
	public AbstractBatiment(String nom, CarteGUI laCarte) {
		
		// On récupère les dimensions de l'écran
		double largeur = N7Frame.getScreenWidth();
		double hauteur = N7Frame.getScreenHeight();
		
		// On définie les dimensions de la fenêtre d'action du bâtiment
		this.width = largeur * 0.675;
		this.setSize((int)width, (int)hauteur);
		
		contenuBatiment = new JPanel();
		
		// On fait en sorte que l'on puisse scroller pour voir toutes les actions sur le bâtiment
		JScrollPane scrollable = new JScrollPane(contenuBatiment);
		scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Désactiver le défilement horizontal
		scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Activer le défilement vertical
		
		contenuBatiment.setAutoscrolls(true);
		
		Integer batimentHauteur = (int)hauteur * 8 / 10;
		scrollable.setPreferredSize(new Dimension((int)width, batimentHauteur));
		this.add(scrollable);
		
		contenuBatiment.setLayout(new BoxLayout(contenuBatiment, BoxLayout.Y_AXIS));
		
		// Titre
		JLabel titre = new JLabel("<html><body><h1>Batiment " + nom + "</h1></body></html>");
		N7Frame.definirTaille(titre, (int)width, 75);
		contenuBatiment.add(titre);
		
		// On créé la zone pour les boutons annuler et valider
		FlowLayout layoutBoutons = new FlowLayout(FlowLayout.CENTER, 50, 0);
		JPanel zoneBoutons = new JPanel(layoutBoutons);
		zoneBoutons.setSize((int)width, (int)hauteur - 2 * batimentHauteur);
		
		// On créé les boutons
		boutonFermer = new JButton("FERMER");
		boutonFermer.setBackground(Color.darkGray);
		boutonFermer.setForeground(Color.white);
		
		// On ajoute les boutons à la zone et on leur ajoute la fermeture de la fenetre d'action
		AbstractBatiment temp = this;
		zoneBoutons.add(boutonFermer);
		boutonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				N7Frame.getInstance().retirerLayer(temp);
			}
		});
		this.add(zoneBoutons);
		
		this.afficherSurCarte(laCarte);
	}
	
	/** Afficher sur la vue de la carte le bâtiment courant
	 * @param laCarte la carte sur laquelle afficher le bâtiment
	 */
	public abstract void afficherSurCarte(CarteGUI laCarte);
	
	/** Afficher sur la vue de la carte un rectangle par les positions des cases
	 * @param laCarte la carte sur laquelle on affiche le rectangle
	 * @param x1 La colonne du point supérieur gauche
	 * @param y1 La ligne du point supérieur gauche
	 * @param x2 La colonne du point inférieur droit
	 * @param y2 La ligne du point inférieur droit
	 */
	protected void afficherRectangle(CarteGUI laCarte, Integer x1, Integer y1, Integer x2, Integer y2) {
		// On définit les contraintes générales
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 1.0;
		contraintes.weighty = 1.0;
		
		// On créé l'action qui permet d'ouvrir la fenêtre d'action du bâtiment
		AbstractBatiment temp = this;
		ActionListener afficherBatiment = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				N7Frame.getInstance().ajouterLayer(temp, JLayeredPane.PALETTE_LAYER);
			}
		};
		
		// On créé et place chaque case de chaque ligne et colonne appartenant au rectangle
		JButton caseCourante;
		for (int x = x1; x <= x2; x ++) {
			contraintes.gridx = x;
			for (int y = y1; y <= y2; y ++) {
				contraintes.gridy = y;
				
				// On créé la case et on l'ajoute selon les contraintes
				caseCourante  = new JButton();
				
				// Supprimez les bordures et les marges
				caseCourante.setBorder(BorderFactory.createEmptyBorder());
				caseCourante.setMargin(new Insets(0, 0, 0, 0));

		        // Supprimez le remplissage et l'arrière-plan
				caseCourante.setBackground(Color.black);
				
				// On ajoute l'ouverture de la fenetre d'action
				caseCourante.addActionListener(afficherBatiment);
				
				laCarte.add(caseCourante, contraintes);
			}
		}
	}

}
