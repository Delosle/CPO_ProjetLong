package n7simulator.vue.repas;

import n7simulator.controller.repas.RepasFoyVueControl;
import n7simulator.database.RepasFoyDAO;
import n7simulator.modele.repas.RepasFoy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListeRepasFoy extends JPanel {

    public ListeRepasFoy(List<RepasFoy> dataRepas){
        super(new GridLayout(dataRepas.size(), 1));
        //setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        for (RepasFoy repasFoy : dataRepas) {
            RepasFoyGUI vueRepas = new RepasFoyGUI(repasFoy.getNom(),repasFoy.getPrix(),repasFoy.getImage());
            repasFoy.addObserver(vueRepas);
            JPanel repasVC = new JPanel();
            repasVC.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
            repasVC.add(new RepasFoyVueControl(repasFoy, vueRepas));
            add(repasVC);
        }
    }
}
