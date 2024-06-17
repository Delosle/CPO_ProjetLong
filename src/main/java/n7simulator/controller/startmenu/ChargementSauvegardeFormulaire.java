package n7simulator.controller.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import n7simulator.N7Simulator;
import n7simulator.database.GestionBddSauvegarde;
import n7simulator.vue.startmenu.StartMenuGUI;

/**
 * Classe représentant le formulaire pour le choix de la sauvegarde à charger
 */
public class ChargementSauvegardeFormulaire extends JPanel {
	
	/**
	 * Obtenir un formulaire de chargement de sauvegarde
	 */
	public ChargementSauvegardeFormulaire() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(100, 150));

		// Recuperation du nom des sauvegardes en BD
		List<String> nomsSauvegardes = getNomSauvegardeBD();
		
		if (nomsSauvegardes.isEmpty()) {
			this.add(new JLabel("<html>Aucune partie sauvegardée ! Veuillez commencer une nouvelle partie.</html>"));
		} else {
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
			this.setLayout(new GridLayout(0, 1)); // Un GridLayout avec une seule colonne

			// Ajout des noms des parties
			for (String sauvegarde : nomsSauvegardes) {
				JButton button = new JButton(sauvegarde);
				button.setBackground(new Color(0xA6DDF0));
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						chargerPartie(sauvegarde);
					}
				});
				this.add(button);
			}

		}

		//Ajout des éléments dans une fenetre de dialogue
		Object[] options = { "Retour" };
		int result = JOptionPane.showOptionDialog(null, this, "Choix de la sauvegarde", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		// Si bouton retour, retour menu
		if (result == 0) {
			new StartMenuGUI();
		}
	}

	/**
	 * Obtenir le nom des sauvegardes en base de données
	 * @return : la liste des noms de sauvegarde
	 */
	private List<String> getNomSauvegardeBD() {
		return GestionBddSauvegarde.recupererNomPartie();
	}

	/**
	 * Charge la partie une fois le bouton cliqué
	 * @param nomPartie : le nom de la partie à charger
	 */
	private void chargerPartie(String nomPartie) {
		Window win = SwingUtilities.getWindowAncestor(this.getParent());
		win.dispose();
		N7Simulator.initPartieChargee(nomPartie);
	}

}
