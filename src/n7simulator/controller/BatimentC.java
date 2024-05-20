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
public class BatimentC extends AbstractBatiment {

	/**
	 * 
	 */
	public BatimentC() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 12, 17, 22, 19);
		this.afficherRectangle(laCarte, 19, 13, 22, 16);
		
	}

}
