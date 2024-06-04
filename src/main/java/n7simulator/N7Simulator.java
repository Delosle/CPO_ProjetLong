package n7simulator;

import n7simulator.database.CreationBddAdmin;
import n7simulator.modele.*;
import n7simulator.modele.evenements.Evenement;
import n7simulator.modele.evenements.Evenement_Irregu;
import n7simulator.modele.jauges.Jauge;
import n7simulator.vue.*;
import n7simulator.vue.Evenement.EvenementGUI;
import n7simulator.vue.Evenement.EventHistoryGUI;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.vue.temps.TempsGUI;
import n7simulator.controller.*;
import n7simulator.database.ValDebPartieDAO;
import n7simulator.modele.evenements.ApparitionEvenementIrregulier;

import java.time.LocalDate;

public class N7Simulator {


	public static void main(String[] args) {

		CreationBddAdmin.initialiserBddAdmin(); //pensez à décommenter cette ligne pour initialiser la base de données
		Partie laPartie = Partie.getInstance();
		Temps temps = laPartie.getTemps();
		ValDebPartieDAO.initialiserDonneesDebutPartie(temps);
		TempsGUI interfaceTemps = new TempsGUI(temps);
		temps.addObserver(interfaceTemps);
		TempsController controllerTemps = new TempsController(temps);
		Crous leCrous = Crous.getInstance(1, 1.30);

		// Les jauges
		Jauge argent = laPartie.getJaugeArgent();
		Jauge bonheur = laPartie.getJaugeBonheur();
		Jauge pedagogie = laPartie.getJaugePedagogie();

		JaugesPannel jaugesPannel = new JaugesPannel(bonheur.getValue(), pedagogie.getValue(), argent.getValue());
		ApparitionEvenementIrregulier gestionnaireEvenementIrregulier = new ApparitionEvenementIrregulier();
		argent.addObserver(jaugesPannel.getVueArgent());
		bonheur.addObserver(jaugesPannel.getVueBonheur());
		pedagogie.addObserver(jaugesPannel.getVuePedagogie());

		PilotageGUI interfacePilotage = new PilotageGUI(interfaceTemps, controllerTemps, jaugesPannel, new EventHistoryGUI());
		CarteGUI interfaceCarte = new CarteGUI();

		N7Frame fenetre = N7Frame.getInstance(interfaceCarte, interfacePilotage);
	}

}
