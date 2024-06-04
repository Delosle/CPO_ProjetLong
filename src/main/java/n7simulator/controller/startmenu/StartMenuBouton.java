package n7simulator.controller.startmenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import n7simulator.style.StyleBoutonUI;

/**
 * Classe representant une abstraction des boutons du menu principal
 */
public abstract class StartMenuBouton extends JButton {

	/**
	 * Obtenir un bouton generique pour le menu principal
	 * @param texteBouton : le nom du bouton
	 * @param backgroundColor : la couleur de fond du bouton
	 */
	public StartMenuBouton(String texteBouton, int backgroundColor) {
		this.setText(texteBouton);
		this.setFont(new Font("Calibri", Font.BOLD, 14));
        this.setBackground(new Color(backgroundColor));
        this.setForeground(Color.white);
        this.setUI(new StyleBoutonUI());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addActionListener(new ActionDebutPartie());
	}

	/**
	 * Permet de creer un formulaire apres que le bouton soit cliqué
	 */
	protected abstract void creationFormulairePartie();


private class ActionDebutPartie implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		creationFormulairePartie();
	}

}

}

