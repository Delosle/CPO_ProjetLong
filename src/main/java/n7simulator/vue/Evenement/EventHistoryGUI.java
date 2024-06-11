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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Dimension dimensionEvent = new Dimension((int) (width * 0.325), (int)(height * 0.2));

        setBorder(BorderFactory.createTitledBorder("Historique des événements"));
        setLayout(new GridLayout(2, 1));
        eventIrreguHistory.setLayout(new BoxLayout(eventIrreguHistory, BoxLayout.Y_AXIS));
        eventIrreguHistory.setBorder(BorderFactory.createTitledBorder("Evénements irréguliers"));
        eventIrreguHistory.add(Box.createVerticalGlue());
        JScrollPane scrollIrregu = new JScrollPane(eventIrreguHistory);
        scrollIrregu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollIrregu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollIrregu.setSize(dimensionEvent);
        add(scrollIrregu);

        eventReguHistory.setLayout(new BoxLayout(eventReguHistory, BoxLayout.Y_AXIS));
        eventReguHistory.setBorder(BorderFactory.createTitledBorder("Evénements réguliers"));
        eventReguHistory.add(Box.createVerticalGlue());
        JScrollPane scrollRegu = new JScrollPane(eventReguHistory);
        scrollRegu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollRegu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRegu.setSize(dimensionEvent);
        add(scrollRegu);

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
        eventReguHistory.add(event, 0);
        this.revalidate();
        this.repaint();
    }

}
