/**
 * 
 */
package n7simulator.controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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

import n7simulator.modele.Crous;

/**
 * Classe représentant un crontrolleur du Crous
 */
public class CrousController extends JPanel {
	
	private JComboBox<String> selecteur;
	private JSpinner spinnerPrixRevente;
	private JLabel marge;

	/**
	 * Permet de représenter les boutons du Crous
	 */
	public CrousController() {
		
		Crous instanceCrous = Crous.getInstance();
		
		String[] options = instanceCrous.getListeQualites();
		
		// Création du contenant qui va contenir les éléments du controller
		JPanel contenant = new JPanel(new GridLayout(3,1));
		
		// Le contenant qui va contenir les éléments de la qualité
		JPanel qualite = new JPanel();
		qualite.setLayout(new BoxLayout(qualite, BoxLayout.Y_AXIS));
		
		// On créé une boîte pour que le texte soit placé à gauche
		Box b = Box.createHorizontalBox();
		b.add(new JLabel("Qualité :"));
	    b.add( Box.createHorizontalGlue() );
		// On créé le sélecteur
		selecteur = new JComboBox<>(options);
		selecteur.addActionListener(e -> updateMarge());
		
		// On place les éléments
		qualite.add(b);
		qualite.add(selecteur);
		qualite.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		contenant.add(qualite);
		
		// Le contenant qui va contenir les éléments de prix de revente et marge
		JPanel prixRevente = new JPanel(new GridLayout(1,2));
		
		// Le contenant qui contient les éléments de prix de revente
		JPanel revente = new JPanel();
		revente.setLayout(new BoxLayout(revente, BoxLayout.Y_AXIS));
		
		// On créé une boîte pour que le texte soit placé à gauche
		Box bPR = Box.createHorizontalBox();
		bPR.add(new JLabel("Prix de revente :"));
	    bPR.add( Box.createHorizontalGlue() );
		
	    // On créé la zone de saisie du prix
		spinnerPrixRevente = new JSpinner(
				new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		spinnerPrixRevente.addChangeListener(e -> updateMarge());
		
		// On place les éléments
		revente.add(bPR);
		revente.add(spinnerPrixRevente);
		prixRevente.add(revente);
		
		// On créé le contenant de la marge
		JPanel laMarge = new JPanel();
		laMarge.setLayout(new BoxLayout(laMarge, BoxLayout.Y_AXIS));
		JLabel titleMarge = new JLabel("Marge totale :");
		titleMarge.setAlignmentX(CENTER_ALIGNMENT);
		
		// On créé la zone de la marge qui va se modifier régulièrement
		marge = new JLabel();
		marge.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		// On place les éléments
		laMarge.add(titleMarge);
		laMarge.add(marge);
		prixRevente.add(laMarge, BorderLayout.CENTER);
		contenant.add(prixRevente);
		
		// On modifie les valeurs
		selecteur.setSelectedIndex(instanceCrous.getQualite());
		spinnerPrixRevente.setValue(instanceCrous.getPrixVente());
		
		
		// Afficher la boite de dialogue
		int result = JOptionPane.showConfirmDialog(null, contenant, "Gestion du contrat",
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Implémentation des modifications
		if (result == JOptionPane.OK_OPTION) {
			instanceCrous.setQualite(selecteur.getSelectedIndex());
			instanceCrous.setPrixVente((double)spinnerPrixRevente.getValue());
		}
		
	}

	/**
	 * controlleur permettant d'updater la marge
	 */
	private void updateMarge() {
		Double valeur = Crous.getInstance().getMarge();
		String text = "";
		if (valeur >= 0) {
			text += "+ ";
		}
		else {
			text += "- ";
			valeur = valeur * -1.0;
		}
		text += "%.2f".formatted(valeur);
		text += " €";
		marge.setText(text);
	}

	/**
	 * permet de récupérer le bouton pour faire les affichages
	 * du crous afin de pouvoir faire des modifications
	 * @return bouton
	 */
	public static JButton getBoutonOuverture() {
		JButton leBouton = new JButton("Modifier");
		leBouton.addActionListener((e -> new CrousController()));
		return leBouton;
	}

}
