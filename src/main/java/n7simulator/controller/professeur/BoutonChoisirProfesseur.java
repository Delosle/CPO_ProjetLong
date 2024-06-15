package n7simulator.controller.professeur;

import javax.swing.JOptionPane;

import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant le bouton "Choisir" lors du choix d'un professeur à
 * embaucher. Elle permet la gestion du salaire et du nombre d'heures du
 * professeur.
 */
public class BoutonChoisirProfesseur extends BoutonContratProfesseur {

	/**
	 * Obtenir un bouton de choix/gestion d'un professeur à embaucher. 
	 * @param professeur : le professeur choisi
	 */
	public BoutonChoisirProfesseur(Professeur professeur) {
		super(professeur, "Veuillez entrer le salaire proposé :",
				"Nombre d'heures travaillées par jour :");
		this.setText("Choisir");
	}

	@Override
	public void resultatBouton(int salaireSpinner, int heuresSprinner) {
		// application des modifications sur le modele
		professeur.setSalaireActuel(salaireSpinner);
		professeur.setNbHeuresTravaillees(heuresSprinner);
		// le professeur est desormais embauché
		gestionProfesseurs.embaucherProfesseur(professeur);
		// message de confirmation
		JOptionPane.showMessageDialog(null,
				"Le salaire proposé de " + salaireSpinner + "€/h est accepté.\nVous avez embauché "
						+ professeur.getPrenom() + " " + professeur.getNom() + ".",
				"Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

}
