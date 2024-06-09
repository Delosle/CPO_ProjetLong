package n7simulator.modele.professeur;

import java.util.List;
import java.util.Observable;

import n7simulator.ImpactJourSuivantCourtTerme;
import n7simulator.modele.Partie;
import n7simulator.modele.jauges.Jauge;

/**
 * Classe permettant la gestion des professeurs avec la distinction entre ceux
 * qui sont embauchés et ceux qui ne le sont pas.
 */
public class GestionProfesseurs extends Observable implements ImpactJourSuivantCourtTerme {
	// liste des professeurs embauchés
	private List<Professeur> professeursEmbauches;

	// liste des professeurs non embauchés
	private List<Professeur> professeursNonEmbauches;

	/**
	 * Obtenir un gestionnaire des professeurs à partir de la liste des professeurs
	 * embauchés et non embauchés
	 * 
	 * @param professeursEmbauches    : liste des professeurs embauchés
	 * @param professeursNonEmbauches : liste des professeurs non embauchés
	 */
	public GestionProfesseurs(List<Professeur> professeursEmbauches, List<Professeur> professeursNonEmbauches) {
		this.professeursEmbauches = professeursEmbauches;
		this.professeursNonEmbauches = professeursNonEmbauches;
	}

	/**
	 * Obtenir les professeurs embauchés
	 * 
	 * @return : liste des professeurs embauchés
	 */
	public List<Professeur> getProfesseursEmbauches() {
		return professeursEmbauches;
	}

	/**
	 * Obtenir les professeurs non embauchés
	 * 
	 * @return : liste des professeurs non embauchés
	 */
	public List<Professeur> getProfesseursNonEmbauches() {
		return professeursNonEmbauches;
	}

	/**
	 * Permet d'embaucher un professeur (notifie les observateurs du changement)
	 * 
	 * @param professeur : le professeur à embaucher
	 */
	public void embaucherProfesseur(Professeur professeur) {
		if (professeursNonEmbauches.remove(professeur)) {
			professeursEmbauches.add(professeur);
			this.setChanged();
			this.notifyObservers(this);
		}
	}

	/**
	 * Licencie un professeur (notifie les observateurs du changement)
	 * @param professeur : le professeur à licencier
	 */
	public void licencierProfesseur(Professeur professeur) {
		if (professeursEmbauches.remove(professeur)) {
			professeursNonEmbauches.add(professeur);
			this.setChanged();
			this.notifyObservers(this);
		}
	}

	@Override
	public void effectuerImpactJourSuivantCourtTerme() {
		Partie partie = Partie.getInstance();
		Jauge jaugeArgent =	partie.getJaugeArgent();
		Jauge jaugePedagogie = partie.getJaugePedagogie();
		Jauge jaugeBohneur = partie.getJaugeBonheur();
		int totalPeda = 0;
		int totalSalaireSupMin = 0;
		
		for (Professeur prof : professeursEmbauches) {
			// Paiement salaire prof
			jaugeArgent.ajouter(-(prof.getNbHeuresTravaillees()*prof.getSalaireActuel()));
			
			totalPeda += prof.getNiveau();
			totalSalaireSupMin += prof.getSalaireActuel() - prof.getSalaireMin();
		}
		
		int nbProfsEmbauches = professeursEmbauches.size();
		
		if (nbProfsEmbauches > 0) {
			// Update niveau pedagogie
			double moyennePeda = totalPeda / nbProfsEmbauches;
			if (moyennePeda >= 40) {
				jaugePedagogie.ajouter((int)(moyennePeda / 10));
			} else {
				jaugePedagogie.ajouter((int)((moyennePeda - 50) / 3));
			}

			double moyenneSalaireSupMin = totalSalaireSupMin / nbProfsEmbauches;
			
			// Si moins d'un professeur pour 50 élèves, malus
			if (partie.getNombreEleves() / nbProfsEmbauches > 50) {
				jaugePedagogie.ajouter(-5);
				
				if (moyenneSalaireSupMin < 100) {
					jaugeBohneur.ajouter(-10);
				}
			}
			
			// Update niveau bonheur profs
			if (moyenneSalaireSupMin >= 100) {
				jaugeBohneur.ajouter(10);
			}
			
		} else {
			jaugePedagogie.ajouter(-20);
		}
	}

}
