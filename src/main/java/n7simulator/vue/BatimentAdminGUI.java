package n7simulator.vue;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.database.ConsommableFoyDAO;
import n7simulator.modele.Partie;
import n7simulator.vue.repas.DisplayFoy;

/**
 * Représenter la vue du bâtiment E-F.
 */
public class BatimentAdminGUI extends JPanel {
	
	public BatimentAdminGUI() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() * 0.675;
		
		// Titre
		JLabel titre = new JLabel("<html><body><h1>Batiment E-F</h1></body></html>");
		Dimension d = titre.getPreferredSize();
		d.width = (int)width;
		d.height = 75;
		titre.setMaximumSize(d);
		this.add(titre);
		
		// Vue élève
		ElevesGUI elevesVue = new ElevesGUI();
		Partie.getInstance().addObserver(elevesVue);
		d = elevesVue.getPreferredSize();
		d.width = (int)width;
		d.height = 50;
		elevesVue.setMaximumSize(d);
		this.add(elevesVue);	
		
		CrousGUI crousVue = new CrousGUI();
		d = crousVue.getPreferredSize();
		d.width = (int)width;
		d.height = 50;
		crousVue.setMaximumSize(d);
		this.add(crousVue);

		DisplayFoy display = new DisplayFoy(new ConsommableFoyDAO().getAllConsommableFoy());
		d = display.getPreferredSize();
		d.width = (int)width;
		d.height = 150;
		display.setMaximumSize(d);
		add(display);
	}
	
}