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
public class BatimentAdmin extends AbstractBatiment {

	/**
	 * 
	 */
	public BatimentAdmin() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 8, 1, 21, 5);
		
	}

}
