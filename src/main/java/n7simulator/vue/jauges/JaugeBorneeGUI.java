package n7simulator.vue.jauges;

import java.awt.*;
import javax.swing.*;
import n7simulator.modele.jauges.*;
import java.util.*;

public class JaugeBorneeGUI extends JPanel implements Observer {
    private static int LARGEUR_PANNEL = 480;
    private static int HAUTEUR_PANNEL = 75;
    private static int LARGEUR_BARMAX = 350;
    private static int HAUTER_BARMAX = 40;
    
    /**
     * Pannel pour afficher la valeur entière contenu dans la jauge
     */
    private JLabel valeurLabel;
    /**
     * Pannel pour représenter la bare d'évolution de la jauge par rapport
     * à la valeur maximale
     */
    private JPanel bareValue;

    /**
     * la largeur de la bareValue par rapport la largeur maximale
     */
    private int largBar;

    /**
     * Initialiser une vue d'une jauge Bornée à partir de son nom,
     * sa valeur initiale et de sa couleur d'affichage
     * @param nom
     * @param initValue la valeur initiale de la jauge
     * @param couleur la couleur d'affichage de la jauge
     */
    public JaugeBorneeGUI(String nom, int initValue, Color couleur) {
        super(new BorderLayout());
        //création d'une marge
        setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        setPreferredSize(new Dimension(LARGEUR_PANNEL, HAUTEUR_PANNEL));

        largBar = LARGEUR_BARMAX * initValue / JaugeBornee.BORNE_MAX;

        // Créer le JLabel pour le nom
        JLabel nomLabel = new JLabel(nom);
        nomLabel.setForeground(couleur);
        add(nomLabel, BorderLayout.NORTH);

        // Créer le JPanel pour contenir la valeur de la jauge et la barre de valeur
        JPanel valuesPanel = new JPanel(new BorderLayout());

        // Créer le JPanel pour la barre de valeur
        bareValue = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Définir la couleur de fond pour le rectangle
                g.setColor(Color.WHITE);

                // Dessiner le rectangle qui contiendra la bareValue
                g.fillRect(0, 0, LARGEUR_BARMAX, HAUTER_BARMAX);
                g.setColor(couleur);
                g.drawRect(0, 0, LARGEUR_BARMAX, HAUTER_BARMAX);
                
                //Dessiner la bareValue
                g.fillRect(0, 0, largBar, HAUTER_BARMAX);
            }
        };
        bareValue.setPreferredSize(new Dimension(LARGEUR_BARMAX+10, HAUTER_BARMAX+5));
        

        // Créer le JLabel pour la valeur
        valeurLabel = new JLabel("" + initValue);
        valeurLabel.setForeground(couleur);
        
        // Ajout de la barre de valeur
        valuesPanel.add(bareValue, BorderLayout.EAST);
        
        // Ajout de la valeur à l'est
        valuesPanel.add(valeurLabel, BorderLayout.CENTER);
        
        add(valuesPanel, BorderLayout.CENTER);
       
    }

    @Override
    public void update(Observable o, Object arg) {
        Integer val = (Integer) arg;
        valeurLabel.setText(""+val);
        largBar = LARGEUR_BARMAX * val / JaugeBornee.BORNE_MAX;
        bareValue.repaint(); // Redessine la barre de valeur lorsque la valeur est mise à jour
    }
}