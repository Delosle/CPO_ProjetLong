package n7simulator.vue.Evenement;

import n7simulator.modele.evenements.Evenement;

import javax.swing.*;
import java.awt.*;

public class EventHistoryGUI extends JPanel{

    static final int LARGEUR_PANNEL = 50;
    JPanel eventHistory = new JPanel();

    /**
     * Constructeur
     */
    public EventHistoryGUI() {
        JScrollPane scrollPane = new JScrollPane(eventHistory);
        eventHistory.setLayout(new BoxLayout(eventHistory, BoxLayout.Y_AXIS));
        eventHistory.setBorder(BorderFactory.createTitledBorder("Historique des événements"));
        eventHistory.add(Box.createVerticalGlue());
        add(scrollPane);
        setPreferredSize(new Dimension(LARGEUR_PANNEL, this.getPreferredSize().height));


    }

    /**
     * Ajoute un événement à l'historique
     * @param event l'événement à ajouter
     */

    public void addEvent(JPanel event) {
        eventHistory.add(event, 0);
        this.revalidate();
        this.repaint();
    }


}
