package n7simulator.vue;

import n7simulator.modele.Evenements.Evenement;

import javax.swing.*;
import java.awt.*;

public class EvenementGUI extends JPanel {
    private String titre;
    private String description;
    private int impactBonheur;
    private int impactArgent;
    private int impactPedagogie;

    public EvenementGUI(Evenement evenement){
        // On recupere les valeurs de l'evenement a afficher
        this.titre = evenement.getTitre();
        this.description = evenement.getDescription();
        this.impactBonheur = evenement.getImpactBonheur();
        this.impactArgent = evenement.getImpactArgent();
        this.impactPedagogie = evenement.getImpactPedagogie();

        // On recupere les dimensions de l'ecran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        // On definie les dimension de la fenetre d'evenement
        Integer popUpLargeur = (int)width * 1 / 2;
        Integer popUpHauteur = (int)height * 1 / 2;
        setSize(popUpLargeur, popUpHauteur);

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

        setLayout(new GridLayout(4,1));
        this.add(partieTitre);
        this.add(partieDescription);
        this.add(zone_Impacts);
    }

    public JPanel getPermanent(){
        JPanel miniPanel = new JPanel();
    }
}
