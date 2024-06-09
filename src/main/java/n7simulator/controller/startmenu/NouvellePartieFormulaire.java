package n7simulator.controller.startmenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import n7simulator.N7Simulator;
import n7simulator.database.GestionBddSauvegarde;
import n7simulator.modele.Partie;
import n7simulator.vue.startmenu.StartMenuGUI;

/**
 * Classe représentant le formulaire de nouvelle partie
 */
public class NouvellePartieFormulaire extends JPanel {

	//champ texte permettant de rentrer le nom de la partie
	JTextField fieldNomPartie;

	/**
	 * Obtenir le formulaire de nouvelle partie
	 */
	public NouvellePartieFormulaire() {
		this.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

		JLabel messageLabel = new JLabel("Nom de la partie");

		// Saisie du nom de la partie
		this.fieldNomPartie = new JTextField(10);

		// Ajouter des composants
		this.add(messageLabel);
		this.add(fieldNomPartie);

		// Afficher la boite de dialogue
		boolean isValidInput = false;
		while (!isValidInput) {
			//creation de la fenetre de dialogue
			int result = JOptionPane.showConfirmDialog(null, this, "Nouvelle Partie", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				if (testerNomValide()) {
					//initialisation de la partie
					isValidInput = true;
					Partie partie = Partie.getInstance();
					partie.initNomPartie(this.fieldNomPartie.getText().trim());
					N7Simulator.sauvegarderPartie();
					N7Simulator.initNouvellePartie();
				}
			} else {
				//retour au menu principal
				new StartMenuGUI();
				break;
			}
		}
	}

	/**
	 * Nom de la partie saisi valide ? (non vide, et n'existe pas deja)
	 * @return : si le nom est valide 
	 */
	private boolean testerNomValide() {
		String nomPartie = this.fieldNomPartie.getText().trim();
		if (nomPartie.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Le nom de la partie ne peut pas être vide.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!GestionBddSauvegarde.nomPartieDisponible(nomPartie)) {
			JOptionPane.showMessageDialog(this, "La partie " + nomPartie + " existe déjà !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
