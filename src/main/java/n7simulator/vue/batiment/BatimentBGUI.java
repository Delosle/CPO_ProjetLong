package n7simulator.vue.batiment;

import n7simulator.vue.CarteGUI;
import n7simulator.vue.professeur.GestionProfesseursGUI;

public class BatimentBGUI extends AbstractBatiment {
	

	public BatimentBGUI(CarteGUI laCarte) {
		super("B", laCarte);
		
		contenuBatiment.add(new GestionProfesseursGUI());

	}

	@Override
	public void afficherSurCarte(CarteGUI laCarte) {
		
		this.afficherRectangle(laCarte, 1, 3, 5, 11);
		
	}
}
