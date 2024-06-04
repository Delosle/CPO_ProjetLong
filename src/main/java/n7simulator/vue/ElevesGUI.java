package n7simulator.vue;

import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.modele.GestionEleves;
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
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		afficheurNombreEleves = new JLabel(
				formatNombreEleves(Partie.getInstance().getGestionEleves().getNombreEleves()));
		this.add(afficheurNombreEleves);
		Partie.getInstance().getGestionEleves().addObserver(this);
	}

	private String formatNombreEleves(int nbEleves) {
		return "Nombres d'élèves inscrits : " + nbEleves;
	}

	@Override
	public void update(Observable o, Object arg) {
		GestionEleves gestElv = (GestionEleves) arg;
		afficheurNombreEleves.setText(formatNombreEleves(gestElv.getNombreEleves()));
	}
}