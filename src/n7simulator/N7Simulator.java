package n7simulator;

import n7simulator.modele.*;
import n7simulator.vue.*;
import n7simulator.controller.*;

public class N7Simulator {
	   public static void main (String[] args){
		     Temps temps = new Temps();
		     TempsGUI interfaceTemps = new TempsGUI(temps);
		     TempsController controllerTemps = new TempsController(temps);
		     temps.addObserver(interfaceTemps);
		     PilotageGUI interfacePilotage = new PilotageGUI(interfaceTemps, controllerTemps);
		     CarteGUI interfaceCarte = new CarteGUI();
		     N7Frame fenetre = new N7Frame(interfaceCarte, interfacePilotage);
	   }
}
