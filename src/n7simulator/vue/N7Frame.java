package n7simulator.vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * 
 */
public class N7Frame extends JFrame {

	private CarteGUI interfaceCarte;
	
	private PilotageGUI interfacePilotage;
	
	private JLayeredPane layeredPanel;
	
	/**
	 * 
	 */
	public N7Frame(CarteGUI interfaceCarte, PilotageGUI interfacePilotage){
		// On créé la fenêtre globale
		super("N7Simulator");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize((int)width, (int)height);
		
		// On créé les layers sur lesquels vont venir se placer nos éléments
		layeredPanel = new JLayeredPane();
		layeredPanel.setSize(this.getWidth(), this.getHeight());
		this.add(layeredPanel);
		
		// On créé la grille du fond, qui viendra contenir notre carte
		JPanel grilleFond = new JPanel(new GridBagLayout());
		grilleFond.setSize(this.getWidth(), this.getHeight());
		
		// On définit les contraintes pour la carte
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.gridx = 0;
		contraintes.weightx = 3.0;
		contraintes.weighty = 1.0;
		
		// On définit la taille de la carte et on l'ajoute
		Integer carteLargeur = this.getWidth() * 3 / 4;
		interfaceCarte.setSize(carteLargeur, this.getHeight());
		this.interfaceCarte = interfaceCarte;
		grilleFond.add(interfaceCarte, contraintes);
		
		// On déinit les contraintes pour le pilotage et on l'ajoute
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.gridx = 1;
		contraintes.weightx = 1.0;
		contraintes.weighty = 1.0;
		this.interfacePilotage = interfacePilotage;
		grilleFond.add(interfacePilotage, contraintes);
		
		// On affiche les éléments
		layeredPanel.add(grilleFond, JLayeredPane.DEFAULT_LAYER);
		this.setVisible(true);
	}

	/** Ajouter un élément au JLayeredPane
	 * @param element L'élément à placer dans un layer
	 * @param numLayer Le numéro du layer
	 */
	public void ajouterLayer(Component element, Integer numLayer) {
		layeredPanel.add(element, numLayer);
	}
}
