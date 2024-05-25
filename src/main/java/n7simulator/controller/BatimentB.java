package n7simulator.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import n7simulator.modele.professeur.Professeur;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;
import n7simulator.vue.professeur.ProfesseursEmbauchesGUI;

/**
 * 
 */
public class BatimentB extends AbstractBatiment {

	/** On créé le bâtiment B
	 * @param laFrame la fenêtre dans laquelle s'affichera le bâtiment
	 */
	public BatimentB(N7Frame laFrame, JPanel contenu) {
		super(laFrame, contenu);
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 1, 3, 5, 11);
		
	}

}
