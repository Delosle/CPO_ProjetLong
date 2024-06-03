package joursuivant;

/**
 * Interface pour les impacts de jour suivant.
 */
public interface ImpactJourSuivant {

	/**
	 * Indique si l'impact doit être supprimé quand on passe au jour suivant.
	 * @return true si à supprimer, false sinon
	 */
	public boolean aSupprimerLendemain();
	
}
