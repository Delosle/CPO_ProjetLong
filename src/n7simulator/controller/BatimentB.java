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
public class BatimentB extends AbstractBatiment {

	/**
	 * 
	 */
	public BatimentB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 1, 3, 5, 11);
		
	}

}
