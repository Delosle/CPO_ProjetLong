package n7simulator.vue;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.modele.Partie;

/**
 * Représenter la vue du bâtiment E-F.
 */
public class BatimentAdminGUI extends JPanel {
	
	public BatimentAdminGUI() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Titre
		JLabel titre = new JLabel("<html><body><h1>Batiment E-F</h1></body></html>");
		this.add(titre);
		
		// Vue élève
		ElevesGUI elevesVue = new ElevesGUI();
		Partie.getInstance().addObserver(elevesVue);
		this.add(elevesVue);	
	}
	
}