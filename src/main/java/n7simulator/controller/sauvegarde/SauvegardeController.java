package n7simulator.controller.sauvegarde;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import n7simulator.N7Simulator;

public class SauvegardeController extends JPanel {
	public SauvegardeController() {
		JButton boutonSauvegarde = new JButton("Sauvegarder");
		boutonSauvegarde.addActionListener(new ActionSauvegarder());
		this.add(boutonSauvegarde);
	}
	
	
	class ActionSauvegarder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			N7Simulator.sauvegarderPartie();
			JOptionPane.showMessageDialog(null,
					"La partie a bien été sauvegardée", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}
