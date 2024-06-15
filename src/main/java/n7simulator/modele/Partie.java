package n7simulator.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import n7simulator.database.ConsommableFoyDAO;
import n7simulator.database.ProfesseurDAO;
import n7simulator.joursuivant.JourSuivant;
import n7simulator.database.ValDebPartieDAO;
import n7simulator.modele.consommableFoy.ConsommablesFoy;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;
import n7simulator.modele.evenements.ApparitionEvenementIrregulier;
import n7simulator.modele.evenements.Evenement_Irregu;
import n7simulator.vue.Evenement.EvenementGUI;
import n7simulator.modele.evenements.Evenement_Regulier;
import n7simulator.vue.Evenement.EvenementIrreguGUI;
import n7simulator.vue.Evenement.EvenementReguGUI;
import n7simulator.vue.PilotageGUI;
import n7simulator.modele.evenements.ApparitionEvenementRegulier;

/**
 * Classe modélisant une partie du jeu N7Simulator.
 * La partie peut être chargée à partir d'une sauvegarde, ou 
 */
public final class Partie extends Observable {

	/**
	 * L'instance unique de la partie.
	 */
	private static Partie instance;
	
	/**
	 * Le nom de la partie (nom de la sauvegarde)
	 */
	private static String nomPartie;
	
	/**
	 * La gestion des professeurs de la partie
	 */
	private static GestionProfesseurs gestionProfesseurs;

	/**
	 * La jauge d'argent
	 */
	private static Jauge jaugeArgent;
	
	/**
	 * La jauge de Bonheur
	 */
	private static Jauge jaugeBonheur;

	/**
	 * La jauge de Pédagigie
	 */
	private static Jauge jaugePedagogie;

	/**
	 * Le temps de la partie
	 */
	private static Temps temps;

	/**
	 * Gestionnaire des événements irréguliers
	 */
	private static ApparitionEvenementIrregulier gestionnaireEvenementIrregulier;

	/**
	 * Gestionnaire des événements réguliers
	 */
	private static ApparitionEvenementRegulier gestionnaireEvenementRegulier;

	/**
	 * Les élèves
	 */
	private static GestionEleves gestionEleves;

	/**
	 * Indique si la partie est perdue ou non
	 */
	private static boolean estPerdue;

	/**
	 * les consommables au Foy
	 */
	private static ConsommablesFoy consommablesFoy;


	private Partie() {}
	
	/**
	 * Permet de récupérer la partie.
	 * @return la partie
	 */
	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
			jaugeArgent = new Jauge("Argent");
			jaugeBonheur = new JaugeBornee("Bonheur");
			jaugePedagogie = new JaugeBornee("Pedagogie");
			gestionnaireEvenementIrregulier = new ApparitionEvenementIrregulier();
			gestionnaireEvenementRegulier = new ApparitionEvenementRegulier();
			temps = new Temps(LocalDate.now());
			gestionProfesseurs = new GestionProfesseurs();
			gestionEleves = new GestionEleves();
			estPerdue = false;
			
			// Ajout dans JourSuivant
			JourSuivant jourSuivant = JourSuivant.getInstance();
			jourSuivant.addImpact(gestionProfesseurs);
			jourSuivant.addImpact(temps);
			jourSuivant.addImpact(gestionEleves);
		}
		return instance;
	}


	public void genererEvenementIrregulier(PilotageGUI pilote) {
		List <Integer> listeEvenement = gestionnaireEvenementIrregulier.calculApparitionEvenementIrregulier(jaugeBonheur, jaugePedagogie);
		for (int idEvenement : listeEvenement) {
			Evenement_Irregu evenement = new Evenement_Irregu(idEvenement, temps.getJourneeEnCours());
			evenement.appliquerImpact(this, true);
			EvenementIrreguGUI evenementIrreguGUI = new EvenementIrreguGUI(evenement, pilote);
			evenementIrreguGUI.setVisible(true);

		}
	}

	public void genererEvenementRegulier(PilotageGUI pilote) {
		List <Integer> listeEvenement = gestionnaireEvenementRegulier.verifEvenementRegulier(temps.getJourneeEnCours());
		for (int idEvenement : listeEvenement) {
			Evenement_Regulier evenement = new Evenement_Regulier(idEvenement, temps.getJourneeEnCours());
			EvenementReguGUI evenementReguGUI = new EvenementReguGUI(evenement, pilote);
			evenementReguGUI.setVisible(true);
		}
	}

	
	/**
	 * Renseigner le nom de la partie (sauvegarde)
	 * @param nomPartie : le nom de la partie
	 */
	public void initNomPartie(String initNomPartie) {
		nomPartie = initNomPartie;
	}
	
	/**
	 * Obtenir le nom de la partie (sauvegarde)
	 * @return : le nom de la partie
	 */
	public String getNomPartie() {
		return nomPartie;
	}
	
	/**
	 * Obtenir la jauge Argent
	 * @return la jauge d'argent
	 */
	public Jauge getJaugeArgent(){
		return jaugeArgent;
	}

	/**
	 * Obtenir la jauge de Bonheur
	 * @return la jauge de Bonheur
	 */
	public Jauge getJaugeBonheur(){
		return jaugeBonheur;
	}

	/**
	 * Obtenir la jauge de Pédagogie
	 * @return la jauge de Pédagogie
	 */
	public Jauge getJaugePedagogie(){
		return jaugePedagogie;
	}
	
	/**
	 * Permet de récupérer la gestion des élèves.
	 * @return la gestion des élèves.
	 */
	public GestionEleves getGestionEleves() {
		return gestionEleves;
	}
	
	/**
	 * Obtenir la gestion des professeurs de la partie
	 * @return : la gestion des professeurs de la partie
	 */
	public GestionProfesseurs getGestionProfesseurs() {
		return gestionProfesseurs;
	}
	

	/**
	 * Obtenir le temps de la partie.
	 * @return le temps.
	 */
	public Temps getTemps() {
		return temps;
	}

	public ConsommablesFoy getConsommablesFoy(){
		return consommablesFoy;
	}

	/**
	 * Obtenir le gestionnaire des événements réguliers
	 * @return : le gestionnaire des événements réguliers
	 */
	public ApparitionEvenementRegulier getGestionnaireEvenementRegulier() {
		return gestionnaireEvenementRegulier;
  }

	/**
	 * Modifie la partie qui devient "perdue"
	 */
	public static void setEstPerdue(boolean estPerdueActuellement) {
		estPerdue = estPerdueActuellement;
	}

	/**
	 * Est ce que la partie est perdue ?
	 * @return : si la partie est perdue
	 */
	public static boolean estPerdue() { 
		return estPerdue;
	}
	
}
