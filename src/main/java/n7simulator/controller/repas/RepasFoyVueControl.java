package n7simulator.controller.repas;

import n7simulator.modele.repas.RepasFoy;
import n7simulator.vue.repas.RepasFoyGUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class RepasFoyVueControl extends JPanel {

    public RepasFoyVueControl(RepasFoy repasFoy, RepasFoyGUI vueRepas){
        super(new GridLayout(2, 1));
        setPreferredSize(new Dimension(500, 200));
        repasFoy.addObserver(vueRepas);
        BoutonModifierPrix boutonModifierPrix = new BoutonModifierPrix(repasFoy);
        JPanel panelMofierPrix = new JPanel();
        panelMofierPrix.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        panelMofierPrix.add(boutonModifierPrix);
        add(vueRepas);
        add(panelMofierPrix);
        Border bottomBorder = new MatteBorder(0, 0, 2, 0, new Color(141, 111, 0));
        setBorder(bottomBorder);
    }
}
