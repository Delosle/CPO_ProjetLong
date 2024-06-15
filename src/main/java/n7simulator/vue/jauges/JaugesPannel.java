package n7simulator.vue.jauges;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import n7simulator.modele.Partie;
import n7simulator.modele.jauges.Jauge;

/**
 * Classe representant la vue contenant toutes les jauges
 */
public class JaugesPannel extends JPanel {
	private static final int LARGEUR_PANNEL = 500;
	private static final int HAUTEUR_PANNEL = 240;

	/**
	 * La vue sur l'argent
	 */
	private ArgentGUI vueArgent;
	/**
	 * La vue sur le bonheur
	 */
	private JaugeBorneeGUI vueBonheur;
	/**
	 * La vue sur la pédagogie
	 */
	private JaugeBorneeGUI vuePedagogie;

	public JaugesPannel() {
		creerJauges();

		setLayout(new GridLayout(3, 1));
		setPreferredSize(new Dimension(LARGEUR_PANNEL, HAUTEUR_PANNEL));

		add(vueBonheur);
		add(vuePedagogie);
		add(vueArgent);
	}

	/**
	 * Cree les jauges appartenant a la vue
	 */
	private void creerJauges() {
		Partie partie = Partie.getInstance();
		Jauge argent = partie.getJaugeArgent();
		Jauge bonheur = partie.getJaugeBonheur();
		Jauge pedagogie = partie.getJaugePedagogie();

		vueArgent = new ArgentGUI("Argent", argent.getValue());
		vueBonheur = new JaugeBorneeGUI("Bonheur", bonheur.getValue(), new Color(212, 0, 253));
		vuePedagogie = new JaugeBorneeGUI("Pedagogie", pedagogie.getValue(), Color.BLUE);
		argent.addObserver(vueArgent);
		bonheur.addObserver(vueBonheur);
		pedagogie.addObserver(vuePedagogie);
	}

}