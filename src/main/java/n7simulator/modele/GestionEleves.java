package n7simulator.modele;

import java.util.Observable;

import n7simulator.joursuivant.ImpactJourSuivantCourtTerme;;

/**
 * Classe permettant de gérer le nombre d'élèves inscrits à l'N7.
 */
public class GestionEleves extends Observable implements ImpactJourSuivantCourtTerme {

	/**
	 * Le nombre d'élèves inscrits à l'N7.
	 */
	private static int nombreEleves;
	
	/**
	 * Obtenir le nombre d'élèves inscrits à l'N7.
	 * @return le nombre d'élèves inscrits.
	 */
	public int getNombreEleves() {
		return nombreEleves;
	}
	
	/**
	 * Permet d'inscrire de nouveaux élèves.
	 * @param nbEleves les nouveaux élèves
	 */
	public void inscrireEleves(int nbEleves) {
		nombreEleves += nbEleves;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Permet de désinscrire des élèves.
	 * @param nbEleves 
	 */
	public void desinscrireEleves(int nbEleves) {
		nombreEleves = nombreEleves - nbEleves < 0 ? 0 : nombreEleves - nbEleves;
		this.setChanged();
		this.notifyObservers(this);
	}	

	@Override
	public void effectuerImpactJourSuivantCourtTerme() {
		Partie partie = Partie.getInstance();
		int gainMax = nombreEleves / (nombreEleves < 200 ? 5 : 50);		
		int totalJauges = (partie.getJaugeBonheur().getValue() + partie.getJaugePedagogie().getValue()) / 2;
		int gain = totalJauges * gainMax / 100;
		
		if (totalJauges >= 25) {
			inscrireEleves(gain);
		} else {
			gain = gain - gainMax;
			gain = gain > 0 ? gain : ( -gain);
			desinscrireEleves(gain);
		}		
	}	
}
