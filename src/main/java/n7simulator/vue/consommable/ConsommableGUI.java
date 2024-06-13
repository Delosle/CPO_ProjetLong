package n7simulator.vue.consommable;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe de l'affichage d'un consommable au détail
 */
public class ConsommableGUI extends JPanel implements Observer {

	//Prix du consommable à afficher
    private JLabel prixLabel;

    public ConsommableGUI(String nom, double prixActuel, String image){
        super(new GridLayout(1,2));

        //Affichage de l'image
        ImageIcon imageIcon = new ImageIcon("src/main/resources/images/"+image);
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        //Affichage des informations sur l'image
        JPanel blocInfoRepas = new JPanel(new GridLayout(2, 1));
        JLabel titreLabel = new JLabel(nom);
        prixLabel = new JLabel("Prix Actuel: " + prixActuel + " €");
        blocInfoRepas.add(titreLabel);
        blocInfoRepas.add(prixLabel);
        add(blocInfoRepas);

    }

    @Override
    public void update(Observable o, Object arg) {
        double newPrix = (double)arg ;
        prixLabel.setText("Prix Actuel: " + newPrix + " €");
    }
}
