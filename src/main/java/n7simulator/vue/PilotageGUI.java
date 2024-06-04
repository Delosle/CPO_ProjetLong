package n7simulator.vue;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.controller.TempsController;
import n7simulator.vue.Evenement.EventHistoryGUI;
import n7simulator.controller.sauvegarde.SauvegardeController;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.vue.temps.TempsGUI;

/**
 * 
 */
public class PilotageGUI extends JPanel {

	private EventHistoryGUI eventHistoryGUI;

	/** Créer la vue du pilotage
	 * @param interfaceTemps l'interface de gestion du temps
	 */
	public PilotageGUI(TempsGUI interfaceTemps, TempsController controllerTemps, JaugesPannel jaugesGUI, EventHistoryGUI eventHistoryGUI) {
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
		
		// Ajouter jaugesGUI à caseCourante
		caseCourante.setLayout(new BorderLayout());
		caseCourante.add(jaugesGUI, BorderLayout.WEST);
		this.add(caseCourante, contraintes);
		
		// On ajoute l'élément médiant
		contraintes.weighty = 2.0;
		contraintes.gridy = 1;
		//caseCourante = new JLabel();
		//caseCourante.setBorder(BorderFactory.createLineBorder(Color.black));

		// On ajoute EventHistoryGUI a l'element median
		JPanel median = new JPanel();
		median.setLayout(new BorderLayout());
		median.setAlignmentX(Component.CENTER_ALIGNMENT);

		median.add(eventHistoryGUI, BorderLayout.CENTER);
		//eventHistoryGUI.adjustSizeToParent();
		add(median, contraintes);
		this.eventHistoryGUI = eventHistoryGUI;



		
		// On ajoute le temps
		contraintes.weighty = 1.0;
		contraintes.gridy = 2;
		JPanel zoneTemps = new JPanel();
		zoneTemps.setLayout(new BoxLayout(zoneTemps, BoxLayout.Y_AXIS));
		zoneTemps.setBorder(BorderFactory.createLineBorder(Color.black));
		zoneTemps.add(interfaceTemps);
		zoneTemps.add(controllerTemps); 
		//TODO : test
		SauvegardeController test = new SauvegardeController(); 
		zoneTemps.add(test);
		this.add(zoneTemps, contraintes);
		
		
	}

	public void enregistrerEvent(JPanel event) {
		eventHistoryGUI.addEvent(event);

	}

}
