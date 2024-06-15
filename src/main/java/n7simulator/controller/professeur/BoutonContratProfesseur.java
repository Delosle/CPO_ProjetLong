package n7simulator.controller.professeur;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.Partie;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant les boutons permettant de modifier les caractérisitiques
 * d'un professeur. Il s'agit d'une classe abstraite devant être adaptée en
 * fonction du contexte de modification du professeur.
 */
public abstract class BoutonContratProfesseur extends JButton {

	// Le professeur étant modifié.
	protected Professeur professeur;
	// La gestion des professeurs.
	protected GestionProfesseurs gestionProfesseurs;

	/**
	 * Obtenir un bouton de modification d'un professeur.
	 * 
	 * @param professeur         : le professeur étant modifié
	 * @param texteSalaire       : le texte permettant la
	 *                           modification/initialisation du salaire dans le
	 *                           formulaire
	 * @param texteNbHeures      : le texte permettant la
	 *                           modification/initialisation du nombre d'heures du
	 *                           professeur
	 */
	protected BoutonContratProfesseur(Professeur professeur, String texteSalaire,
			String texteNbHeures) {
		this.professeur = professeur;
		this.gestionProfesseurs = Partie.getInstance().getGestionProfesseurs();
		this.addActionListener(new ActionProfesseur(texteSalaire, texteNbHeures));
	}

	/**
	 * Une fois les modifications effectuées, le bouton "valider" appelle cette
	 * fonction qui doit modifier le modele et prévenir l'utilisateur.
	 * 
	 * @param salaireSpinner : résultat du formulaire concernant le salaire
	 * @param heuresSprinner : résultat du formulaire concernant le nombre d'heures
	 */
	public abstract void resultatBouton(int salaireSpinner, int heuresSprinner);

	/**
	 * La classe affichant le formulaire pour la gestion du professeur
	 */
	private class ActionProfesseur implements ActionListener {

		// Le texte concernant la gestion du salaire dans le formulaire
		String texteSalaire;
		// Le texte concernant la gestion du nombre d'heures dans le formulaire
		String texteNbHeures;

		public ActionProfesseur(String texteSalaire, String texteNbHeures) {
			this.texteNbHeures = texteNbHeures;
			this.texteSalaire = texteSalaire;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Créer la boite de dialogue
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			panel.setLayout(new java.awt.GridLayout(3, 3, 5, 5)); // 3 lignes : description, salaire, nbheures

			// description
			JLabel messageLabel = new JLabel(
					"Le salaire minimum pour ce professeur est de " + professeur.getSalaireMin() + "€/h.");

			// Saisie du salaire
			JLabel inputSalaire = new JLabel(texteSalaire);
			JSpinner spinnerSalaire = new JSpinner(new SpinnerNumberModel(professeur.getSalaireActuel(),
					professeur.getSalaireMin(), Integer.MAX_VALUE, 1));
			JComponent editorSalaire = ((JSpinner.NumberEditor) spinnerSalaire.getEditor());
			((JSpinner.DefaultEditor) editorSalaire).getTextField().setColumns(5);

			// Saisie du nombre d'heures
			JLabel inputNbHeures = new JLabel(texteNbHeures);
			JSpinner spinnerNbHeures = new JSpinner(
					new SpinnerNumberModel(professeur.getNbHeuresTravaillees(), 0, 12, 1));
			JComponent editorNbHeures = ((JSpinner.NumberEditor) spinnerNbHeures.getEditor());
			((JSpinner.DefaultEditor) editorNbHeures).getTextField().setColumns(5);

			// Ajouter des composants
			panel.add(messageLabel);
			panel.add(Box.createRigidArea(new Dimension(0, 20)));// Espacement
			panel.add(inputSalaire);
			panel.add(spinnerSalaire);
			panel.add(inputNbHeures);
			panel.add(spinnerNbHeures);

			// Afficher la boite de dialogue
			int result = JOptionPane.showConfirmDialog(null, panel, "Gestion du contrat",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			// Implémentation des modifications
			if (result == JOptionPane.OK_OPTION) {
				resultatBouton((int) spinnerSalaire.getValue(), (int) spinnerNbHeures.getValue());
			}
		}

	}

}
