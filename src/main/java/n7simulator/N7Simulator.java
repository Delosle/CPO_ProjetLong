package n7simulator;

import n7simulator.controller.TempsController;
import n7simulator.database.CreationBddAdmin;
import n7simulator.database.CreerBddSauvegarde;
import n7simulator.database.ValDebPartieDAO;
import n7simulator.modele.Partie;
import n7simulator.modele.Temps;
import n7simulator.modele.jauges.Jauge;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;
import n7simulator.vue.PilotageGUI;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.vue.startmenu.StartMenuGUI;
import n7simulator.vue.temps.TempsGUI;

public class N7Simulator {

	private static Temps tempsPartie;

	public static void main(String[] args) {
		CreationBddAdmin.initialiserBddAdmin();
		CreerBddSauvegarde.initialiserBddSauvegarde();
		tempsPartie = new Temps();
		affichageMenu();

	}

	private static void affichageMenu() {
		new StartMenuGUI();
	}

	public static void initNouvellePartie() {
		ValDebPartieDAO.initialiserDonneesDebutPartie(tempsPartie);
		affichageCarte();
	}

	private static void affichageCarte() {
		Partie laPartie = Partie.getInstance();
		TempsGUI interfaceTemps = new TempsGUI(tempsPartie);
		tempsPartie.addObserver(interfaceTemps);
		TempsController controllerTemps = new TempsController(tempsPartie);

		// Les jauges
		Jauge argent = laPartie.getJaugeArgent();
		Jauge bonheur = laPartie.getJaugeBonheur();
		Jauge pedagogie = laPartie.getJaugePedagogie();

		JaugesPannel jaugesPannel = new JaugesPannel(bonheur.getValue(), pedagogie.getValue(), argent.getValue());

		argent.addObserver(jaugesPannel.getVueArgent());
		bonheur.addObserver(jaugesPannel.getVueBonheur());
		pedagogie.addObserver(jaugesPannel.getVuePedagogie());

		PilotageGUI interfacePilotage = new PilotageGUI(interfaceTemps, controllerTemps, jaugesPannel);
		CarteGUI interfaceCarte = new CarteGUI();
		N7Frame fenetre = new N7Frame(interfaceCarte, interfacePilotage);
	}
}
