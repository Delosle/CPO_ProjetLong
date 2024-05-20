package n7simulator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import n7simulator.modele.Partie;

/**
 * Controller permettant de modifier la date de la journée en cours.
 * Contient un bouton "Jour Suivant".
 */
public class TempsController extends JPanel {
	
	Partie partieEnCours;

	public TempsController(Partie partie) {
		this.partieEnCours = partie;
		JButton jourSuivantButton = new JButton("Jour Suivant");
		jourSuivantButton.addActionListener(new ActionJourSuivant());
		this.add(jourSuivantButton);
	}
	
	private class ActionJourSuivant implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partieEnCours.incrementJournee();
		}
	}
}
