package n7simulator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import n7simulator.controller.TempsController;
import n7simulator.database.CreationBddAdmin;
import n7simulator.database.CreerBddSauvegarde;
import n7simulator.database.GestionBddSauvegarde;
import n7simulator.database.ValDebPartieDAO;
import n7simulator.modele.Crous;
import n7simulator.modele.Partie;
import n7simulator.modele.Temps;
import n7simulator.modele.consommableFoy.ConsommableFoy;
import n7simulator.modele.consommableFoy.ConsommablesFoy;
import n7simulator.modele.evenements.ApparitionEvenementIrregulier;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;
import n7simulator.vue.PilotageGUI;
import n7simulator.vue.Evenement.EventHistoryGUI;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.vue.startmenu.StartMenuGUI;
import n7simulator.vue.temps.TempsGUI;

public class N7Simulator {

 
	public static void main(String[] args) {
		CreationBddAdmin.initialiserBddAdmin();
		CreerBddSauvegarde.initialiserBddSauvegarde();
		affichageMenu();
	}

	/**
	 * Permet d'afficher le menu de démarrage du jeu
	 * (nouvelle partie - charger une partie)
	 */
	private static void affichageMenu() {
		new StartMenuGUI();
	}

	/**
	 * permet de valoriser la partie avec les valeurs par défaut
	 * de début de partie (nouvelel partie)
	 */
	public static void initNouvellePartie() {
		ValDebPartieDAO.initialiserDonneesDebutPartie();
		affichageCarte();
	}
	
	/**
	 * permet de valoriser la partie avec les valeurs récupérées
	 * depuis une sauvegarde en base de données
	 * @param nomPartie : le nom de la partie en bd
	 */
	public static void initPartieChargee(String nomPartie) {
		//Recuperation des donnees en base de données
		Map<String, List<Map<String, Object>>> donneesChargees = GestionBddSauvegarde.recupererInfoBddSauvegarde(nomPartie);
		
		//Valorsiation des données de la partie
		valoriserDonneesPartie(donneesChargees);
		valoriserDonneesProfesseur(donneesChargees);
		valoriserDonnesFoy(donneesChargees);
		
		affichageCarte();
	}

	/**
	 * Permet d'afficher la carte (interface principale du jeu)
	 */
	private static void affichageCarte() {
		Partie laPartie = Partie.getInstance();

		//crous
		Crous leCrous = Crous.getInstance(1, 1.30);
		
		//partie temps
		Temps tempsPartie = laPartie.getTemps();
		TempsGUI interfaceTemps = new TempsGUI(tempsPartie);
		tempsPartie.addObserver(interfaceTemps);
		TempsController controllerTemps = new TempsController(tempsPartie);

		// Les jauges
		Jauge argent = laPartie.getJaugeArgent();
		Jauge bonheur = laPartie.getJaugeBonheur();
		Jauge pedagogie = laPartie.getJaugePedagogie();

		JaugesPannel jaugesPannel = new JaugesPannel(bonheur.getValue(), pedagogie.getValue(), argent.getValue());
		ApparitionEvenementIrregulier gestionnaireEvenementIrregulier = new ApparitionEvenementIrregulier();
		argent.addObserver(jaugesPannel.getVueArgent());
		bonheur.addObserver(jaugesPannel.getVueBonheur());
		pedagogie.addObserver(jaugesPannel.getVuePedagogie());

		//Creation des interfaces
		PilotageGUI interfacePilotage = new PilotageGUI(interfaceTemps, controllerTemps, jaugesPannel, new EventHistoryGUI());
		CarteGUI interfaceCarte = new CarteGUI();
		N7Frame fenetre = N7Frame.getInstance(interfaceCarte, interfacePilotage);
		
	}
	
