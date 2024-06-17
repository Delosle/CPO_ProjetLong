package n7simulator.controller.professeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.Partie;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant les boutons permettant de licencier un professeur.
 */
public class BoutonLicencierProfesseur extends JButton {

	// Le professeur étant licencié.
	private Professeur professeur;
	// La gestion des professeurs.
	private GestionProfesseurs gestionProfesseurs;

	/**
	 * Obtenir un bouton de licenciement d'un professeur (afin de le supprimer de la
	 * liste des professeurs embauchés)
	 * 
	 * @param professeur         : le professeur concerné
	 */
	public BoutonLicencierProfesseur(Professeur professeur) {
		this.professeur = professeur;
		this.gestionProfesseurs = Partie.getInstance().getGestionProfesseurs();
		this.setText("Licencier");
		this.addActionListener(new ActionListener() {
			/**
			 * Action du bouton licencier : affiche un formulaire de confirmation et
			 * licencie le professeur le cas échéant.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// Créer la boite de dialogue
				JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));

				// Message de confirmation
				JLabel messageLabel = new JLabel(
						"Etes-vous sûrs de vouloir licencier " + professeur.getPrenom() + " " + professeur.getNom() + " ?");
				panel.add(messageLabel);

				// Afficher la boite de dialogue
				String[] options = { "Oui", "Non" };
				int result = JOptionPane.showOptionDialog(null, panel, "Confirmation du licenciement",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				// Implémentation des modifications
				if (result == JOptionPane.OK_OPTION) {
					// le professeur est licencié
					gestionProfesseurs.licencierProfesseur(professeur);
					// message de confirmation
					JOptionPane.showMessageDialog(null,
							professeur.getPrenom() + " " + professeur.getNom() + " a été licencié(e).", "Confirmation",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
	}

}
