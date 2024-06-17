package n7simulator.vue.consommable;

import n7simulator.controller.consommable.BoutonGestionConsommables;
import n7simulator.modele.Partie;
import n7simulator.modele.foy.ConsommableFoy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vue permettant l'accès à la gestion des consommables
 */
public class VueConsommables extends JPanel {

	/**
	 * Constructeur
	 */
    public VueConsommables(){
        super(new GridLayout(1, 2));
        List<ConsommableFoy> consommables = Partie.getInstance().getFoy().getConsommables();
        setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        this.add(new JLabel("<html>Consommables foy <br>" +
                "Cliquer pour modifier les prix le prix de chaque" +
                "consommable <br> 40% du prix de vente est utilisé" +
                "pour l'approvisionnement <br>" +
                "Nombre de consommables : "+ consommables.size()+"</html>"));


        JButton modifier = new BoutonGestionConsommables();
        JPanel modificationPanel = new JPanel(new GridLayout(3, 1));
        modificationPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        //Adaptation de la vue
        modificationPanel.add(new JPanel());
        modificationPanel.add(modifier);
        this.add(modificationPanel);
    }
}
