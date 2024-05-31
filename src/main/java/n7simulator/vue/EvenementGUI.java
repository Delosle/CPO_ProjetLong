package n7simulator.vue;

import n7simulator.modele.Evenements.Evenement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EvenementGUI extends JPanel {
    private String titre;
    private String description;
    private int impactBonheur;
    private int impactArgent;
    private int impactPedagogie;


    private N7Frame n7Frame;
    private PilotageGUI pilotageGUI;

    public EvenementGUI(Evenement evenement, N7Frame n7Frame, PilotageGUI pilotageGUI){
        // On recupere les valeurs de l'evenement a afficher
        this.titre = evenement.getTitre();
        this.description = evenement.getDescription();
        this.impactBonheur = evenement.getImpactBonheur();
        this.impactArgent = evenement.getImpactArgent();
        this.impactPedagogie = evenement.getImpactPedagogie();


        this.n7Frame = n7Frame;

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

        JLabel partieDescription = new JLabel("" + description);

        JPanel zone_Impacts = new JPanel();
        zone_Impacts.setLayout(new GridLayout(1, 3));

        JLabel impactBonheurText = new JLabel("Impact Bonheur : " + impactBonheur);
        impactBonheurText.setForeground(new Color(212, 0, 253));
        JLabel impactArgentText = new JLabel("Impact Argent : " + impactArgent);
        impactArgentText.setForeground(new Color(141, 111, 0));
        JLabel impactPedagogieText = new JLabel("Impact Pedagogie : " + impactPedagogie);
        impactPedagogieText.setForeground(Color.BLUE);

        zone_Impacts.add(impactBonheurText);
        zone_Impacts.add(impactArgentText);
        zone_Impacts.add(impactPedagogieText);

        JButton closeButton = new JButton("X");
        closeButton.setPreferredSize(new Dimension(20, 20));
        closeButton.setContentAreaFilled(false); // Rendre le bouton transparent
        closeButton.setBorderPainted(false); // Supprimer la bordure du bouton
        closeButton.setHorizontalAlignment(SwingConstants.RIGHT);
        closeButton.setForeground(Color.RED);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n7Frame.retirerLayer(EvenementGUI.this);
                pilotageGUI.enregistrerEvent(getPermanent());
            }
        });

        JPanel panelVide = new JPanel();

        JPanel miniZone = new JPanel();
        GridBagLayout minigridBag = new GridBagLayout();
        GridBagConstraints mini_c = new GridBagConstraints();
        miniZone.setLayout(minigridBag);

        mini_c.fill = GridBagConstraints.BOTH;

        //mini_c.gridwidth = 1;
        mini_c.gridy = 0;
        mini_c.weighty=0.25;
        minigridBag.setConstraints(partieTitre, mini_c);
        miniZone.add(partieTitre);

        mini_c.gridy = 1;
        mini_c.weighty=0.50;
        minigridBag.setConstraints(partieDescription, mini_c);
        miniZone.add(partieDescription);

        mini_c.gridy = 2;
        mini_c.weighty=0.3;
        minigridBag.setConstraints(zone_Impacts, mini_c);
        miniZone.add(zone_Impacts);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;
        c.weightx=1.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridbag.setConstraints(panelVide, c);
        add(panelVide);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(closeButton, c);
        add(closeButton);

        c.gridwidth = 1;
        c.weighty = 1.0;
        gridbag.setConstraints(miniZone, c);
        add(miniZone);


    }

    public JPanel getPermanent(){
        JPanel miniPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        gridLayout.setHgap(20);
        miniPanel.setLayout(gridLayout);

        JLabel titre = new JLabel(this.titre);
        JPanel zone_Impacts = new JPanel();
        zone_Impacts.setLayout(new GridLayout(1, 3));

        JLabel impactBonheurText = new JLabel("" + this.impactBonheur);
        impactBonheurText.setForeground(new Color(212, 0, 253));

        JLabel impactPedagogieText = new JLabel("" + this.impactPedagogie);
        impactPedagogieText.setForeground(Color.BLUE);

        zone_Impacts.add(impactBonheurText);
        zone_Impacts.add(impactPedagogieText);
        zone_Impacts.add(new JLabel("" + this.impactArgent));

        miniPanel.add(titre);
        miniPanel.add(zone_Impacts);

        return miniPanel;
    }
}
