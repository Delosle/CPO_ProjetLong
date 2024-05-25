package n7simulator.controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;

/**
 * 
 */
public class BatimentC extends AbstractBatiment {

	/** On créé le bâtiment C
	 * @param laFrame la fenêtre dans laquelle s'affichera le bâtiment
	 */
	public BatimentC(N7Frame laFrame, JPanel contenu) {
		super(laFrame, contenu);
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 12, 17, 22, 19);
		this.afficherRectangle(laCarte, 19, 13, 22, 16);
		
	}

}
