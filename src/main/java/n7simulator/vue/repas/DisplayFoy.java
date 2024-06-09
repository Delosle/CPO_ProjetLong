package n7simulator.vue.repas;

import n7simulator.controller.repas.ConsommableFoyController;
import n7simulator.modele.repas.ConsommableFoy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisplayFoy extends JPanel {

    public DisplayFoy(List<ConsommableFoy> reaps){
        super(new GridLayout(1, 2));
        setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JPanel description = new JPanel(new GridLayout(2, 1));
        description.add(new JLabel("<html>Consommables foy <br>" +
                "Cliquer pour modifier les prix le prix de chaque" +
                "consommable <br> 40% du prix de vente est utilisé pour l'approvisionnement</html>"));

        description.add(new JLabel("Nombre de consommables : "+reaps.size()));
        add(description);
        JButton modifier = ConsommableFoyController.getBoutonOuverture(reaps);
        JPanel modifierPannel = new JPanel();
        modifierPannel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        modifier.setPreferredSize(new Dimension(645,34));
        modifierPannel.add(modifier);
        add(modifierPannel);
    }
}
