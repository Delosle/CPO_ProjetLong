package n7simulator.vue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.modele.Partie;

/**
 * Classe représentant la vue du nombre d'élèves. 
 */
public class ElevesGUI extends JPanel implements Observer {
	
	private JLabel afficheurNombreEleves;
	
	/**
	 * Constructeur.
	 */
	public ElevesGUI() {
		afficheurNombreEleves = new JLabel(formatNombreEleves(Partie.getInstance().getNombreEleves()));
		this.add(afficheurNombreEleves);
	}
	
	private String formatNombreEleves(int nbEleves) {
		return "Nombres d'élèves inscrits : " + nbEleves;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Partie partieModifiee = (Partie) arg;
		afficheurNombreEleves.setText(formatNombreEleves(partieModifiee.getNombreEleves()));
	}	
}