package n7simulator.vue;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.modele.Partie;
import n7simulator.modele.Temps;

/**
 * Classe representant la vue de la journée en cours de la partie.
 * Affiche sous forme de date.
 */
public class TempsGUI extends JPanel implements Observer {
	
	private JLabel afficheurJourEnCours;
	
	public TempsGUI(Temps partie) {
		String dateAffichage = formatJourneeEnCours(partie.getJourneeEnCours());
		this.afficheurJourEnCours = new JLabel(dateAffichage);
		this.add(afficheurJourEnCours);
	}
	
	
	private String formatJourneeEnCours(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
		return date.format(formatter);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Temps tempsModifie = (Temps) arg;
		this.afficheurJourEnCours.setText(formatJourneeEnCours(tempsModifie.getJourneeEnCours()));
	}
}
