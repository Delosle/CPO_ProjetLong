package n7simulator.joursuivant;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet d'effectuer tous les impacts pour le jour suivant.
 */
public final class JourSuivant {

	private static JourSuivant instance;
	
	private static List<ImpactJourSuivant> impacts = new ArrayList<>();

	/**
	 * Contient l'instance de la classe.
	 * @return l'instance de JourSui
	 */
	public static JourSuivant getInstance() {
		if (instance == null) {
			instance = new JourSuivant();
		}
		return instance;
	}
	
	private JourSuivant() {}
	
	/**
	 * Ajoute un impact dans la liste des impacts.
	 * @param impact
	 */
	public void addImpact(ImpactJourSuivant impact) {
		impacts.add(impact);
	}
	
	/**
	 * Effectue tous les impacts.
	 */
	public void effectuerImpactsJourSuivant() {
		for (ImpactJourSuivant impact : impacts) {
			impact.effectuerImpactJourSuivant();
		}
	}
}
