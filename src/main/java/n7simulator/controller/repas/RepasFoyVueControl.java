package n7simulator.controller.repas;

import n7simulator.modele.repas.RepasFoy;
import n7simulator.vue.repas.RepasFoyGUI;

import javax.swing.*;
import java.awt.*;

public class RepasFoyVueControl extends JPanel {

    public RepasFoyVueControl(RepasFoy repasFoy){
        super(new GridLayout(2, 1));
        RepasFoyGUI vueRepas = new RepasFoyGUI(repasFoy.getNom(),repasFoy.getPrix(),repasFoy.getImage());
        BoutonModifierPrix boutonModifierPrix = new BoutonModifierPrix(repasFoy);

        repasFoy.addObserver(vueRepas);

        add(vueRepas);
        add(boutonModifierPrix);

    }
}
