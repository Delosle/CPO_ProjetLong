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
public class BatimentA extends AbstractBatiment {

	/** On créé le bâtiment A
	 * @param laFrame la fenêtre dans laquelle s'affichera le bâtiment
	 */
	public BatimentA(N7Frame laFrame) {
		super(laFrame);
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 1, 13, 5, 19);
		this.afficherRectangle(laCarte, 6, 17, 8, 19);
		
	}

}
