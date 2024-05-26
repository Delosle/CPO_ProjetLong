package n7simulator.vue;

import n7simulator.vue.jauges.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.vue.jauges.JaugesPannel;

/**
 * 
 */
public class PilotageGUI extends JPanel {

	/**
	 * 
	 */
	public PilotageGUI() {
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
		JaugesPannel jauges = new JaugesPannel(10, 5, 0);
		caseCourante.add(jauges);
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
		caseCourante = new JLabel();
		caseCourante.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(caseCourante, contraintes);
		
	}

}
