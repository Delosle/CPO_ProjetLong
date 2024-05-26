package n7simulator.vue.jauges;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;

public class JaugesPannel extends JPanel{
    static final int LARGEUR_PANNEL = 400;
    static final int HAUTEUR_PANNEL = 180;


    public JaugesPannel(int initBonheur, int initPedagogie, int sommeInitiale) {
        setLayout(new GridLayout(3, 1));
        Jauge argent = new Jauge("Argent", 50);
        Jauge bonheur = new JaugeBornee("Bonheur");
        Jauge pedagogie = new JaugeBornee("Pedagogie", 15);

        ArgentGUI vueArgent = new ArgentGUI(argent.getNom(), argent.getValue());
        JaugeBorneeGUI vueBonheur = new JaugeBorneeGUI(bonheur.getNom(), bonheur.getValue(), new Color(212, 0, 253));
        JaugeBorneeGUI vuePedagogie = new JaugeBorneeGUI(pedagogie.getNom(), pedagogie.getValue(), Color.BLUE);

        // .......... TO-DO............
        // Ajouter les controllers des jauges

        setLayout(new GridLayout(3,1));
        setSize(LARGEUR_PANNEL, HAUTEUR_PANNEL);
        
        add(vueBonheur);
        add(vuePedagogie);
        add(vueArgent);
        
    }
}
