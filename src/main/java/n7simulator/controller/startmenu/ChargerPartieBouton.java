package n7simulator.controller.startmenu;

import java.awt.Window;

import javax.swing.SwingUtilities;

/**
 * Classe représentant le bouton "charger" dans le menu principal
 */
public class ChargerPartieBouton extends StartMenuBouton {

	/**
	 * Obtenir un bouton permettant d'ouvrir l'interface de chargement du jeu
	 */
	public ChargerPartieBouton() {
		super("Charger une partie", 0x3561D0);
	}

	@Override
	protected void creationFormulairePartie() {
		Window win = SwingUtilities.getWindowAncestor(this.getParent());
		win.dispose();
		new ChargementSauvegardeFormulaire();
	}

}
