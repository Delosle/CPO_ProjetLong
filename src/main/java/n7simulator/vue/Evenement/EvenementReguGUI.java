package n7simulator.vue.Evenement;

import n7simulator.controller.ChoixEventRegulierController;
import n7simulator.modele.Partie;
import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

import javax.swing.*;
import java.awt.*;

public class EvenementReguGUI extends EvenementGUI{

    public EvenementReguGUI(Evenement evenement, PilotageGUI pilotageGUI) {
        super(evenement, pilotageGUI);
        panelChoix.add(new ChoixEventRegulierController(evenement,  Partie.getInstance(), this));
        //panelChoix.add(panelBoutonsChoix);
        revalidate();
        repaint();
    }

    public void changeInpactEnNega(int bonheur, int pedagogie, int argent){
        impactBonheur = bonheur;
        impactPedagogie = pedagogie;
        impactArgent = argent;
    }

    public void afficher(){
        pilotageGUI.enregistrerEvent(getPermanent(), true);
    }


}
