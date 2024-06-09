package n7simulator.controller;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import n7simulator.modele.Bibliotheque;

@SuppressWarnings("serial")
public class BibliothequeController extends JPanel {
    private JSpinner spinnerNbLivreAchete;
    private JSpinner spinnerNbLivreVendu;
    private JLabel coutA;
    private JLabel coutV;

    public BibliothequeController() {
        Bibliotheque instanceBiblio = Bibliotheque.getInstance(0);

        // Création du contenant qui va contenir les éléments du controller
        JPanel contenant = new JPanel(new GridLayout(2, 1));

        // création et ajout des espaces de vente et achat de livre
        contenant.add(createAchatSection(instanceBiblio));
        contenant.add(createVenteSection(instanceBiblio));

        int result = JOptionPane.showConfirmDialog(null, contenant, "Modifier le nombre de livres",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            int newNbLivre = instanceBiblio.getNbLivre() + (int) spinnerNbLivreAchete.getValue() - (int) spinnerNbLivreVendu.getValue();
            instanceBiblio.setNbLivre(newNbLivre);
        }
    }

    /**
     * Fonction permettant la création de la partie de selection d'achat
     * @param instanceBiblio
     * @return
     */
    private JPanel createAchatSection(Bibliotheque instanceBiblio) {
        JPanel prixAchatLivre = new JPanel(new GridLayout(1, 2));
        JPanel nbAchat = new JPanel();
        nbAchat.setLayout(new BoxLayout(nbAchat, BoxLayout.Y_AXIS));

        Box boxAchat = Box.createHorizontalBox();
        boxAchat.add(new JLabel("Nombre de Livre à acheter :"));
        boxAchat.add(Box.createHorizontalGlue());

        spinnerNbLivreAchete = new JSpinner(new SpinnerNumberModel(0, 0, 1500, 1));
        spinnerNbLivreAchete.addChangeListener(e -> updatePrixTransactionVente(spinnerNbLivreAchete, coutA));

        nbAchat.add(boxAchat);
        nbAchat.add(spinnerNbLivreAchete);
        prixAchatLivre.add(nbAchat);

        JPanel coutAchat = new JPanel();
        coutAchat.setLayout(new BoxLayout(coutAchat, BoxLayout.Y_AXIS));
        JLabel titleAchat = new JLabel("Cout d'achat total :");
        titleAchat.setAlignmentX(CENTER_ALIGNMENT);

        coutA = new JLabel();
        coutA.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        coutAchat.add(titleAchat);
        coutAchat.add(coutA);
        prixAchatLivre.add(coutAchat);

        return prixAchatLivre;
    }

    private JPanel createVenteSection(Bibliotheque instanceBiblio) {
        JPanel prixVenteLivre = new JPanel(new GridLayout(1, 2));
        JPanel nbVente = new JPanel();
        nbVente.setLayout(new BoxLayout(nbVente, BoxLayout.Y_AXIS));

        Box boxVente = Box.createHorizontalBox();
        boxVente.add(new JLabel("Nombre de Livre à vendre :"));
        boxVente.add(Box.createHorizontalGlue());

        spinnerNbLivreVendu = new JSpinner(new SpinnerNumberModel(0, 0, instanceBiblio.getNbLivre(), 1));
        spinnerNbLivreVendu.addChangeListener(e -> updatePrixTransactionVente(spinnerNbLivreVendu, coutV));

        nbVente.add(boxVente);
        nbVente.add(spinnerNbLivreVendu);
        prixVenteLivre.add(nbVente);

        JPanel coutVente = new JPanel();
        coutVente.setLayout(new BoxLayout(coutVente, BoxLayout.Y_AXIS));
        JLabel titleVente = new JLabel("Gain de revente total :");
        titleVente.setAlignmentX(CENTER_ALIGNMENT);

        coutV = new JLabel();
        coutV.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        coutVente.add(titleVente);
        coutVente.add(coutV);
        prixVenteLivre.add(coutVente);

        return prixVenteLivre;
    }

    private void updatePrixTransactionVente(JSpinner spinner, JLabel cout) {
        int nbVendu = (int) spinner.getValue();
        int valeur = nbVendu * Bibliotheque.getInstance(0).getPrixLivre();
        cout.setText(String.format("%d €", valeur));
    }

    public static JButton getBoutonOuverture() {
        JButton leBouton = new JButton("Modifier le nombre de livres");
        leBouton.addActionListener(e -> new BibliothequeController());
        return leBouton;
    }
}