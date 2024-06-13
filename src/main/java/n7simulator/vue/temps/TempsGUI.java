package n7simulator.vue.temps;

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
	
	//label de la date affichee
	private JLabel afficheurJourEnCours;
	
	/**
	 * Obtenir la vue du temps (de la date en cours)
	 * @param temps
	 */
	public TempsGUI() {
		Temps modeleTemps = Partie.getInstance().getTemps();
		String dateAffichage = formatJourneeEnCours(modeleTemps.getJourneeEnCours());
		this.afficheurJourEnCours = new JLabel(dateAffichage);
		this.add(afficheurJourEnCours);
		modeleTemps.addObserver(this);
	}
	
	/**
	 * Formatte la journee en cours sous la forme jour mois annee
	 * @param date : la date qu'il faut formatter
	 * @return : la date formattee
	 */
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
