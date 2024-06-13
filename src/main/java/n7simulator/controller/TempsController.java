package n7simulator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import n7simulator.joursuivant.JourSuivant;
import n7simulator.modele.Partie;
import n7simulator.modele.jauges.ValeurNulleException;
import n7simulator.vue.GameOverFrame;
import n7simulator.vue.PilotageGUI;

/**
 * Controller permettant de modifier la date de la journée en cours. Contient un
 * bouton "Jour Suivant" qui applique les impacts des prises de décisions et génère
 * des évènements.
 */
public class TempsController extends JPanel {

	public TempsController() {
		JButton jourSuivantButton = new JButton("Jour Suivant");
		jourSuivantButton.addActionListener(new ActionJourSuivant());
		this.add(jourSuivantButton);
	}

	private class ActionJourSuivant implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JourSuivant.getInstance().effectuerImpactsJourSuivant();
				Partie.getInstance().genererEvenementIrregulier((PilotageGUI) getParent().getParent());
				Partie.getInstance().genererEvenementRegulier((PilotageGUI) getParent().getParent());
			} catch (ValeurNulleException vne) {
				Partie.setPerdue();
				new GameOverFrame(vne.getJaugeDeclenchement());
			}
		}
	}
}
