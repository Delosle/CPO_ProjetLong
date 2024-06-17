package n7simulator.controller.startmenu;

/**
 * Classe représentant le bouton de nouvelle partie dans le menu principal du jeu
 */
public class NouvellePartieBouton extends StartMenuBouton {

	/**
	 * Obtenir le bouton permettant d'ouvrir l'interface d'une nouvelle partie
	 */
	public NouvellePartieBouton() {
		super("Nouvelle partie", 0x3CC060);
	}

	@Override
	protected void creationFormulairePartie() {
		new NouvellePartieFormulaire(this);
	}

}
