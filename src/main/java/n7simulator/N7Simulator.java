package n7simulator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import n7simulator.database.ConsommableFoyDAO;
import n7simulator.database.CreationBddAdmin;
import n7simulator.database.CreerBddSauvegarde;
import n7simulator.database.GestionBddSauvegarde;
import n7simulator.database.ProfesseurDAO;
import n7simulator.database.ValDebPartieDAO;
import n7simulator.modele.Bibliotheque;
import n7simulator.modele.Crous;
import n7simulator.modele.Partie;
import n7simulator.modele.foy.ConsommableFoy;
import n7simulator.modele.foy.Foy;
import n7simulator.modele.evenements.ApparitionEvenementRegulier;
import n7simulator.vue.GameOverFrame;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;
import n7simulator.vue.PilotageGUI;
import n7simulator.vue.Evenement.EventHistoryGUI;
import n7simulator.vue.startmenu.StartMenuGUI;

public class N7Simulator {

	public static void main(String[] args) {
		CreationBddAdmin.initialiserBddAdmin();
		CreerBddSauvegarde.initialiserBddSauvegarde();
		affichageMenu();
	}

	/**
	 * Permet d'afficher le menu de démarrage du jeu (nouvelle partie - charger une
	 * partie)
	 */
	private static void affichageMenu() {
		new StartMenuGUI();
	}

	/**
	 * permet de valoriser la partie avec les valeurs par défaut de début de partie
	 * (nouvelel partie)
	 */
	public static void initNouvellePartie() {
		ValDebPartieDAO.initialiserDonneesDebutPartie();
		// crous
		Crous crousInstance = Crous.getInstance();
		crousInstance.setPrixVente(1.30);
		crousInstance.setQualite(1);
		affichageCarte();
	}

	/**
	 * permet de valoriser la partie avec les valeurs récupérées depuis une
	 * sauvegarde en base de données
	 * 
	 * @param nomPartie : le nom de la partie en bd
	 */
	public static void initPartieChargee(String nomPartie) {
		// Recuperation des donnees en base de données
		Map<String, List<Map<String, Object>>> donneesChargees = GestionBddSauvegarde
				.recupererInfoBddSauvegarde(nomPartie);

		// Valorsiation des données de la partie
		valoriserDonneesPartie(donneesChargees);
		valoriserDonneesProfesseur(donneesChargees);
		valoriserDonnesFoy(donneesChargees);
		valoriserDonneesDateEvenementRegulier(donneesChargees);
		// affichage de l'interface du jeu
		if (!Partie.estPerdue()) {
			affichageCarte();
		}

	}

	/**
	 * Permet d'afficher la carte (interface principale du jeu)
	 */
	private static void affichageCarte() {
		// Creation des interfaces
		PilotageGUI interfacePilotage = new PilotageGUI(new EventHistoryGUI());
		CarteGUI interfaceCarte = new CarteGUI();
		N7Frame.setUp(interfaceCarte, interfacePilotage);
		N7Frame.getInstance();	
	}

