package n7simulator.vue.batiment;

import n7simulator.modele.Partie;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.CrousGUI;
import n7simulator.vue.ElevesGUI;
import n7simulator.vue.N7Frame;
import n7simulator.vue.consommable.VueConsommables;

/**
 * Représenter la vue du bâtiment E-F.
 */
public class BatimentAdminGUI extends AbstractBatiment {
	
	public BatimentAdminGUI(CarteGUI laCarte) {
		super("E-F", laCarte);
		
		// Vue élève
		ElevesGUI elevesVue = new ElevesGUI();
		Partie.getInstance().addObserver(elevesVue);
		N7Frame.definirTaille(elevesVue, (int)width, 50);
		contenuBatiment.add(elevesVue);	
		
		CrousGUI crousVue = new CrousGUI();
		N7Frame.definirTaille(crousVue, (int)width, 50);
		contenuBatiment.add(crousVue);

		VueConsommables display = new VueConsommables();
		N7Frame.definirTaille(display, (int)width, 150);
		contenuBatiment.add(display);
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		this.afficherRectangle(laCarte, 8, 1, 21, 5);
	}
	
}