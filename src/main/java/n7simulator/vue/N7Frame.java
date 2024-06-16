package n7simulator.vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import n7simulator.N7Simulator;

/**
 * 
 */
public class N7Frame extends JFrame {
	
	private static N7Frame instance;

	private static CarteGUI interfaceCarte = null;
	
	private static PilotageGUI interfacePilotage = null;
	
	private static Dimension screenSize;
	
	private JLayeredPane layeredPanel;
	
	/**
	 * 
	 */
	private N7Frame(){
		// On créé la fenêtre globale
		super("N7Simulator");
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                N7Simulator.sauvegarderPartie();
                dispose();  
            }
        });
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize((int)N7Frame.getScreenWidth(), (int)N7Frame.getScreenHeight());
		
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
		N7Frame.interfaceCarte.setSize(carteLargeur, this.getHeight());
		grilleFond.add(N7Frame.interfaceCarte, contraintes);
		
		// On déinit les contraintes pour le pilotage et on l'ajoute
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.gridx = 1;
		contraintes.weightx = 1.0;
		contraintes.weighty = 1.0;
		grilleFond.add(N7Frame.interfacePilotage, contraintes);
		
		// On affiche les éléments
		layeredPanel.add(grilleFond, JLayeredPane.DEFAULT_LAYER);
		this.setVisible(true);
	}

	public static N7Frame getInstance() {
		if (instance == null) {
			assert(N7Frame.interfaceCarte != null);
			assert(N7Frame.interfacePilotage != null);
			instance = new N7Frame();
		}
		return instance;
	}
	
	/** Préparer la création de l'instance de N7Frame
	 * @param laCarte
	 * @param lePilotage
	 */
	public static void setUp(CarteGUI laCarte, PilotageGUI lePilotage) {
		assert(N7Frame.interfaceCarte == null && N7Frame.interfacePilotage == null);
		assert(laCarte != null && lePilotage != null);
		N7Frame.interfaceCarte = laCarte;
		N7Frame.interfacePilotage = lePilotage;
	}
	
	/** Définir la taille d'un élément en fonction de sa largeur et sa hauteur
	 * @param element l'élément dont on défini la taille
	 * @param width la largeur souhaitée de l'élément
	 * @param height la hauteur souhaitée de l'élément
	 */
	public static void definirTaille(JComponent element, int width, int height) {
		Dimension d = new Dimension();
		d.width = width;
		d.height = height;
		element.setMaximumSize(d);
		element.setSize(d);
		element.setMinimumSize(d);
	}
	
	/** Récupérer la largeur de l'écran
	 * @return la largeur de l'écran
	 */
	public static double getScreenWidth() {
		return screenSize.getWidth();
	}
	
	/** Récupérer la hauteur de l'écran
	 * @return la hauteur de l'écran
	 */
	public static double getScreenHeight() {
		return screenSize.getHeight();
	}
	
	/** Ajouter un élément au JLayeredPane
	 * @param element L'élément à placer dans un layer
	 * @param numLayer Le numéro du layer
	 */
	public void ajouterLayer(Component element, Integer numLayer) {
		layeredPanel.add(element, numLayer);
	}
	
	/** Retirer un élément au JLayeredPane
	 * @param element L'élément à retirer
	 */
	public void retirerLayer(Component element) {
		layeredPanel.remove(element);
		layeredPanel.revalidate();
		layeredPanel.repaint();
	}

	public static void reinitialiserInstance() {
		instance = null;
		N7Frame.interfaceCarte = null;
		N7Frame.interfacePilotage = null;
	}


}
