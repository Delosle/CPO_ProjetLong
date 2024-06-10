package n7simulator.vue.Evenement;

import n7simulator.modele.evenements.Evenement;
import n7simulator.vue.PilotageGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class EvenementGUI extends JFrame {
    protected String titre;
    protected String description;
    protected int impactBonheur;
    protected int impactArgent;
    protected int impactPedagogie;

    protected PilotageGUI pilotageGUI;

    protected JPanel panelChoix = new JPanel();
    protected JPanel popupPanel = new JPanel();
    public JPanel getpopupPanel() {
        return popupPanel;
    }


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

        JPanel zone_Impacts = new JPanel();
        zone_Impacts.setLayout(new GridLayout(1, 3));

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


        zone_Impacts.add(panelBonheur);
        zone_Impacts.add(panelArgent);
        zone_Impacts.add(panelPedagogie);
        zone_Impacts.setBorder(new EmptyBorder( 0, 60, 0, 0));
        //zone_Impacts.setBackground(Color.GREEN); // A commenter, sert a modif du GridBagLayout

        // On met en place le layer de la popup, en y ajoutant les differents elements
        JPanel popupPanel = new JPanel();
        GridBagLayout minigridBag = new GridBagLayout();
        GridBagConstraints mini_c = new GridBagConstraints();
        //popupPanel.setBackground(Color.YELLOW);
        popupPanel.setLayout(minigridBag);
        mini_c.fill = GridBagConstraints.BOTH;

        mini_c.gridy = 0;
        mini_c.weighty=0.1;
        mini_c.weightx=1;
        minigridBag.setConstraints(panelTitre, mini_c);
        popupPanel.add(panelTitre);

        mini_c.gridy = 1;
        mini_c.weighty=0.35;
        mini_c.weightx=1;
        minigridBag.setConstraints(panelDescription, mini_c);
        popupPanel.add(panelDescription);

        mini_c.gridy = 2;
        mini_c.weighty=0.3;
        mini_c.weightx=1;
        minigridBag.setConstraints(zone_Impacts, mini_c);
        popupPanel.add(zone_Impacts);

        mini_c.gridy = 3;
        mini_c.weighty=0.3;
        minigridBag.setConstraints(panelChoix, mini_c);
        popupPanel.add(panelChoix);
        add(popupPanel);
    }

    public JPanel getPermanent() {
        JPanel miniPanel = new JPanel();
        JPanel panelTitre = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        gridLayout.setHgap(20);
        miniPanel.setLayout(gridLayout);

        JLabel titre = new JLabel(this.titre);
        panelTitre.add(titre);

        JPanel zone_Impacts = new JPanel();
        zone_Impacts.setLayout(new GridLayout(1, 3));

        JLabel impactBonheurText = new JLabel("" + this.impactBonheur);
        impactBonheurText.setForeground(new Color(212, 0, 253));

        JLabel impactPedagogieText = new JLabel("" + this.impactPedagogie);
        impactPedagogieText.setForeground(Color.BLUE);

        zone_Impacts.add(impactBonheurText);
        zone_Impacts.add(impactPedagogieText);
        zone_Impacts.add(new JLabel("" + this.impactArgent));

        miniPanel.add(panelTitre);
        miniPanel.add(zone_Impacts);

        return miniPanel;
    }
}
