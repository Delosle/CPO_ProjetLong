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

/**
 * Controleur de la bibliotheque
 */
public class GestionBibliothequeController extends JPanel {
	
    private JSpinner spinnerNbLivreAchete;
    private JSpinner spinnerNbLivreVendu;
    private JLabel labelCoutAchat;
    private JLabel labelCoutVente;

    public GestionBibliothequeController() {
        Bibliotheque instanceBiblio = Bibliotheque.getInstance(0);

        // Création du contenant qui va contenir les éléments du controller
        JPanel contenant = new JPanel(new GridLayout(2, 1));

        // création et ajout des espaces de vente et achat de livre
        contenant.add(createAchatSection());
        contenant.add(createVenteSection(instanceBiblio));

        int result = JOptionPane.showConfirmDialog(null, contenant, "Modifier le nombre de livres",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            int newNbLivre = instanceBiblio.getNbLivre() + (int) spinnerNbLivreAchete.getValue() - (int) spinnerNbLivreVendu.getValue();
            instanceBiblio.setNbLivre(newNbLivre);
        }
    }

    /**
     * Fonction permettant la création de la partie de sélection d'achat
     * @return un jpanel contenant la partie achat
     */
    private JPanel createAchatSection() {
        JPanel prixAchatLivre = new JPanel(new GridLayout(1, 2));
        JPanel nbAchat = new JPanel();
        nbAchat.setLayout(new BoxLayout(nbAchat, BoxLayout.Y_AXIS));

        Box boxAchat = Box.createHorizontalBox();
        boxAchat.add(new JLabel("Nombre de Livre à acheter :"));
        boxAchat.add(Box.createHorizontalGlue());

        spinnerNbLivreAchete = new JSpinner(new SpinnerNumberModel(0, 0, 1500, 1));
        spinnerNbLivreAchete.addChangeListener(e -> updatePrixTransactionVente(spinnerNbLivreAchete, labelCoutAchat));

        nbAchat.add(boxAchat);
        nbAchat.add(spinnerNbLivreAchete);
        prixAchatLivre.add(nbAchat);

        JPanel coutAchat = new JPanel();
        coutAchat.setLayout(new BoxLayout(coutAchat, BoxLayout.Y_AXIS));
        JLabel titleAchat = new JLabel("Cout d'achat total :");
        titleAchat.setAlignmentX(CENTER_ALIGNMENT);

        labelCoutAchat = new JLabel();
        labelCoutAchat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        coutAchat.add(titleAchat);
        coutAchat.add(labelCoutAchat);
        prixAchatLivre.add(coutAchat);

        return prixAchatLivre;
    }

    /**
     * Fonction permettant de créer la partie vente de livres
     * @param instanceBiblio
     * @return un jpanel contenant la partie vente
     */
    private JPanel createVenteSection(Bibliotheque instanceBiblio) {
        JPanel prixVenteLivre = new JPanel(new GridLayout(1, 2));
        JPanel nbVente = new JPanel();
        nbVente.setLayout(new BoxLayout(nbVente, BoxLayout.Y_AXIS));

        Box boxVente = Box.createHorizontalBox();
        boxVente.add(new JLabel("Nombre de Livre à vendre :"));
        boxVente.add(Box.createHorizontalGlue());

        spinnerNbLivreVendu = new JSpinner(new SpinnerNumberModel(0, 0, instanceBiblio.getNbLivre(), 1));
        spinnerNbLivreVendu.addChangeListener(e -> updatePrixTransactionVente(spinnerNbLivreVendu, labelCoutVente));

        nbVente.add(boxVente);
        nbVente.add(spinnerNbLivreVendu);
        prixVenteLivre.add(nbVente);

        JPanel coutVente = new JPanel();
        coutVente.setLayout(new BoxLayout(coutVente, BoxLayout.Y_AXIS));
        JLabel titleVente = new JLabel("Gain de revente total :");
        titleVente.setAlignmentX(CENTER_ALIGNMENT);

        labelCoutVente = new JLabel();
        labelCoutVente.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        coutVente.add(titleVente);
        coutVente.add(labelCoutVente);
        prixVenteLivre.add(coutVente);

        return prixVenteLivre;
    }

    /**
     * Met à jour le cout de la transaction des livres 
     * @param spinner
     * @param cout label
     */
    private void updatePrixTransactionVente(JSpinner spinner, JLabel cout) {
        int nbVendu = (int) spinner.getValue();
        int valeur = nbVendu * Bibliotheque.getInstance(0).getPrixLivre();
        cout.setText(String.format("%d €", valeur));
    }

    /**
     * Bouton pour ouvrir la fenêtre de gestion des livres
     * @return un bouton
     */
    public static JButton getBoutonOuverture() {
        JButton leBouton = new JButton("Modifier le nombre de livres");
        leBouton.addActionListener(e -> new GestionBibliothequeController());
        return leBouton;
    }
}