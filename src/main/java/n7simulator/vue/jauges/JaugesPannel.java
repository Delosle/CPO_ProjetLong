package n7simulator.vue.jauges;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class JaugesPannel extends JPanel{
    static final int LARGEUR_PANNEL = 500;
    static final int HAUTEUR_PANNEL = 240;

    /**
     * La vue sur l'argent
     */
    private ArgentGUI vueArgent;
    /**
     * La vue sur le bonheur
     */
    private JaugeBorneeGUI vueBonheur;
    /**
     * La vue sur la pédagogie
     */
    private JaugeBorneeGUI vuePedagogie;

    public JaugesPannel(int initBonheur, int initPedagogie, int sommeInitiale) {
        setLayout(new GridLayout(3, 1));
        
        vueArgent = new ArgentGUI("Argent", sommeInitiale);
        vueBonheur = new JaugeBorneeGUI("Bonheur", initBonheur, new Color(212, 0, 253));
        vuePedagogie = new JaugeBorneeGUI("Pedagogie", initPedagogie, Color.BLUE);

        // .......... TO-DO............
        // Ajouter les controllers des jauges

        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(LARGEUR_PANNEL, HAUTEUR_PANNEL));
        
        add(vueBonheur);
        add(vuePedagogie);
        add(vueArgent);
    }

    public ArgentGUI getVueArgent(){
        return vueArgent;
    }

    public JaugeBorneeGUI getVueBonheur(){
        return vueBonheur;
    }

    public JaugeBorneeGUI getVuePedagogie(){
        return vuePedagogie;
    }
}