package n7simulator.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;

/**
 * 
 */
public abstract class AbstractBatiment extends JPanel implements InterfaceBatiment {

	protected JPanel contenuBatiment; // Le JPanel dans lequel on place les actions
	
	protected JButton boutonAnnuler; // Le bouton qui annule les actions
	
	protected JButton boutonValider; // Le bouton qui valide les actions
	
	private N7Frame laFrame; // la fenêtre générale que l'on récupère pour afficher la vue du bâtiment
	
	/** On créé un bâtiment abstrait
	 * @param laFrame la fenêtre générale que l'on récupère pour afficher la vue du bâtiment
	 */
	public AbstractBatiment(N7Frame laFrame, JPanel contenu) {
		// On récupère la frame
		this.laFrame = laFrame;
		
		// On récupère les dimensions de l'écran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		// On définie les dimensions de la fenêtre d'action du bâtiment
		double batimentLargeur = width * 0.675;
		this.setSize((int)batimentLargeur, (int)height);
		
		// On créé le JPanel qui contient les actions (rempli dans les sous-classes)
		contenuBatiment = contenu;
		
		// On fait en sorte que l'on puisse scroller pour voir toutes les actions sur le bâtiment
		JScrollPane scrollable = new JScrollPane(contenuBatiment);
		contenuBatiment.setAutoscrolls(true);
		Integer batimentHauteur = (int)height * 8 / 10;
		scrollable.setPreferredSize(new Dimension((int)batimentLargeur, batimentHauteur));
		this.add(scrollable);
		
		// On créé la zone pour les boutons annuler et valider
		FlowLayout layoutBoutons = new FlowLayout(FlowLayout.CENTER, 50, 0);
		JPanel zoneBoutons = new JPanel(layoutBoutons);
		zoneBoutons.setSize((int)width, (int)height - 2 * batimentHauteur);
		
		// On créé les boutons
		boutonAnnuler = new JButton("ANNULER");
		boutonAnnuler.setBackground(Color.red);
		boutonAnnuler.setForeground(Color.white);
		
		boutonValider = new JButton("VALIDER");
		boutonValider.setBackground(Color.green);
		boutonValider.setForeground(Color.white);
		
		// On ajoute les boutons à la zone et on leur ajoute la fermeture de la fenetre d'action
		zoneBoutons.add(boutonAnnuler);
		zoneBoutons.add(boutonValider);
		ajouterFermetureBouton(boutonAnnuler);
		ajouterFermetureBouton(boutonValider);
		this.add(zoneBoutons);
	}
	
	/** Ajouter à un bouton la fermeture de la fenetre lorsque l'utilisateur appuie
	 * @param bouton
	 */
	protected void ajouterFermetureBouton(JButton bouton) {
		AbstractBatiment temp = this;
		ActionListener cacherBatiment = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				laFrame.retirerLayer(temp);
			}
		};
		bouton.addActionListener(cacherBatiment);
	}
	
	@Override
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
				laFrame.ajouterLayer(temp, JLayeredPane.PALETTE_LAYER);
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
