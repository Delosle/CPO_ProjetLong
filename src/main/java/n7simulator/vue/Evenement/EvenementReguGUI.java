package n7simulator.vue.Evenement;

import n7simulator.controller.ChoixEventRegu;
import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

import javax.swing.*;
import java.awt.*;

public class EvenementReguGUI extends EvenementGUI{


    public EvenementReguGUI(Evenement evenement, PilotageGUI pilotageGUI) {
        super(evenement, pilotageGUI);
        ChoixEventRegu panelChoix = new ChoixEventRegu();
        popupPanel.add(panelChoix);
    }

    public void changeInpactEnNega(int bonheur, int pedagogie, int argent){
        impactBonheur = bonheur;
        impactPedagogie = pedagogie;
        impactArgent = argent;
    }


}