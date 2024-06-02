/**
 * 
 */
package n7simulator.controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import n7simulator.modele.Partie;
import n7simulator.vue.N7Frame;

/**
 * 
 */
public class CrousController extends JPanel {
	
	private JComboBox selecteur;
	
	private JSpinner spinnerPrixRevente;
	
	private JLabel marge;

	public CrousController() {
		// TODO : Récupérer l'instance du modèle de Crous
		String[] options = {"Mauvaise : 1.10€", "Acceptable : 1.30€", "Bonne : 1.70€", "Très bonne : 2.00€"};
		
		JPanel contenant = new JPanel(new GridLayout(3,1));
		
		JPanel qualite = new JPanel();
		qualite.setLayout(new BoxLayout(qualite, BoxLayout.Y_AXIS));
		Box b = Box.createHorizontalBox();
		b.add(new JLabel("Qualité :"));
	    b.add( Box.createHorizontalGlue() );
		qualite.add(b);
		selecteur = new JComboBox(options);
		// TODO : placer la valeur de la qualité
		qualite.add(selecteur);
		contenant.add(qualite);
		
		JPanel prixRevente = new JPanel(new GridLayout(1,2));
		
		JPanel revente = new JPanel();
		revente.setLayout(new BoxLayout(revente, BoxLayout.Y_AXIS));
		Box bPR = Box.createHorizontalBox();
		bPR.add(new JLabel("Prix Revente :"));
	    bPR.add( Box.createHorizontalGlue() );
		revente.add(bPR);
		// TODO : placer la valeur du prix de revente
		spinnerPrixRevente = new JSpinner(
				new SpinnerNumberModel(0.0, 0.0, 100.0, 0.1));
		revente.add(spinnerPrixRevente);
		prixRevente.add(revente);
		
		JPanel laMarge = new JPanel();
		laMarge.setLayout(new BoxLayout(laMarge, BoxLayout.Y_AXIS));
		laMarge.add(new JLabel("Marge :"));
		
		// TODO : récupérer la valeur de la marge
		marge = new JLabel("+ 2 €");
		laMarge.add(marge);
		prixRevente.add(laMarge);
		
		contenant.add(prixRevente);
		
		// TODO : réaliser le calcul de la marge
		
		// Afficher la boite de dialogue
		int result = JOptionPane.showConfirmDialog(null, contenant, "Gestion du contrat",
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Implémentation des modifications
		if (result == JOptionPane.OK_OPTION) {
			// TODO : faire l'implémentation des modifications
		}
		
	}
	
	public static JButton getBoutonOuverture() {
		JButton leBouton = new JButton("Modifier");
		ActionListener ouvrirModification = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrousController fenetre = new CrousController();
				//N7Frame.getInstance(null, null).ajouterLayer(fenetre, JLayeredPane.MODAL_LAYER);
			}
		};
		leBouton.addActionListener(ouvrirModification);
		return leBouton;
	}

}