	/**
	 * Valorise toutes les données liées à la partie (table Partie en bd)
	 * @param donneesChargees : les données récupérées depuis la bd
	 */
	private static void valoriserDonneesPartie(Map<String, List<Map<String, Object>>> donneesChargees) {
		//table Partie
		Map<String, Object> donneesPartie = donneesChargees.get("Partie").get(0); //il n'y a qu'une ligne
		Partie partie = Partie.getInstance();
		partie.initNomPartie((String)donneesPartie.get("nomPartie"));
		partie.getGestionEleves().inscrireEleves((int)donneesPartie.get("nbEleves"));
		partie.getJaugeArgent().ajouter((double)donneesPartie.get("argent"));
		partie.getJaugeBonheur().ajouter((double)donneesPartie.get("bonheur"));
		partie.getJaugePedagogie().ajouter((double)donneesPartie.get("pedagogie"));
		String dateString = (String) donneesPartie.get("dateEnCours");
		partie.getTemps().setJourneeEnCours(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));		
	}
	
	/**
	 * Valorise toutes les données liées aux professeurs embauches (table ProfEmbauches en bd)
	 * @param donneesChargees : les données récupérées depuis la bd
	 */
	private static void valoriserDonneesProfesseur(Map<String, List<Map<String, Object>>> donneesChargees) {
		List<Map<String, Object>> donneesPartie = donneesChargees.get("ProfEmbauches");
		Partie partie = Partie.getInstance();
		GestionProfesseurs gestionProfesseurs = partie.getGestionProfesseurs();
		List<Professeur> professeursNonEmbauches = gestionProfesseurs.getProfesseursNonEmbauches();
		
		//Parcours des professeurs recupérés en bd
		for(Map<String, Object> profEmbaucheBD : donneesPartie) {
			int idProf = (int)profEmbaucheBD.get("idprof");
			int nbProfesseursNonEmbauches = professeursNonEmbauches.size();
			int i = 0;
			boolean professeurTrouve = false;
			
			//Recherche du professeur dans la liste des professeurs non embauches
			while(i<nbProfesseursNonEmbauches && !professeurTrouve) {
				Professeur profIter = professeursNonEmbauches.get(i);
				if(profIter.getId() == idProf) {
					//valorisation des données du professeur
					profIter.setSalaireActuel((int)profEmbaucheBD.get("salaire"));
					profIter.setNbHeuresTravaillees((int)profEmbaucheBD.get("nbheure"));
					gestionProfesseurs.embaucherProfesseur(profIter);
					professeurTrouve = true;
				}
				i ++;
			}
		}
	}

	private static void valoriserDonnesFoy(Map<String, List<Map<String, Object>>> donneesChargees){
		List<Map<String, Object>> donneesPartie = donneesChargees.get("ConsommableEnCours");
		Partie partie = Partie.getInstance();
		List<ConsommableFoy> consommables = ConsommablesFoy.getConsommables();
		for(int i = 0; i < consommables.size(); i++){
			consommables.get(i).setPrix((double)donneesPartie.get(i).get("prix"));
		}
	}
	
	/**
	 * Permet de sauvegarder la partie en base de données
	 */
	public static void sauvegarderPartie() {
		//Association entre la table de BD et les données
		Map<String, List<Map<String, Object>>> donneesPartie = new HashMap<String, List<Map<String,Object>>>();
		donneesPartie.put("Partie", getObjetSauvegardePartie());
		donneesPartie.put("ProfEmbauches", getObjetSauvegardeProfs());
		donneesPartie.put("ConsommableEnCours", getObjetSauvegardeFoy());
		
		Partie partieEnCours = Partie.getInstance();
		GestionBddSauvegarde.sauvegarderDonnee(donneesPartie, partieEnCours.getNomPartie());
	}
	
	/**
	 * Obtenir un objet contenant toutes les données liées à la partie
	 * afin de pouvoir les écrire en bd
	 * @return : une liste d'objets associatifs (entre le nom de la colonne et sa valeur)
	 */
	private static List<Map<String, Object>> getObjetSauvegardePartie() {
		Partie partieEnCours = Partie.getInstance();
		Map<String, Object> sauvegardePartie = new HashMap<String, Object>();

	    String dateEnCours = (partieEnCours.getTemps().getJourneeEnCours()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		sauvegardePartie.put("nomPartie", partieEnCours.getNomPartie());
		sauvegardePartie.put("dateEnCours", dateEnCours);
		sauvegardePartie.put("nbEleves", partieEnCours.getGestionEleves().getNombreEleves());
		sauvegardePartie.put("argent", partieEnCours.getJaugeArgent().getValue());
		sauvegardePartie.put("bonheur", partieEnCours.getJaugeBonheur().getValue());
		sauvegardePartie.put("pedagogie", partieEnCours.getJaugePedagogie().getValue());
		//a changer quand implementation des fonctionnalités
		sauvegardePartie.put("estPerdue", false);
		sauvegardePartie.put("idQualiteRepasCrous", 0);
		sauvegardePartie.put("prixVenteRepascrous", 0.0);
		
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		result.add(sauvegardePartie);
		return result;
	}
	
	/**
	 * Obtenir un objet contenant toutes les données liées aux professeurs embauchés
	 * afin de pouvoir les écrire en bd
	 * @return : une liste d'objets associatifs (entre le nom de la colonne et sa valeur)
	 */
	private static List<Map<String, Object>> getObjetSauvegardeProfs() {
		List<Map<String, Object>> listeSauvegardeProfs = new ArrayList<>();
		
		Partie partieEnCours = Partie.getInstance();
		GestionProfesseurs gestionProfesseurs = partieEnCours.getGestionProfesseurs();
		List<Professeur> professeursEmbauches = gestionProfesseurs.getProfesseursEmbauches();
		
		//Creation d'une ligne par prof
		for(Professeur prof : professeursEmbauches) {
			Map<String, Object> sauvegardeProfs = new HashMap<String, Object>();
			sauvegardeProfs.put("idprof", prof.getId());
			sauvegardeProfs.put("salaire", prof.getSalaireActuel());
			sauvegardeProfs.put("nbheure", prof.getNbHeuresTravaillees());
			listeSauvegardeProfs.add(sauvegardeProfs);
		}
		
		return listeSauvegardeProfs;
	}

	private static List<Map<String, Object>> getObjetSauvegardeFoy(){
		List<Map<String, Object>> listeSauvegardeFoy = new ArrayList<>();

		Partie partieEnCours = Partie.getInstance();
		List<ConsommableFoy> consommablesFoy = ConsommablesFoy.getConsommables();

		//création d'une ligne par consommables
		for(ConsommableFoy consommable : consommablesFoy){
			Map<String, Object> sauvegardeFoy = new HashMap<String, Object>();
			sauvegardeFoy.put("idConsommable", consommable.getId());
			sauvegardeFoy.put("prix", consommable.getPrix());
			listeSauvegardeFoy.add(sauvegardeFoy);
		}

		return listeSauvegardeFoy;
	}

}
