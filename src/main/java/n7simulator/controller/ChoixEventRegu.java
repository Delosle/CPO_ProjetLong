package n7simulator.controller;

import n7simulator.modele.Partie;
import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.Evenement.EvenementReguGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixEventRegu extends JPanel {
    private JButton boutonOui;
    private JButton boutonNon;

    public ChoixEventRegu(Evenement evenement, Partie p, EvenementReguGUI evenementReguGUI) {

        JPanel panelOui = new JPanel(new BorderLayout());
        JPanel panelNon = new JPanel(new BorderLayout());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        boutonOui = new JButton("Oui");
        boutonOui.setBackground(Color.GREEN);
        boutonOui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evenement.appliquerImpact(p, true);
                evenementReguGUI.afficher();
                evenementReguGUI.dispose();
            }
        });

        boutonNon = new JButton("Non");
        boutonNon.setBackground(Color.RED);
        boutonNon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evenement.appliquerImpact(p, false);
                evenementReguGUI.afficher();
                evenementReguGUI.dispose();
            }
        });


        //panelOui.add(boutonOui, BorderLayout.CENTER);
        //panelNon.add(boutonNon, BorderLayout.CENTER);
        this.add(boutonNon);
        this.add(boutonOui);
        this.setBackground(Color.RED);

    }
}
