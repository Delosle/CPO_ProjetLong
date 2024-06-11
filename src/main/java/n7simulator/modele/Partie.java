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
import n7simulator.vue.PilotageGUI;

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
	 * Les élèves
	 */
	private static GestionEleves gestionEleves;

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
			temps = new Temps(LocalDate.now());
			gestionProfesseurs = new GestionProfesseurs((List<Professeur>)new ArrayList<Professeur>(), ProfesseurDAO.getAllProfesseurs());
			gestionEleves = new GestionEleves();
			consommablesFoy = new ConsommablesFoy(new ConsommableFoyDAO().getAllConsommableFoy());

			// Ajout dans JourSuivant
			JourSuivant jourSuivant = JourSuivant.getInstance();
			jourSuivant.addImpactCourtTerme(gestionProfesseurs);
			jourSuivant.addImpactCourtTerme(temps);
			jourSuivant.addImpactCourtTerme(gestionEleves);
		}
		return instance;
	}


	public void genererEvenementIrregulier(PilotageGUI pilote) {
		List <Integer> listeEvenement = gestionnaireEvenementIrregulier.calculApparitionEvenementIrregulier(jaugeBonheur, jaugePedagogie);
		System.out.println("Evenements Irreguliers : " + listeEvenement);
		for (int idEvenement : listeEvenement) {
			System.out.println("Evenement Irregulier : " + idEvenement);
			Evenement_Irregu evenement = new Evenement_Irregu(idEvenement, temps.getJourneeEnCours());
			evenement.appliquerImpact(this);
			EvenementGUI evenementGUI = new EvenementGUI(evenement, pilote);
			evenementGUI.setVisible(true);

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
}
