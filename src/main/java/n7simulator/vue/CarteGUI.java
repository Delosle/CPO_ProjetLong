package n7simulator.vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.controller.*;
import n7simulator.modele.Partie;

/**
 * 
 */
public class CarteGUI extends JPanel {

	private BatimentA batimentA;
	
	private BatimentB batimentB;
	
	private BatimentC batimentC;
	
	private BatimentAdmin batimentAdmin;
	
	/**
	 * 
	 */
	public CarteGUI() {
		// On délimite précisément l'emplacement de la carte
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.white);
		
		// On sépare la carte en une grille
		this.setLayout(new GridBagLayout());
		
		// On créé les contraintes des éléments de la grille
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 1.0;
		contraintes.weighty = 1.0;

		// On créé les colonnes et les lignes de la grille
		JLabel caseCourante;
		for(int i = 0; i < 24; i ++) {
			caseCourante = new JLabel();
			caseCourante.setOpaque(true);
			caseCourante.setBackground(Color.white);
			contraintes.gridx = i;
			contraintes.gridy = 0;
			this.add(caseCourante, contraintes);
			if (i < 22) {
				caseCourante = new JLabel();
				caseCourante.setOpaque(true);
				caseCourante.setBackground(Color.white);
				contraintes.gridx = 0;
				contraintes.gridy = i;
				this.add(caseCourante, contraintes);	

			}
		}

	}

	/** On créé les bâtiments dans la fenêtre
	 * @param laFrame la fenêtre dans laquelle on place les bâtiments
	 */
	public void creerBatiments(N7Frame laFrame) {
		// Batiment A
		batimentA = new BatimentA(laFrame, new JPanel());
		batimentA.afficherSurCarte(this);
		
		// Batiment B
		batimentB = new BatimentB(laFrame, new JPanel());
		batimentB.afficherSurCarte(this);
		
		// Batiment C
		batimentC = new BatimentC(laFrame, new JPanel());
		batimentC.afficherSurCarte(this);
		
		// Batiment Admin
		batimentAdmin = new BatimentAdmin(laFrame, new BatimentAdminGUI());
		batimentAdmin.afficherSurCarte(this);
	}

}
