package n7simulator.vue.Evenement;

import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Vue pour les événements irréguliers.
 */
public class EvenementIrreguGUI extends EvenementGUI{

	/**
	 * Constructeur
	 * @param evenement l'événement à afficher.
	 * @param pilotageGUI
	 */
    public EvenementIrreguGUI(Evenement evenement, PilotageGUI pilotageGUI) {
        super(evenement, pilotageGUI);
        // Quand la fenetre se ferme, on ajoute l'evenement en question à la liste des evenements passés
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pilotageGUI.enregistrerEvent(getPermanent(), false);
            }
        });
    }

}
