package n7simulator.controller.professeur;

import javax.swing.JOptionPane;

import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant le bouton "Modifier" lors de la visualisation des
 * professeurs embauchés. Elle permet la gestion du salaire et du nombre
 * d'heures du professeur.
 */
public class BoutonModifierContratProfesseur extends BoutonGeneriqueProfesseur {

	/**
	 * Obtenir un bouton de modification/gestion d'un professeur à embaucher.
	 * 
	 * @param professeur         : le professeur choisi
	 */
	public BoutonModifierContratProfesseur(Professeur professeur, GestionProfesseurs gestionProfesseurs) {
		super(professeur, "Veuillez entrer le nouveau salaire : ",
				"Nombre d'heures travaillées par jour :");
		this.setText("Modifier");
	}

	@Override
	public void resultatBouton(int salaireSpinner, int heuresSprinner) {
		// modification du modele
		professeur.modifierContrat(salaireSpinner, heuresSprinner);
		// message de confirmation
		JOptionPane.showMessageDialog(null, "Les modifications ont bien été acceptées pour le professeur "
				+ professeur.getPrenom() + " " + professeur.getNom() + ".", "Confirmation",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
