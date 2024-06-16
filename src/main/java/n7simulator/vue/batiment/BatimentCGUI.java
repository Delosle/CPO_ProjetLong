package n7simulator.vue.batiment;

import n7simulator.vue.BibliothequeGUI;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;

public class BatimentCGUI extends AbstractBatiment {
	

	public BatimentCGUI(CarteGUI laCarte) {
		super("C", laCarte);
		
		// Bibliotheque
		BibliothequeGUI biblioVue = new BibliothequeGUI();
		N7Frame.definirTaille(biblioVue, (int)width, 50);
		contenuBatiment.add(biblioVue);
	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 12, 17, 22, 19);
		this.afficherRectangle(laCarte, 19, 13, 22, 16);
		
	}
}
