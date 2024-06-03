package n7simulator.controller.startmenu;

import java.awt.Window;

import javax.swing.SwingUtilities;

public class NouvellePartieBouton extends StartMenuBouton {

	public NouvellePartieBouton() {
		super("Nouvelle partie", 0x3CC060);
	}

	@Override
	protected void creationFormulairePartie() {
		new NouvellePartieFormulaire();
		Window win = SwingUtilities.getWindowAncestor(this.getParent());
		win.dispose();
	}

}
