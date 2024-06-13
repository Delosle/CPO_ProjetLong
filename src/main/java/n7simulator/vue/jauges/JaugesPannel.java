package n7simulator.vue.jauges;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import n7simulator.modele.Partie;
import n7simulator.modele.jauges.Jauge;

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

    public JaugesPannel() {
        setLayout(new GridLayout(3, 1));
        
        Partie partie = Partie.getInstance();
        Jauge argent = partie.getJaugeArgent();
		Jauge bonheur = partie.getJaugeBonheur();
		Jauge pedagogie = partie.getJaugePedagogie();
        
        vueArgent = new ArgentGUI("Argent", argent.getValue());
        vueBonheur = new JaugeBorneeGUI("Bonheur", bonheur.getValue(), new Color(212, 0, 253));
        vuePedagogie = new JaugeBorneeGUI("Pedagogie", pedagogie.getValue(), Color.BLUE);
        argent.addObserver(vueArgent);
		bonheur.addObserver(vueBonheur);
		pedagogie.addObserver(vuePedagogie);
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