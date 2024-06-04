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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.325;
        Dimension maxSize = this.getMaximumSize();
        maxSize.width = (int) width;
        this.setMaximumSize(maxSize);

        JScrollPane scrollPane = new JScrollPane(eventHistory);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Désactiver le défilement horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Activer le défilement vertical
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
