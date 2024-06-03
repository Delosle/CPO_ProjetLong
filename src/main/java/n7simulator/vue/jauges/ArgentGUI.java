package n7simulator.vue.jauges;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import n7simulator.modele.jauges.*;

public class ArgentGUI extends JPanel implements Observer {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
    
    private JLabel valeur;

    /**
     * Initialiser la vue de l'argent avec une valeur initiale.
     * On affichera juste le nom et la valeur de la jauge correspondante.
     * @param nom
     * @param sommeInitiale
     */
    public ArgentGUI(String nom, double sommeInitiale){
        super();
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        setPreferredSize(new Dimension(200, 45));
        valeur = new JLabel(""+df.format(sommeInitiale)+" €");
        valeur.setForeground(new Color(141, 111, 0));
        add(new JLabel(nom));
        add(valeur);
    }

    @Override
    public void update(Observable o, Object arg){
        double newSomme = (double) arg;
        valeur.setText(""+df.format(newSomme)+" €");
    }

}