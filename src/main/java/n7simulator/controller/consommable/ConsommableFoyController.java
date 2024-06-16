package n7simulator.controller.consommable;

import n7simulator.joursuivant.JourSuivant;
import n7simulator.modele.Partie;
import n7simulator.modele.foy.ConsommableFoy;
import n7simulator.modele.foy.Foy;
import n7simulator.vue.consommable.ConsommableGUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controleur des consommables du foy
 */
public class ConsommableFoyController extends JPanel {

    /**
     * Copie des consommables à l'initialisation
     */
    private List<ConsommableFoy> consommableFoys;

    /**
     * Crée un controleur des consommables du foy
     */
    public ConsommableFoyController(){
    	Foy foy = Partie.getInstance().getFoy();
    	List<ConsommableFoy> consommables = foy.getConsommables();
        this.setLayout(new GridLayout(consommables.size(), 1));

        this.consommableFoys = new ArrayList<>();

        // Récupération des consommables du modèle et initialisation des vues ainsi que
        // des controleurs pour chaque consommable.
        for (ConsommableFoy consommableFoy : consommables) {
            this.consommableFoys.add(ConsommableFoy.copieFoy(consommableFoy));
            ConsommableGUI vueRepas = new ConsommableGUI(consommableFoy.getNom(),consommableFoy.getPrix(),consommableFoy.getImage());
            consommableFoy.addObserver(vueRepas);
            JPanel repasVC = new JPanel();
            repasVC.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));

            JPanel vueControl = creeVueControlFoy(consommableFoy, vueRepas);
            add(vueControl);
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Récupération des dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        this.setAutoscrolls(true);

        scrollPane.setPreferredSize(new Dimension((int)(width/2.5), (int)(height/2)));
        // Afficher la boite de dialogue
        int result = JOptionPane.showOptionDialog(null, scrollPane, "Consommables foy"
                        ,JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, null, null);

        // Si l'utilisateur clique sur annuler, restaure les prix
        if (result == JOptionPane.CANCEL_OPTION) {
            for (int i= 0; i < consommables.size(); i++){
            	consommables.get(i).setPrix(consommableFoys.get(i).getPrix());
            }
        }

        // Calcul des impacts ( Bonheur et Argent)
        double impactBonheur = 0;
        double impactArgent = 0;
        for(ConsommableFoy consommableFoy: consommables){
            impactArgent += consommableFoy.getMarge();
            impactBonheur += consommableFoy.getDiff();
        }
        //pour une journée l'impact max
        impactBonheur = impactBonheur > 10 ? 10 : impactBonheur;
        
        JourSuivant.getInstance().addImpact(foy);
        foy.setImpactArgent(impactArgent);
        foy.setImpactBonheur(impactBonheur);
    }

    /**
     * Créer un JPanel contenant la vue et le bouton pour modifier un consommableFoy
     * @param consommableFoy le consommable en modèle
     * @param vueRepas la vue du consommable
     * @return le JPanel contenant le MVC du consommable.
     */
    private static JPanel creeVueControlFoy(ConsommableFoy consommableFoy, ConsommableGUI vueRepas){
        JPanel monPanel = new JPanel(new GridLayout(2, 1));

        monPanel.setPreferredSize(new Dimension(500, 200));
        JButton boutonModifierPrixRepas = creerModifierPrixBouton(consommableFoy);
        JPanel panelModifierPrix = new JPanel();
        panelModifierPrix.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        panelModifierPrix.add(boutonModifierPrixRepas);
        monPanel.add(vueRepas);
        monPanel.add(panelModifierPrix);
        Border bottomBorder = new MatteBorder(0, 0, 2, 0, new Color(141, 111, 0));
        monPanel.setBorder(bottomBorder);

        return monPanel;
    }

    /**
     * Crée le bouton qui ouvre le controleur modifierPrix pour un consommable.
     * @param consommableFoy le consommable en tant que modèle
     * @return le bouton.
     */
    public static JButton creerModifierPrixBouton(ConsommableFoy consommableFoy){
        JButton boutonModifier = new JButton("Modifier");
        class ActionModifierPrix implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Création de la boite de dialogue
                JPanel panel = new JPanel(new GridLayout(3,1));
                panel.setBorder(new EmptyBorder(10, 10, 10, 10));

                // Message de confirmation
                JLabel messageLabel = new JLabel("<html>Veuillez Saisir le nouveau prix pour "
                        +"<strong>"+ consommableFoy.getNom()+ "</strong></html> ");
                panel.add(messageLabel);

                JTextField newPrix = new JTextField(""+consommableFoy.getPrix());
                panel.add(newPrix);
                // Afficher la boite de dialogue
                int result = JOptionPane.showConfirmDialog(null, panel, "Modifier le prix",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Implémentation des modifications
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double nouveauPrix = Double.parseDouble(newPrix.getText());
                        if(nouveauPrix < 0){
                            throw new NumberFormatException("prix négatif");
                        }
                        consommableFoy.setPrix(nouveauPrix);

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null,
                                "<html><font style='color: red;'>Veuillez saisir un montant valide pour le prix de "
                                        + consommableFoy.getNom()+ "</font></html>", "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (NullPointerException e1){
                        JOptionPane.showMessageDialog(null,
                                "<html><font style='color: red;'>Aucun montant saisi "
                                        + consommableFoy.getNom()+ "</font></html>", "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        boutonModifier.addActionListener(new ActionModifierPrix());
        return boutonModifier;
    }

}
