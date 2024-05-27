package n7simulator;

import n7simulator.modele.*;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;
import n7simulator.vue.*;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.controller.*;

public class N7Simulator {
	   public static void main (String[] args){	
			Partie laPartie = new Partie();
			TempsGUI interfaceTemps = new TempsGUI(laPartie);
			laPartie.addObserver(interfaceTemps);
			TempsController controllerTemps = new TempsController(laPartie);

			// Les jauges
			Jauge argent = laPartie.jaugeArgent;
			Jauge bonheur = laPartie.jaugeBonheur;
			Jauge pedagogie = laPartie.jaugePedagogie;

			JaugesPannel jaugesPannel = new JaugesPannel(bonheur.getValue(), pedagogie.getValue(), argent.getValue());

			PilotageGUI interfacePilotage = new PilotageGUI(interfaceTemps, controllerTemps, jaugesPannel);
			CarteGUI interfaceCarte = new CarteGUI();
			N7Frame fenetre = new N7Frame(interfaceCarte, interfacePilotage);
	   }
}
