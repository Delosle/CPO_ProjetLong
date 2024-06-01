package n7simulator.controller.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import n7simulator.database.GestionBddSauvegarde;
import n7simulator.vue.startmenu.StartMenuGUI;

public class ChargementSauvegardeFormulaire extends JPanel {

	    public ChargementSauvegardeFormulaire() {
	        this.setLayout(new BorderLayout());
	        this.setPreferredSize(new Dimension(100, 150));
	        
	        // Panneau pour contenir les boutons

	        // Ajout des boutons au panneau
	        List<String> nomsSauvegardes = getNomSauvegardeBD();
	        if(nomsSauvegardes.size() == 0) {
	        	this.add(new JLabel("<html>Aucune partie sauvegardée ! Veuillez commencer une nouvelle partie.</html>"));
	        } else {

		    	this.setBorder(new EmptyBorder(10, 10, 10, 10));
	        	 this.setLayout(new GridLayout(0, 1)); // Un GridLayout avec une seule colonne
	        	 
	        	 for (String sauvegarde : nomsSauvegardes) {
		            JButton button = new JButton(sauvegarde);
		            button.setBackground(new Color(0xA6DDF0));
		            button.addActionListener(new ActionListener() {
		                @Override
						public void actionPerformed(ActionEvent e) {
		                    loadBackupData(sauvegarde);
		                }
		            });
		            this.add(button);
		        }
	        }

	        Object[] options = {"Retour"};
	        int result = JOptionPane.showOptionDialog(
                    null,
                    this,
                    "Choix de la sauvegarde",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);
	        if(result == 0) {
	        	new StartMenuGUI();
	        }

	    }

	    private List<String> getNomSauvegardeBD() {
	        return GestionBddSauvegarde.recupererNomPartie();
	    }

	    private void loadBackupData(String backupName) {
	        // Simuler le chargement des données de sauvegarde
	        System.out.println("Chargement des données pour: " + backupName);
	        // Vous pouvez ouvrir une nouvelle fenêtre ici ou afficher les données comme nécessaire
	    }


}
