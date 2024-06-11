package n7simulator.vue;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BatimentCGUI extends JPanel {
	

	public BatimentCGUI() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() * 0.675;
		
		// Titre
		JLabel titre = new JLabel("<html><body><h1>Batiment C</h1></body></html>");
		Dimension d = titre.getPreferredSize();
		d.width = (int)width;
		d.height = 75;
		titre.setMaximumSize(d);
		this.add(titre);
		
		// Bibliotheque
		BibliothequeGUI biblioVue = new BibliothequeGUI();
		d = biblioVue.getPreferredSize();
		d.width = (int)width;
		d.height = 50;
		biblioVue.setMaximumSize(d);
		this.add(biblioVue);
	}
}
