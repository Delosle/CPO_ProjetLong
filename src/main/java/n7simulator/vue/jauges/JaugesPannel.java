package n7simulator.vue.jauges;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;

public class JaugesPannel extends JPanel{
    static final int LARGEUR_PANNEL = 500;
    static final int HAUTEUR_PANNEL = 240;

    public ArgentGUI vueArgent;
    public JaugeBorneeGUI vueBonheur;
    public JaugeBorneeGUI vuePedagogie;

    public JaugesPannel(int initBonheur, int initPedagogie, int sommeInitiale) {
        setLayout(new GridLayout(3, 1));
        
        vueArgent = new ArgentGUI("Argent", sommeInitiale);
        vueBonheur = new JaugeBorneeGUI("Bonheur", 0, new Color(212, 0, 253));
        vuePedagogie = new JaugeBorneeGUI("Pedagogie", 0, Color.BLUE);

        // .......... TO-DO............
        // Ajouter les controllers des jauges

        setLayout(new GridLayout(3,1));
        setSize(LARGEUR_PANNEL, HAUTEUR_PANNEL);
        
        add(vueBonheur);
        add(vuePedagogie);
        add(vueArgent);
        
    }
}