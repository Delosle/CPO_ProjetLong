package n7simulator.vue.consommable;

import n7simulator.controller.consommable.ConsommableFoyController;
import n7simulator.modele.consommableFoy.ConsommableFoy;
import n7simulator.modele.consommableFoy.ConsommablesFoy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VueConsommables extends JPanel {

    public VueConsommables(){
        super(new GridLayout(1, 2));
        List<ConsommableFoy> consommables = ConsommablesFoy.getConsommables();
        setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JPanel description = new JPanel(new GridLayout());
        description.add(new JLabel("<html>Consommables foy <br>" +
                "Cliquer pour modifier les prix le prix de chaque" +
                "consommable <br> 40% du prix de vente est utilisé" +
                "pour l'approvisionnement <br>" +
                "Nombre de consommables : "+ consommables.size()+"</html>"));

        add(description);


        JButton modifier = ConsommableFoyController.getBoutonOuverture(consommables);
        JPanel modifierPannel = new JPanel(new GridLayout(3, 1));
        modifierPannel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        //Adaptation de la vue
        modifierPannel.add(new JPanel());
        modifierPannel.add(modifier);
        add(modifierPannel);
    }
}
