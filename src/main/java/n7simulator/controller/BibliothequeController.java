package n7simulator.controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import n7simulator.modele.Bibliotheque;

@SuppressWarnings({ "serial" })
public class BibliothequeController extends JPanel {
	private JSpinner spinnerNbLivreAchete;
	private JLabel cout;

	public BibliothequeController() {
		Bibliotheque instanceBiblio = Bibliotheque.getInstance(0);

		// Création du contenant qui va contenir les éléments du controller
		JPanel contenant = new JPanel(new GridLayout(3,1));

		// Le contenant qui va contenir les éléments du nombre de livre acheté et du prix d'achat total
		JPanel prixAchatLivre = new JPanel(new GridLayout(1,2));

		// Le contenant qui contient les éléments du nb Livre acheté
		JPanel nbAchat = new JPanel();
		nbAchat.setLayout(new BoxLayout(nbAchat, BoxLayout.Y_AXIS));

		// On créé une boîte pour que le texte soit placé à gauche
		Box boxAchat = Box.createHorizontalBox();
		boxAchat.add(new JLabel("Nombre de Livre à acheter :"));
		boxAchat.add( Box.createHorizontalGlue() );

		// On créé la zone de saisie du prix
		spinnerNbLivreAchete = new JSpinner(
				new SpinnerNumberModel(0, 0, 1500, 1));
		spinnerNbLivreAchete.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updatePrixAchat();
			}
		});

		// On place les éléments
		nbAchat.add(boxAchat);
		nbAchat.add(spinnerNbLivreAchete);
		prixAchatLivre.add(nbAchat);

		// On créé le contenant du cout d'achat
		JPanel coutAchat = new JPanel();
		coutAchat.setLayout(new BoxLayout(coutAchat, BoxLayout.Y_AXIS));
		JLabel titleAchat = new JLabel("Cout d'achat total :");
		titleAchat.setAlignmentX(CENTER_ALIGNMENT);

		// On créé la zone du cout qui va se mettre à jour régulièrement
		cout = new JLabel();
		cout.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		// On place les éléments
		coutAchat.add(titleAchat);
		coutAchat.add(cout);
		prixAchatLivre.add(coutAchat, BorderLayout.CENTER);
		contenant.add(prixAchatLivre);

		// Afficher la boite de dialogue
		int result = JOptionPane.showConfirmDialog(null, contenant, "Acheter des livres",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Implémentation des modifications
        if (result == JOptionPane.OK_OPTION) {
            int newNbLivre = (int) spinnerNbLivreAchete.getValue() + instanceBiblio.getNbLivre();
            instanceBiblio.setNbLivre(newNbLivre);
        }
	}

	/**
	 * controlleur permettant d'updater le prix d'achat des livres
	 */
	private void updatePrixAchat() {
		int nbAchete = (int) spinnerNbLivreAchete.getValue();
		int valeur = nbAchete * Bibliotheque.getInstance(0).getPrixLivre();
		String text = String.format("%d €", valeur); // Utiliser String.format pour formater en entier
		cout.setText(text);
	}

	/**
	 * permet de récupérer le bouton pour faire les affichages
	 * de la bibliotheque afin de pouvoir faire des modifications
	 * @return bouton
	 */
	public static JButton getBoutonOuverture() {
		JButton leBouton = new JButton("Acheter des livres");
		ActionListener ouvrirModification = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BibliothequeController();
			}
		};
		leBouton.addActionListener(ouvrirModification);
		return leBouton;
	}
}
