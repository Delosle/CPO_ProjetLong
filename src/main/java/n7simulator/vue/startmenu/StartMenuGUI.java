package n7simulator.vue.startmenu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import n7simulator.controller.startmenu.ChargerPartieBouton;
import n7simulator.controller.startmenu.NouvellePartieBouton;

/**
 * Classe représentant la vue du menu principal (nouvelle partie - charger une partie)
 */
public class StartMenuGUI extends JPanel {

	/**
	 * Obtenir une vue du menu principal
	 */
	public StartMenuGUI() {
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(300, 200);

		// Ajout du titre
		this.setTitre();

		// Ajout boutons nouvelle partie et charger partie
		JButton nouvellePartieBouton = new NouvellePartieBouton();
		JButton chargerPartieBouton = new ChargerPartieBouton();

		// Changement de la taille du bouton nouvelle partie
		// pour qu'il soit de la même taille que charger partie
		nouvellePartieBouton.setMaximumSize(new Dimension(chargerPartieBouton.getMaximumSize()));

		// ajout des boutons
		this.add(nouvellePartieBouton);
		this.add(Box.createRigidArea(new Dimension(0, 20)));// espacement
		this.add(chargerPartieBouton);

		// Création de la boîte de dialogue
		JOptionPane optionPane = new JOptionPane(this, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				new Object[] {}, null);

		// Crée et montre le JDialog personnalisé
		final JDialog dialog = optionPane.createDialog("N7 Simulator");
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		// Affichage de la boîte de dialogue
		dialog.setVisible(true);
	}

	/**
	 * Titre de la fenetre
	 */
	private void setTitre() {
		JLabel messageLabel = new JLabel("<html><h1><font color=\"#E4BA22\">N7</font> Simulator</h1></html>");
		messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		messageLabel.setBorder(new EmptyBorder(10, 10, 30, 10));
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(messageLabel);
	}
}
