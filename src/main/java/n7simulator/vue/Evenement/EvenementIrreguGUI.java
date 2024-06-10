package n7simulator.vue.Evenement;

import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EvenementIrreguGUI extends EvenementGUI{

    public EvenementIrreguGUI(Evenement evenement, PilotageGUI pilotageGUI) {
        super(evenement, pilotageGUI);
        // Quand la fenetre se ferme, on ajoute l'evenement en question à la liste des evenements passés
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pilotageGUI.enregistrerEvent(getPermanent(), false);
            };

        });
    }

}
