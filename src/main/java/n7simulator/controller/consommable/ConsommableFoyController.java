package n7simulator.controller.consommable;

import n7simulator.joursuivant.ImpactConsommableFoy;
import n7simulator.joursuivant.JourSuivant;
import n7simulator.modele.consommableFoy.ConsommableFoy;
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

public class ConsommableFoyController extends JPanel {

    /**
     * On veut garder une copie des repas à l'initialisation
     */
    List<ConsommableFoy> consommableFoys;



    public ConsommableFoyController(List<ConsommableFoy> dataRepas){
        super(new GridLayout(dataRepas.size(), 1));

        ImpactConsommableFoy impact = ImpactConsommableFoy.getInstance();
        JourSuivant.getInstance().addImpact(impact);

        this.consommableFoys = new ArrayList<ConsommableFoy>();

        // on recupère les consommables en modèle; initialise les vues ainsi que
        // les controleurs pour chaque consommable.
        for (ConsommableFoy consommableFoy : dataRepas) {

            this.consommableFoys.add(consommableFoy.copieFoy(consommableFoy));
            ConsommableGUI vueRepas = new ConsommableGUI(consommableFoy.getNom(),consommableFoy.getPrix(),consommableFoy.getImage());
            consommableFoy.addObserver(vueRepas);
            JPanel repasVC = new JPanel();
            repasVC.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));

            JPanel vueControl = creeVueControlFoy(consommableFoy, vueRepas);
            add(vueControl);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // On récupère les dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setAutoscrolls(true);

        scrollPane.setPreferredSize(new Dimension((int)(width/2.5), (int)(height/2)));
        // Afficher la boite de dialogue
        int result = JOptionPane.showOptionDialog(null, scrollPane, "Consommables foy"
                        ,JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, null, null);

        // Implémentation des modifications si non validation
        if (result == JOptionPane.CANCEL_OPTION) {
            for (int i= 0; i < dataRepas.size(); i++){
                dataRepas.get(i).setPrix(consommableFoys.get(i).getPrix());
            }
        }

        //calcul des impacts ( Bonheur et Argent)
        JourSuivant jourSuivant = JourSuivant.getInstance();

        double impactBonheur = 0;
        double impactArgent = 0;
        for(ConsommableFoy consommableFoy: dataRepas){
            impactArgent += consommableFoy.getMarge();
            impactBonheur += consommableFoy.getDiff();
        }

        //pour une journée l'impact max
        impactBonheur = impactBonheur > 10 ? 10 : impactBonheur;

        impact.setImpactArgent(impactArgent);
        impact.setImpactBonheur(impactBonheur);
    }

    /**
     * Créer un JPannel contenant la vue et le bouton pour modifier un consommableFoy
     * @param consommableFoy le consommable en modèle
     * @param vueRepas la vue du consommable
     * @return le JPannel contenant le MVC du consommable.
     */
    private static JPanel creeVueControlFoy(ConsommableFoy consommableFoy, ConsommableGUI vueRepas){
        JPanel monPanel = new JPanel(new GridLayout(2, 1));

        monPanel.setPreferredSize(new Dimension(500, 200));
        JButton boutonModifierPrixRepas = creerModifierPrixBouton(consommableFoy);
        JPanel panelMofierPrix = new JPanel();
        panelMofierPrix.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        panelMofierPrix.add(boutonModifierPrixRepas);
        monPanel. add(vueRepas);
        monPanel.add(panelMofierPrix);
        Border bottomBorder = new MatteBorder(0, 0, 2, 0, new Color(141, 111, 0));
        monPanel.setBorder(bottomBorder);

        return monPanel;
    }

    /**
     * Créer le bouton qui ouvre le controleur modifierPrix pour un consommable
     * @param consommableFoy le consommable en tant que modèle
     * @return le bouton.
     */
    public static JButton creerModifierPrixBouton(ConsommableFoy consommableFoy){
        JButton leButton = new JButton("Modifier");
        class ActionModifierPrix implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Créer la boite de dialogue
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
        leButton.addActionListener(new ActionModifierPrix());
        return  leButton;
    }

    /**
     * Cette fonction permet de créer le Bouton qui ouvrira le controleur pour l'ensemble
     * des consommables.
     * @param consommableFoys les consommables;
     * @return Le bouton qui ouvre le controler sur les consommables.
     */
    public static JButton getBoutonOuverture(List<ConsommableFoy> consommableFoys){
        JButton modifier = new JButton("Modifier");
        ActionListener ouvrirModification = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsommableFoyController fenetre = new ConsommableFoyController(consommableFoys);
            }
        };
        modifier.addActionListener(ouvrirModification);
        return modifier;
    }



}
