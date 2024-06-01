package n7simulator.controller.startmenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import n7simulator.N7Simulator;
import n7simulator.vue.startmenu.StartMenuGUI;

public class NouvellePartieFormulaire extends JPanel {
	public NouvellePartieFormulaire() {
		this.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

		JLabel messageLabel = new JLabel("Nom de la partie");

		// Saisie du nom de la partie
		JTextField fieldNomPartie = new JTextField(10);

		// Ajouter des composants
		this.add(messageLabel);
		this.add(fieldNomPartie);

		// Afficher la boite de dialogue
		int result = JOptionPane.showConfirmDialog(null, this, "Gestion du contrat",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			N7Simulator.initNouvellePartie();
		} else {
			new StartMenuGUI();
		}
	}
}
