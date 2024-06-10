package n7simulator.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import n7simulator.N7Simulator;
import n7simulator.modele.Partie;
import n7simulator.modele.jauges.Jauge;
import n7simulator.vue.startmenu.StartMenuGUI;

/**
 * Classe représentant la fenêtre Game Over
 */
public class GameOverFrame extends JDialog {

	//La couleur de fond de la fenetre
    private static final Color couleurBackground = new Color(0xEAD3A8);

    public GameOverFrame(Jauge jaugeOver) {
    	this.setModal(true); //permet de bloquer le reste
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);//enlève la barre en haut de la fenetre
        //creation de la bordure
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0xC01E1E)));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(couleurBackground);
        mainPanel.setLayout(new GridLayout(4, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Message game over
        JLabel titleGameOver = new JLabel("<html><font color=\"#C01E1E\">GAME OVER !</font></html>", SwingConstants.CENTER);
        titleGameOver.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleGameOver.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleGameOver);

        // Message d'explication
        JLabel messageExplications = new JLabel(String.format("<html>Vous avez perdu ! La jauge %s a atteint la valeur 0.</html>", jaugeOver.getNom().toLowerCase()));
        messageExplications.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(messageExplications);

        // Jour atteint
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        JLabel dateAtteinte = new JLabel("Date atteinte : " + Partie.getInstance().getTemps().getJourneeEnCours().format(formatter));
        mainPanel.add(dateAtteinte);

        // Boutons
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.X_AXIS));
        JButton boutonMenu = new JButton("Menu principal");
        boutonMenu.addActionListener(new OuvrirMenuAction());
        JButton boutonQuitter = new JButton("Quitter");
        boutonQuitter.addActionListener(new QuitterAction());
        panelBoutons.add(boutonMenu);
        panelBoutons.add(boutonQuitter);
        panelBoutons.setBackground(couleurBackground);
        mainPanel.add(panelBoutons);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
    
    class QuitterAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			N7Simulator.sauvegarderPartie();
			System.exit(0);
		}
    }
    
    class OuvrirMenuAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Window[] windows = Window.getWindows();
	        for (Window window : windows) {
	                window.dispose();
	        }
			new StartMenuGUI();
		}
    }
    
}
