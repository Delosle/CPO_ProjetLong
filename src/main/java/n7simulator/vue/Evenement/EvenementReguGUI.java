package n7simulator.vue.Evenement;

import n7simulator.controller.ChoixEventRegulierController;
import n7simulator.modele.Partie;
import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

/**
 * Vue pour les événements réguliers.
 */
public class EvenementReguGUI extends EvenementGUI{

	/**
	 * Constructeur
	 * @param evenement l'événement à afficher
	 * @param pilotageGUI
	 */
    public EvenementReguGUI(Evenement evenement, PilotageGUI pilotageGUI) {
        super(evenement, pilotageGUI);
        panelChoix.add(new ChoixEventRegulierController(evenement,  Partie.getInstance(), this));
        revalidate();
        repaint();
    }

    /**
     * Affiche l'événement
     */
    public void afficher(){
        pilotageGUI.enregistrerEvent(getPermanent(), true);
    }
}
