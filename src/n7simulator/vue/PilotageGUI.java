package n7simulator.vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.controller.TempsController;

/**
 * 
 */
public class PilotageGUI extends JPanel {

	/** Créer la vue du pilotage
	 * @param interfaceTemps l'interface de gestion du temps
	 */
	public PilotageGUI(TempsGUI interfaceTemps, TempsController controllerTemps) {
		this.setBackground(Color.white);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 1.0;
		contraintes.gridx = 0;
		
		// On ajoute les jauges
		contraintes.weighty = 1.0;
		contraintes.gridy = 0;
		JLabel caseCourante = new JLabel();
		caseCourante.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(caseCourante, contraintes);
		
		// On ajoute l'élément médiant
		contraintes.weighty = 2.0;
		contraintes.gridy = 1;
		caseCourante = new JLabel();
		caseCourante.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(caseCourante, contraintes);
		
		// On ajoute le temps
		contraintes.weighty = 1.0;
		contraintes.gridy = 2;
		JPanel zoneTemps = new JPanel();
		zoneTemps.setLayout(new BoxLayout(zoneTemps, BoxLayout.Y_AXIS));
		zoneTemps.setBorder(BorderFactory.createLineBorder(Color.black));
		zoneTemps.add(interfaceTemps);
		zoneTemps.add(controllerTemps);
		this.add(zoneTemps, contraintes);
		
	}

}
