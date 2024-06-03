/**
 * 
 */
package n7simulator.controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import n7simulator.modele.Partie;
import n7simulator.modele.Crous;
import n7simulator.vue.N7Frame;

/**
 * 
 */
public class CrousController extends JPanel {
	
	private JComboBox selecteur;
	
	private JSpinner spinnerPrixRevente;
	
	private JLabel marge;
	
	public CrousController() {
		
		Crous instanceCrous = Crous.getInstance(0, 0.00);
		
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
		selecteur = new JComboBox(options);
		selecteur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateMarge();
			}
		});
		
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
		spinnerPrixRevente.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateMarge();
			}
		});
		
		// On place les éléments
		revente.add(bPR);
		revente.add(spinnerPrixRevente);
		prixRevente.add(revente);
		
		// On créé le contenant de la marge
		JPanel laMarge = new JPanel();
		laMarge.setLayout(new BoxLayout(laMarge, BoxLayout.Y_AXIS));
		JLabel titleMarge = new JLabel("Marge :");
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
	
	private void updateMarge() {
		int indexQualite = selecteur.getSelectedIndex();
		Double prixRevente = (Double)spinnerPrixRevente.getValue();
		Double valeur = Crous.getInstance(0, 0.0).getMarge(indexQualite, prixRevente);
		String text = "";
		if (valeur > 0) {
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
	
	public static JButton getBoutonOuverture() {
		JButton leBouton = new JButton("Modifier");
		ActionListener ouvrirModification = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrousController fenetre = new CrousController();
			}
		};
		leBouton.addActionListener(ouvrirModification);
		return leBouton;
	}

}