	/**
	 * Valorise toutes les données liées à la partie (table Partie en bd)
	 * 
	 * @param donneesChargees : les données récupérées depuis la bd
	 */
	private static void valoriserDonneesPartie(Map<String, List<Map<String, Object>>> donneesChargees) {
		// table Partie
		Map<String, Object> donneesPartie = donneesChargees.get("Partie").get(0); // il n'y a qu'une ligne
		Partie partie = Partie.getInstance();
		
		valoriserJauges(donneesPartie);
		
		partie.initNomPartie((String)donneesPartie.get("nomPartie"));
		partie.getGestionEleves().inscrireEleves((int) donneesPartie.get("nbEleves"));

		String dateString = (String) donneesPartie.get("dateEnCours");
		partie.getTemps().setJourneeEnCours(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		Crous crousInstance = Crous.getInstance();
		crousInstance.setQualite((int) donneesPartie.get("idQualiteRepasCrous"));
		crousInstance.setPrixVente((double) donneesPartie.get("prixVenteRepascrous"));
		Bibliotheque biblioInstance = Bibliotheque.getInstance();
		biblioInstance.setNbLivre((int) donneesPartie.get("nbLivre"));
		Partie.setEstPerdue(Boolean.parseBoolean((String) donneesPartie.get("estPerdue")));
	}
	
	/**
	 * Valorise les jauges pour une partie sauvegarde et lance l'écran de game over
	 * si une jauge a atteint 0
	 * @param donneesPartie : les donnees chargees de la partie
	 */
	private static void valoriserJauges(Map<String, Object> donneesPartie) {
		Partie partie = Partie.getInstance();

		double valeurArgent = (double) donneesPartie.get("argent");
		double valeurBonheur = (double) donneesPartie.get("bonheur");
		double valeurPedagogie = (double) donneesPartie.get("pedagogie");
		
		Jauge declenchementGameOver = null;
		if(valeurArgent == 0) {
			declenchementGameOver = partie.getJaugeArgent();
		}
		if(valeurBonheur == 0) {
			declenchementGameOver = partie.getJaugeBonheur();
		}
		if(valeurPedagogie == 0) {
			declenchementGameOver = partie.getJaugePedagogie();
		}

		if (declenchementGameOver != null) {
			Partie.setEstPerdue(true);
			new GameOverFrame(declenchementGameOver);
		} else {
			partie.getJaugeArgent().reinitialiserValeur(valeurArgent);
			partie.getJaugeBonheur().reinitialiserValeur(valeurBonheur);
			partie.getJaugePedagogie().reinitialiserValeur(valeurPedagogie);
		}
	}

	/**
	 * Valorise toutes les données liées aux professeurs embauches (table
	 * ProfEmbauches en bd)
	 * 
	 * @param donneesChargees : les données récupérées depuis la bd
	 */
	private static void valoriserDonneesProfesseur(Map<String, List<Map<String, Object>>> donneesChargees) {
		List<Map<String, Object>> donneesPartie = donneesChargees.get("ProfEmbauches");
		Partie partie = Partie.getInstance();
		GestionProfesseurs gestionProfesseurs = partie.getGestionProfesseurs();
		gestionProfesseurs.initialiserListeProfesseurs(new ArrayList<Professeur>(), ProfesseurDAO.getAllProfesseurs());
		List<Professeur> professeursNonEmbauches = gestionProfesseurs.getProfesseursNonEmbauches();

		// Parcours des professeurs recupérés en bd
		for (Map<String, Object> profEmbaucheBD : donneesPartie) {
			int idProf = (int) profEmbaucheBD.get("idprof");
			int nbProfesseursNonEmbauches = professeursNonEmbauches.size();
			int i = 0;
			boolean professeurTrouve = false;

			// Recherche du professeur dans la liste des professeurs non embauches
			while (i < nbProfesseursNonEmbauches && !professeurTrouve) {
				Professeur profIter = professeursNonEmbauches.get(i);
				if (profIter.getId() == idProf) {
					// valorisation des données du professeur
					profIter.setSalaireActuel((int) profEmbaucheBD.get("salaire"));
					profIter.setNbHeuresTravaillees((int) profEmbaucheBD.get("nbheure"));
					gestionProfesseurs.embaucherProfesseur(profIter);
					professeurTrouve = true;
				}
				i++;
			}
		}
	}

	/**
	 * Valorise toutes les données liées aux foy.
	 * 
	 * @param donneesChargees : les données récupérées depuis la bd
	 */
	private static void valoriserDonnesFoy(Map<String, List<Map<String, Object>>> donneesChargees) {
		List<Map<String, Object>> donneesPartie = donneesChargees.get("ConsommableEnCours");
		Foy foy = Partie.getInstance().getFoy();
		foy.setConsommablesListe(ConsommableFoyDAO.getAllConsommableFoy());

		List<ConsommableFoy> consommables = foy.getConsommables();
		
		for (Map<String, Object> consommableModifieBD : donneesPartie) {
			int idConsommable = (int)consommableModifieBD.get("idConsommable");
			
			boolean trouve = false;
			int nbConsommables = consommables.size();
			int i=0;
			
			while (i<nbConsommables && !trouve) {
				ConsommableFoy conso = consommables.get(i);
				if (idConsommable == conso.getId()) {
					conso.setPrix((double) consommableModifieBD.get("prix"));
					trouve = true;
				}
				i++;
			}			
		}
	}

	/**
	 * Valorise toutes les données liées aux professeurs embauches (table
	 * ProfEmbauches en bd)
	 * 
	 * @param donneesChargees : les données récupérées depuis la bd
	 */
	private static void valoriserDonneesDateEvenementRegulier(Map<String, List<Map<String, Object>>> donneesChargees) {
		List<Map<String, Object>> donneesPartie = donneesChargees.get("DateEvenementRegulier");
		Partie partie = Partie.getInstance();
		ApparitionEvenementRegulier gestionnaireEvenementRegulier = partie.getGestionnaireEvenementRegulier();
		gestionnaireEvenementRegulier.setDonneeEvenement(donneesPartie);
	}

	/**
	 * Permet de sauvegarder la partie en base de données
	 */
	public static void sauvegarderPartie() {
		// Association entre la table de BD et les données
		Map<String, List<Map<String, Object>>> donneesPartie = new HashMap<>();
		donneesPartie.put("Partie", getObjetSauvegardePartie());
		donneesPartie.put("ProfEmbauches", getObjetSauvegardeProfs());
		donneesPartie.put("ConsommableEnCours", getObjetSauvegardeFoy());
		donneesPartie.put("DateEvenementRegulier", getObjetEvenementRegulierDate());

		Partie partieEnCours = Partie.getInstance();
		GestionBddSauvegarde.sauvegarderDonnee(donneesPartie, partieEnCours.getNomPartie());
	}

	/**
	 * Obtenir un objet contenant toutes les données liées à la partie afin de
	 * pouvoir les écrire en bd
	 * 
	 * @return : une liste d'objets associatifs (entre le nom de la colonne et sa
	 *         valeur)
	 */
	private static List<Map<String, Object>> getObjetSauvegardePartie() {
		Partie partieEnCours = Partie.getInstance();
		Map<String, Object> sauvegardePartie = new HashMap<>();

		String dateEnCours = (partieEnCours.getTemps().getJourneeEnCours())
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		sauvegardePartie.put("nomPartie", partieEnCours.getNomPartie());
		sauvegardePartie.put("dateEnCours", dateEnCours);
		sauvegardePartie.put("nbEleves", partieEnCours.getGestionEleves().getNombreEleves());
		sauvegardePartie.put("argent", partieEnCours.getJaugeArgent().getValue());
		sauvegardePartie.put("bonheur", partieEnCours.getJaugeBonheur().getValue());
		sauvegardePartie.put("pedagogie", partieEnCours.getJaugePedagogie().getValue());
		// a changer quand implementation des fonctionnalités
		sauvegardePartie.put("estPerdue", Partie.estPerdue());
		Crous crousInstance = Crous.getInstance();
		sauvegardePartie.put("idQualiteRepasCrous", crousInstance.getQualite());
		sauvegardePartie.put("prixVenteRepascrous", crousInstance.getPrixVente());
		Bibliotheque biblioInstance = Bibliotheque.getInstance();
		sauvegardePartie.put("nbLivre", biblioInstance.getNbLivre());

		List<Map<String, Object>> result = new ArrayList<>();
		result.add(sauvegardePartie);
		return result;
	}

	/**
	 * Obtenir un objet contenant toutes les données liées aux professeurs embauchés
	 * afin de pouvoir les écrire en bd
	 * 
	 * @return : une liste d'objets associatifs (entre le nom de la colonne et sa
	 *         valeur)
	 */
	private static List<Map<String, Object>> getObjetSauvegardeProfs() {
		List<Map<String, Object>> listeSauvegardeProfs = new ArrayList<>();

		Partie partieEnCours = Partie.getInstance();
		GestionProfesseurs gestionProfesseurs = partieEnCours.getGestionProfesseurs();
		List<Professeur> professeursEmbauches = gestionProfesseurs.getProfesseursEmbauches();

		// Creation d'une ligne par prof
		for (Professeur prof : professeursEmbauches) {
			Map<String, Object> sauvegardeProfs = new HashMap<>();
			sauvegardeProfs.put("idprof", prof.getId());
			sauvegardeProfs.put("salaire", prof.getSalaireActuel());
			sauvegardeProfs.put("nbheure", prof.getNbHeuresTravaillees());
			listeSauvegardeProfs.add(sauvegardeProfs);
		}
		return listeSauvegardeProfs;
	}

	/**
	 * Obtenir un objet contenant toutes les données liées aux foy afin de pouvoir les écrire en bd
	 * 
	 * @return : une liste d'objets associatifs (entre le nom de la colonne et sa
	 *         valeur)
	 */
	private static List<Map<String, Object>> getObjetSauvegardeFoy() {
		List<Map<String, Object>> listeSauvegardeFoy = new ArrayList<>();

		Partie partieEnCours = Partie.getInstance();
		List<ConsommableFoy> consommablesFoy = partieEnCours.getFoy().getConsommables();

		// création d'une ligne par consommables
		for (ConsommableFoy consommable : consommablesFoy) {
			Map<String, Object> sauvegardeFoy = new HashMap<>();
			sauvegardeFoy.put("idConsommable", consommable.getId());
			sauvegardeFoy.put("prix", consommable.getPrix());
			listeSauvegardeFoy.add(sauvegardeFoy);
		}

		return listeSauvegardeFoy;
	}

	/**
	 * Obtenir un objet contenant toutes les dates des evenements réguliers à
	 * 
	 * @return : une liste d'objets associatifs (entre le nom de la colonne et sa
	 *         valeur)
	 */
	private static List<Map<String, Object>> getObjetEvenementRegulierDate() {
		List<Map<String, Object>> listeSauvegardeEvenementRegulier = new ArrayList<>();

		Partie partieEnCours = Partie.getInstance();
		ApparitionEvenementRegulier gestionnaireEvenementRegulier = partieEnCours.getGestionnaireEvenementRegulier();
		Map<Integer, Map<String, Object>> donneeEvenement = gestionnaireEvenementRegulier.getDonneesEvenements();

		for (Map.Entry<Integer, Map<String, Object>> entry : donneeEvenement.entrySet()) {
			Map<String, Object> sauvegardeEvenement = new HashMap<String, Object>();
			Integer idEveReg = entry.getKey();
			sauvegardeEvenement.put("idEvenementRegulier", idEveReg);
			Map<String, Object> eventDetails = entry.getValue();
			String dateString = eventDetails.get("debut").toString();
			LocalDate dateDebut = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			sauvegardeEvenement.put("dateEvenement", dateDebut);
			listeSauvegardeEvenementRegulier.add(sauvegardeEvenement);
		}
		return listeSauvegardeEvenementRegulier;
	}

}
