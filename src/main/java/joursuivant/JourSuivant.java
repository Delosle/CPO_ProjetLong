package joursuivant;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet d'effectuer tous les impacts pour le jour suivant.
 */
public final class JourSuivant {

	private static JourSuivant instance;
	
	private static List<ImpactJourSuivantCourtTerme> impactsCourtTerme;
	private static List<ImpactJourSuivantLongTerme> impactsLongTerme; 

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
	
	private JourSuivant() {
		impactsCourtTerme = new ArrayList<ImpactJourSuivantCourtTerme>();
		impactsLongTerme = new ArrayList<ImpactJourSuivantLongTerme>();
	}
	
	/**
	 * Ajoute un impact dans la liste des impacts.
	 * @param impact
	 */
	public void addImpactLongTerme(ImpactJourSuivantLongTerme impact) {
		impactsLongTerme.add(impact);
	}
	
	/**
	 * Ajoute un impact dans la liste des impacts.
	 * @param impact
	 */
	public void addImpactCourtTerme(ImpactJourSuivantCourtTerme impact) {
		impactsCourtTerme.add(impact);
	}
	
	/**
	 * Effectue tous les impacts.
	 */
	public void effectuerImpactsJourSuivant() {
		for (ImpactJourSuivantCourtTerme impact : impactsCourtTerme) {
			impact.effectuerImpactJourSuivantCourtTerme();
		}
		
		for (ImpactJourSuivantLongTerme impact : impactsLongTerme) {
			impact.effectuerImpactJourSuivantLongTerme();
		}
	}
	
	/**
	 * Permet de clearer le contenu des listes.
	 */
	public void reinitialiser() {
		impactsCourtTerme.clear();
		impactsLongTerme.clear();
	}
}
