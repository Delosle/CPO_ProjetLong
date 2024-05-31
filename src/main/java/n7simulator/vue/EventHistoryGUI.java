package n7simulator.vue;

import n7simulator.modele.Evenements.Evenement;

import javax.swing.*;
import java.awt.*;

public class EventHistoryGUI extends JPanel{

    static final int LARGEUR_PANNEL = 1500;
    JPanel eventHistory = new JPanel();

    public EventHistoryGUI() {

        setPreferredSize(new Dimension(LARGEUR_PANNEL, this.getPreferredSize().height));
        JScrollPane scrollPane = new JScrollPane(eventHistory);

        eventHistory.setLayout(new BoxLayout(eventHistory, BoxLayout.Y_AXIS));
        eventHistory.setBorder(BorderFactory.createTitledBorder("Historique des événements"));
        eventHistory.add(Box.createVerticalGlue());

        this.add(scrollPane);

    }

    public void addEvent(JPanel event) {
        eventHistory.add(event, 0);
        eventHistory.add(Box.createRigidArea(new Dimension(0, 10)));
        eventHistory.revalidate();
        eventHistory.repaint();
    }

    public void adjustSizeToParent() {
        Container parent = this.getParent();
        if (parent != null) {
            Dimension parentSize = parent.getSize();
            this.setPreferredSize(parentSize);
            this.revalidate();
        }
    }
}
