package n7simulator.controller.repas;

import n7simulator.joursuivant.ImpactConsommableFoy;
import n7simulator.joursuivant.JourSuivant;
import n7simulator.modele.ConsommableFoy;
import n7simulator.vue.consommable.ConsommableGUI;

import javax.swing.*;
import javax.swing.border.Border;
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

    ImpactConsommableFoy impact;
    public ConsommableFoyController(List<ConsommableFoy> dataRepas){
        super(new GridLayout(dataRepas.size(), 1));

        impact = ImpactConsommableFoy.getInstance();
        JourSuivant.getInstance().addImpact(impact);

        this.consommableFoys = new ArrayList<ConsommableFoy>();

        // on crèe recupère les consommables en modèle; initialise les vues ainsi que
        // les controleurs pour chaque consommables.
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
        BoutonModifierPrixFoy boutonModifierPrixRepas = new BoutonModifierPrixFoy(consommableFoy);
        JPanel panelMofierPrix = new JPanel();
        panelMofierPrix.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        panelMofierPrix.add(boutonModifierPrixRepas);
        monPanel. add(vueRepas);
        monPanel.add(panelMofierPrix);
        Border bottomBorder = new MatteBorder(0, 0, 2, 0, new Color(141, 111, 0));
        monPanel.setBorder(bottomBorder);

        return monPanel;
    }

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
