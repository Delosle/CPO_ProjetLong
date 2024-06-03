package n7simulator.controller.startmenu;

import java.awt.Window;

import javax.swing.SwingUtilities;

public class ChargerPartieBouton extends StartMenuBouton {

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
