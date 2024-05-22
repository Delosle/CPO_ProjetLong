package n7simulator.controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.vue.CarteGUI;

/**
 * 
 */
public class BatimentA extends AbstractBatiment {

	/**
	 * 
	 */
	public BatimentA() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 1, 13, 5, 19);
		this.afficherRectangle(laCarte, 6, 17, 8, 19);
		
	}

}
