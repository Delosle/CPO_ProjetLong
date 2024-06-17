package n7simulator.vue.Evenement;

import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EvenementGUI extends JFrame {
	
    protected String titre;
    protected String description;
    protected int impactBonheur;
    protected int impactArgent;
    protected int impactPedagogie;

    protected PilotageGUI pilotageGUI;
    protected JPanel panelChoix = new JPanel();

    /**
     * Constructeur
     * @param evenement l'evenement a afficher
     * @param pilotageGUI la partie à droite dans l'interface principale
     */
    public EvenementGUI(Evenement evenement, PilotageGUI pilotageGUI) {

        // On recupere les valeurs de l'evenement a afficher
        this.titre = evenement.getTitre();
        this.description = evenement.getDescription();
        this.impactBonheur = evenement.getImpactBonheur();
        this.impactArgent = evenement.getImpactArgent();
        this.impactPedagogie = evenement.getImpactPedagogie();

        this.pilotageGUI = pilotageGUI;

        // On recupere les dimensions de l'ecran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        // On definie les dimension de la fenetre d'evenement
        int popUpLargeur = (int)width * 1 / 2;
        int popUpHauteur = (int)height * 1 / 2;
        setSize(popUpLargeur, popUpHauteur);
        setLocation(popUpLargeur/4, popUpHauteur/2);


        // On definie les elements qui seront presents dans la popup
        JLabel partieTitre = new JLabel("" + titre);
        JPanel panelTitre = new JPanel();
        panelTitre.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTitre.add(partieTitre);
        panelTitre.setBorder(new EmptyBorder( 0, 10, 0, 0));
        //panelTitre.setBackground(Color.BLUE); // A commenter, sert a modif du GridBagLayout

        JTextArea partieDescription = new JTextArea("" + description);
        partieDescription.setLineWrap(true);
        partieDescription.setWrapStyleWord(true);
        partieDescription.setEditable(false);
        partieDescription.setOpaque(false);
        partieDescription.getCaret().setVisible(false);
        partieDescription.setFocusable(false);
        partieDescription.setFont(javax.swing.UIManager.getDefaults().getFont("Label.font"));
        partieDescription.setBorder(new EmptyBorder( 0, 15, 0, 15));

        JPanel panelDescription = new JPanel();
        panelDescription.setLayout(new BorderLayout());

        panelDescription.add(partieDescription, BorderLayout.CENTER);
        //panelDescription.setBackground(Color.RED);// A commenter, sert a modif du GridBagLayout

        JPanel zoneImpacts = new JPanel();
        zoneImpacts.setLayout(new GridLayout(1, 3));

        JLabel impactBonheurText = new JLabel("Impact Bonheur : " + impactBonheur);
        impactBonheurText.setForeground(new Color(212, 0, 253));
        JPanel panelBonheur = new JPanel();
        panelBonheur.setLayout(new BorderLayout());
        panelBonheur.add(impactBonheurText, BorderLayout.CENTER);

        JLabel impactArgentText = new JLabel("Impact Argent : " + impactArgent);
        impactArgentText.setForeground(new Color(141, 111, 0));
        JPanel panelArgent = new JPanel();
        panelArgent.setLayout(new BorderLayout());
        panelArgent.add(impactArgentText, BorderLayout.CENTER);

        JLabel impactPedagogieText = new JLabel("Impact Pedagogie : " + impactPedagogie);
        impactPedagogieText.setForeground(Color.BLUE);
        JPanel panelPedagogie = new JPanel();
        panelPedagogie.setLayout(new BorderLayout());
        panelPedagogie.add(impactPedagogieText, BorderLayout.CENTER);


        zoneImpacts.add(panelBonheur);
        zoneImpacts.add(panelArgent);
        zoneImpacts.add(panelPedagogie);
        zoneImpacts.setBorder(new EmptyBorder( 0, 60, 0, 0));

        // On met en place le layer de la popup, en y ajoutant les differents elements
        JPanel popupPanel = new JPanel();
        GridBagLayout minigridBag = new GridBagLayout();
        GridBagConstraints minigridConstraints = new GridBagConstraints();
        popupPanel.setLayout(minigridBag);
        minigridConstraints.fill = GridBagConstraints.BOTH;

        minigridConstraints.gridy = 0;
        minigridConstraints.weighty=0.1;
        minigridConstraints.weightx=1;
        minigridBag.setConstraints(panelTitre, minigridConstraints);
        popupPanel.add(panelTitre);

        minigridConstraints.gridy = 1;
        minigridConstraints.weighty=0.35;
        minigridConstraints.weightx=1;
        minigridBag.setConstraints(panelDescription, minigridConstraints);
        popupPanel.add(panelDescription);

        minigridConstraints.gridy = 2;
        minigridConstraints.weighty=0.3;
        minigridConstraints.weightx=1;
        minigridBag.setConstraints(zoneImpacts, minigridConstraints);
        popupPanel.add(zoneImpacts);

        minigridConstraints.gridy = 3;
        minigridConstraints.weighty=0.3;

        minigridBag.setConstraints(panelChoix, minigridConstraints);
        popupPanel.add(panelChoix);

        add(popupPanel);

    }

    /**
     * Crée un panel propre à tous les événements.
     * @return
     */
    public JPanel getPermanent() {
        JPanel miniPanel = new JPanel();
        JPanel panelTitre = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        gridLayout.setHgap(20);
        miniPanel.setLayout(gridLayout);

        JLabel titreLabel = new JLabel(this.titre);
        panelTitre.add(titreLabel);

        JPanel zoneImpactsPanel = new JPanel();
        zoneImpactsPanel.setLayout(new GridLayout(1, 3));

        JLabel impactBonheurText = new JLabel("" + this.impactBonheur);
        impactBonheurText.setForeground(new Color(212, 0, 253));

        JLabel impactPedagogieText = new JLabel("" + this.impactPedagogie);
        impactPedagogieText.setForeground(Color.BLUE);

        zoneImpactsPanel.add(impactBonheurText);
        zoneImpactsPanel.add(impactPedagogieText);
        zoneImpactsPanel.add(new JLabel("" + this.impactArgent));

        miniPanel.add(panelTitre);
        miniPanel.add(zoneImpactsPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Dimension dimensionEvent = new Dimension((int) (width * 0.3), (int)(height * 0.05));
        miniPanel.setSize(dimensionEvent);
        miniPanel.setMaximumSize(dimensionEvent);
        miniPanel.setMinimumSize(dimensionEvent);
        
        return miniPanel;
    }
}
