package n7simulator.controller;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

public class BoutonChoixProfesseur extends JButton {
	
	Professeur professeur;
	GestionProfesseurs gestionProfesseurs;
	
	public BoutonChoixProfesseur(Professeur professeur, GestionProfesseurs gestionProfesseurs) {
		this.professeur = professeur;
		this.gestionProfesseurs = gestionProfesseurs;
		this.setText("Choisir");
		this.addActionListener(new ActionChoisir());
	}
	
	private class ActionChoisir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            // Créer le panneau de la boîte de dialogue
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.setLayout(new java.awt.GridLayout(3, 3, 5, 5));
            
            JLabel messageLabel = new JLabel("Le salaire minimum pour ce professeur est de " + professeur.getSalaireMin() + "€/h.");

            // Saisie du salaire
            JLabel inputSalaire = new JLabel("Veuillez entrer le salaire proposé :");
            JSpinner spinnerSalaire = new JSpinner(new SpinnerNumberModel(professeur.getSalaireMin(), professeur.getSalaireMin(), Integer.MAX_VALUE, 1));
            JComponent editorSalaire = ((JSpinner.NumberEditor) spinnerSalaire.getEditor());
            ((JSpinner.DefaultEditor) editorSalaire).getTextField().setColumns(5);
            
         // Saisie du nombre d'heures
            JLabel inputNbHeures = new JLabel("Nombre d'heures travaillées :");
            JSpinner spinnerNbHeures = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
            JComponent editorNbHeures = ((JSpinner.NumberEditor) spinnerNbHeures.getEditor());
            ((JSpinner.DefaultEditor) editorNbHeures).getTextField().setColumns(5);

            // Ajouter les composants au panneau
            panel.add(messageLabel);
            panel.add(new JLabel()); //Espacement TODO : changer
            panel.add(inputSalaire);
            panel.add(spinnerSalaire);
            panel.add(inputNbHeures);
            panel.add(spinnerNbHeures);
            
            // Afficher la boîte de dialogue
            int result = JOptionPane.showConfirmDialog(null, panel, "Confirmation du salaire", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                int salairePropose = (int) spinnerSalaire.getValue();
                professeur.setSalaireActuel(salairePropose);
                int nbHeures = (int) spinnerNbHeures.getValue();
                professeur.setNbHeuresTravaillees(nbHeures);
                gestionProfesseurs.embaucherProfesseur(professeur);
                JOptionPane.showMessageDialog(null, "Le salaire proposé de " + salairePropose + "€/h est accepté.\nVous avez embauché "
                		+ professeur.getPrenom() + " " + professeur.getNom() + ".", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
		
	}

}
