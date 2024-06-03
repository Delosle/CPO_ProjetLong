/**
 * 
 */
package n7simulator.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import n7simulator.controller.CrousController;
import n7simulator.modele.Crous;

/**
 * 
 */
public class CrousGUI extends JPanel implements Observer {
	
	private Integer qualite;
	
	private Double prixRevente;
	
	private JLabel labelQualite;
	
	private JLabel labelRevente;

	/**
	 * 
	 */
	public CrousGUI() {
		Crous instanceCrous = Crous.getInstance(0, 0.0);
		
		qualite = instanceCrous.getQualite();
		prixRevente = instanceCrous.getPrixVente();

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		// Zone titre
		Box title = Box.createHorizontalBox();
		title.add(new JLabel("CROUS : "));
		title.add(Box.createHorizontalGlue());
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		this.add(title);
		
		// Zone contenu
		JPanel contenu = new JPanel(new GridLayout(1,2));
		
		// Zone informations
		JPanel informations = new JPanel();
		informations.setLayout(new BoxLayout(informations, BoxLayout.Y_AXIS));
		
		// Ligne qualité repas
		Box qualiteRepas = Box.createHorizontalBox();
		qualiteRepas.add(new JLabel("Qualité du repas : "));
		labelQualite = new JLabel(Crous.QUALITE_STR[qualite]);
		qualiteRepas.add(labelQualite);
		qualiteRepas.add(Box.createHorizontalGlue());
		informations.add(qualiteRepas);
		
		// Ligne prix de revente
		Box panelRevente = Box.createHorizontalBox();
		panelRevente.add(new JLabel("Prix de revente du repas : "));
		labelRevente = new JLabel("%.2f".formatted(prixRevente));
		panelRevente.add(labelRevente);
		panelRevente.add(Box.createHorizontalGlue());
		informations.add(panelRevente);
		
		contenu.add(informations, BorderLayout.WEST);
		
		// Zone bouton modification
		contenu.add(CrousController.getBoutonOuverture(), BorderLayout.EAST);

		this.add(contenu);

		instanceCrous.addObserver(this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Crous) {
			Crous c = (Crous)o;
			labelQualite.setText(Crous.QUALITE_STR[c.getQualite()]);
			labelRevente.setText("%.2f".formatted(c.getPrixVente()));
		}
		
	}
	
}
