package n7simulator.controller;

import n7simulator.modele.Partie;
import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.Evenement.EvenementReguGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur représentant le choix de l'utilisateur face aux événements réguliers
 */
public class ChoixEventRegulierController extends JPanel {
    private JButton boutonOui;
    private JButton boutonNon;

    public ChoixEventRegulierController(Evenement evenement, Partie partie, EvenementReguGUI evenementReguGUI) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        boutonOui = new JButton("Oui");
        boutonOui.setBackground(Color.GREEN);
        boutonOui.addActionListener(e -> {
                evenement.appliquerImpact(partie, true);
                evenementReguGUI.afficher();
                evenementReguGUI.dispose();
        });

        boutonNon = new JButton("Non");
        boutonNon.setBackground(Color.RED);
        boutonNon.addActionListener(e -> {
                evenement.appliquerImpact(partie, false);
                evenementReguGUI.afficher();
                evenementReguGUI.dispose();
        });
        
        this.add(boutonNon);
        this.add(boutonOui);
        this.setBackground(Color.RED);

    }
}
