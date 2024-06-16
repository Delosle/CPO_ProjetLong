package n7simulator.vue.batiment;

import n7simulator.vue.CarteGUI;

public class BatimentAGUI extends AbstractBatiment {
	

	public BatimentAGUI(CarteGUI laCarte) {
		super("A", laCarte);

	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 1, 13, 5, 19);
		this.afficherRectangle(laCarte, 6, 17, 8, 19);
		
	}
}
