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
import n7simulator.modele.Partie;
import n7simulator.modele.Temps;
import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;
import n7simulator.vue.CarteGUI;
import n7simulator.vue.N7Frame;
import n7simulator.vue.PilotageGUI;
import n7simulator.vue.jauges.JaugesPannel;
import n7simulator.vue.startmenu.StartMenuGUI;
import n7simulator.vue.temps.TempsGUI;

public class N7Simulator {

 
	public static void main(String[] args) {
		CreationBddAdmin.initialiserBddAdmin();
		CreerBddSauvegarde.initialiserBddSauvegarde();
		affichageMenu();
	}

	private static void affichageMenu() {
		new StartMenuGUI();
	}

	public static void initNouvellePartie() {
		ValDebPartieDAO.initialiserDonneesDebutPartie();
		affichageCarte();
	}
	
	public static void initPartieChargee(String nomPartie) {
		Map<String, List<Map<String, Object>>> donneesChargees = GestionBddSauvegarde.recupererInfoBddSauvegarde(nomPartie);
		valoriserDonneesPartie(donneesChargees);
		valoriserDonneesProfesseur(donneesChargees);
		affichageCarte();
	}

	private static void affichageCarte() {
		Partie laPartie = Partie.getInstance();
		Temps tempsPartie = laPartie.getTemps();
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
	
	private static void valoriserDonneesPartie(Map<String, List<Map<String, Object>>> donneesChargees) {
		//table Partie
		Map<String, Object> donneesPartie = donneesChargees.get("Partie").get(0); //il n'y a qu'une ligne
		Partie partie = Partie.getInstance();
		partie.initNomPartie((String)donneesPartie.get("nomPartie"));
		partie.inscrireEleves((int)donneesPartie.get("nbEleves"));
		partie.getJaugeArgent().ajouter((double)donneesPartie.get("argent"));
		partie.getJaugeBonheur().ajouter((double)donneesPartie.get("bonheur"));
		partie.getJaugePedagogie().ajouter((double)donneesPartie.get("pedagogie"));
		String dateString = (String) donneesPartie.get("dateEnCours");
		partie.getTemps().setJourneeEnCours(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));		
	}
	
	
	
	private static void valoriserDonneesProfesseur(Map<String, List<Map<String, Object>>> donneesChargees) {
		List<Map<String, Object>> donneesPartie = donneesChargees.get("ProfEmbauches");
		System.out.println(donneesPartie);
		Partie partie = Partie.getInstance();
		GestionProfesseurs gestionProfesseurs = partie.getGestionProfesseurs();
		List<Professeur> professeursNonEmbauches = gestionProfesseurs.getProfesseursNonEmbauches();
		List<Professeur> professeursEmbauches = gestionProfesseurs.getProfesseursEmbauches();
		
		for(Map<String, Object> profEmbaucheBD : donneesPartie) {
			int idProf = (int)profEmbaucheBD.get("idprof");
			int nbProfesseursNonEmbauches = professeursNonEmbauches.size();
			int i = 0;
			boolean professeurTrouve = false;
			while(i<nbProfesseursNonEmbauches && !professeurTrouve) {
				Professeur profIter = professeursNonEmbauches.get(i);
				if(profIter.getId() == idProf) {
					profIter.setSalaireActuel((int)profEmbaucheBD.get("salaire"));
					profIter.setNbHeuresTravaillees((int)profEmbaucheBD.get("nbheure"));
					gestionProfesseurs.embaucherProfesseur(profIter);
					professeurTrouve = true;
				}
				i ++;
			}
		}
	}
	
	public static void sauvegarderPartie() {
		Map<String, List<Map<String, Object>>> donneesPartie = new HashMap<String, List<Map<String,Object>>>();
		donneesPartie.put("Partie", getObjetSauvegardePartie());
		donneesPartie.put("ProfEmbauches", getObjetSauvegardeProfs());
		
		Partie partieEnCours = Partie.getInstance();
		GestionBddSauvegarde.sauvegarderDonnee(donneesPartie, partieEnCours.getNomPartie());
	}
	
	private static List<Map<String, Object>> getObjetSauvegardePartie() {
		Partie partieEnCours = Partie.getInstance();
		Map<String, Object> sauvegardePartie = new HashMap<String, Object>();

	    String dateEnCours = (partieEnCours.getTemps().getJourneeEnCours()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		sauvegardePartie.put("nomPartie", partieEnCours.getNomPartie());
		sauvegardePartie.put("dateEnCours", dateEnCours);
		sauvegardePartie.put("nbEleves", partieEnCours.getNombreEleves());
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
	
	private static List<Map<String, Object>> getObjetSauvegardeProfs() {
		List<Map<String, Object>> listeSauvegardeProfs = new ArrayList<>();
		
		Partie partieEnCours = Partie.getInstance();
		GestionProfesseurs gestionProfesseurs = partieEnCours.getGestionProfesseurs();
		List<Professeur> professeursEmbauches = gestionProfesseurs.getProfesseursEmbauches();
		
		for(Professeur prof : professeursEmbauches) {
			Map<String, Object> sauvegardeProfs = new HashMap<String, Object>();
			sauvegardeProfs.put("idprof", prof.getId());
			sauvegardeProfs.put("salaire", prof.getSalaireActuel());
			sauvegardeProfs.put("nbheure", prof.getNbHeuresTravaillees());
			listeSauvegardeProfs.add(sauvegardeProfs);
		}
		
		return listeSauvegardeProfs;
	}
}
