package n7simulator;

import n7simulator.modele.*;
import n7simulator.modele.Evenements.Evenement;
import n7simulator.modele.Evenements.Evenement_Irregu;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;
import n7simulator.vue.*;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.vue.temps.TempsGUI;
import n7simulator.controller.*;
import n7simulator.database.CreationBddAdmin;
import n7simulator.database.ValDebPartieDAO;

import java.time.LocalDate;

public class N7Simulator {
	public static void main(String[] args) {
		CreationBddAdmin.initialiserBddAdmin();
		Temps temps = new Temps();
		ValDebPartieDAO.initialiserDonneesDebutPartie(temps);
		Partie laPartie = Partie.getInstance();
		TempsGUI interfaceTemps = new TempsGUI(temps);
		temps.addObserver(interfaceTemps);
		TempsController controllerTemps = new TempsController(temps);

		// Les jauges
		Jauge argent = laPartie.getJaugeArgent();
		Jauge bonheur = laPartie.getJaugeBonheur();
		Jauge pedagogie = laPartie.getJaugePedagogie();

		JaugesPannel jaugesPannel = new JaugesPannel(bonheur.getValue(), pedagogie.getValue(), argent.getValue());

		argent.addObserver(jaugesPannel.getVueArgent());
		bonheur.addObserver(jaugesPannel.getVueBonheur());
		pedagogie.addObserver(jaugesPannel.getVuePedagogie());

		PilotageGUI interfacePilotage = new PilotageGUI(interfaceTemps, controllerTemps, jaugesPannel, new EventHistoryGUI());
		CarteGUI interfaceCarte = new CarteGUI();
		N7Frame fenetre = new N7Frame(interfaceCarte, interfacePilotage);

		LocalDate date = LocalDate.of(2020, 1, 1);
		Evenement eventTest = new Evenement_Irregu(1, date);
		fenetre.ajouterEvenement(eventTest);

	}
}
