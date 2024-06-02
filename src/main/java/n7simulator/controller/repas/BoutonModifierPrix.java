package n7simulator.controller.repas;

import n7simulator.modele.repas.RepasFoy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonModifierPrix extends JButton {

    private RepasFoy repasFoy;

    public BoutonModifierPrix(RepasFoy repasFoy){
        this.repasFoy = repasFoy;
        setText("Modifier");
        addActionListener(new ActionModifierPrix());
    }

    class ActionModifierPrix implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Créer la boite de dialogue
            JPanel panel = new JPanel(new GridLayout(3,1));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Message de confirmation
            JLabel messageLabel = new JLabel("<html>Veuillez Saisir le nouveau prix pour "
                    +"<strong>"+ repasFoy.getNom()+ "</strong></html> ");
            panel.add(messageLabel);

            JTextField newPrix = new JTextField(""+repasFoy.getPrix()+" €");
            panel.add(newPrix);
            // Afficher la boite de dialogue
            int result = JOptionPane.showConfirmDialog(null, panel, "Modifier le prix",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Implémentation des modifications
            if (result == JOptionPane.OK_OPTION) {
                try {
                    double nouveauPrix = Double.parseDouble(newPrix.getText());
                    repasFoy.setPrix(nouveauPrix);

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null,
                            "<html style='color: red;'>Veuillez saisir un montant valide pour le prix de "
                                    + repasFoy.getNom()+ "</html>", "Erreur",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (NullPointerException e1){
                    JOptionPane.showMessageDialog(null,
                            "<html style='color: red;'>Aucun montant saisi "
                                    + repasFoy.getNom()+ "</html>", "Erreur",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }
}
