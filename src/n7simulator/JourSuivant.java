package n7simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet d'effectuer tous les impacts pour le jour suivant.
 */
public class JourSuivant {

	private List<ImpactJourSuivantCourtTerme> impactsCourtTerme;
	private List<ImpactJourSuivantLongTerme> impactsLongTerme;
	
	/**
	 * Constructeur.
	 */
	public JourSuivant() {
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
}