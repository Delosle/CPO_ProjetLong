package n7simulator.vue.Evenement;

import n7simulator.modele.evenements.Evenement;

import javax.swing.*;
import java.awt.*;

public class EventHistoryGUI extends JPanel{

    static final int LARGEUR_PANNEL = 50;
    JPanel eventIrreguHistory = new JPanel();
    JPanel eventReguHistory = new JPanel();

    /**
     * Constructeur
     */
    public EventHistoryGUI() {
        setBorder(BorderFactory.createTitledBorder("Historique des événements"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        eventIrreguHistory.setLayout(new BoxLayout(eventIrreguHistory, BoxLayout.Y_AXIS));
        eventIrreguHistory.setBorder(BorderFactory.createTitledBorder("Evénements irréguliers"));
        eventIrreguHistory.add(Box.createVerticalGlue());
        add(eventIrreguHistory);

        eventReguHistory.setLayout(new BoxLayout(eventReguHistory, BoxLayout.Y_AXIS));
        eventReguHistory.setBorder(BorderFactory.createTitledBorder("Evénements réguliers"));
        eventReguHistory.add(Box.createVerticalGlue());
        add(eventReguHistory);

        setPreferredSize(new Dimension(LARGEUR_PANNEL, this.getPreferredSize().height));

    }

    /**
     * Ajoute un événement à l'historique
     * @param event l'événement à ajouter
     */

    public void addEventIrregu(JPanel event) {
        eventIrreguHistory.add(event, 0);
        this.revalidate();
        this.repaint();
    }

    public void addEventRegu(JPanel event) {
        eventReguHistory.add(event);
        this.revalidate();
        this.repaint();
    }

}
