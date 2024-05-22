package n7simulator.controller;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import n7simulator.vue.CarteGUI;

/**
 * 
 */
public abstract class AbstractBatiment implements InterfaceBatiment {

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
		
		// On créé et place chaque case de chaque ligne et colonne appartenant au rectangle
		JLabel caseCourante;
		for (int x = x1; x <= x2; x ++) {
			contraintes.gridx = x;
			for (int y = y1; y <= y2; y ++) {
				contraintes.gridy = y;
				
				// On créé la case et on l'ajoute selon les contraintes
				caseCourante  = new JLabel();
				caseCourante.setOpaque(true);
				caseCourante.setBackground(Color.black);
				laCarte.add(caseCourante, contraintes);
			}
		}
	}

}
