package n7simulator.modele;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import n7simulator.ImpactJourSuivantCourtTerme;
import n7simulator.JourSuivant;
import n7simulator.database.DatabaseConnection;

public class Crous extends Observable implements ImpactJourSuivantCourtTerme {
	public static final String[] QUALITE_STR = {"Mauvaise", "Acceptable", "Bonne", "Très bonne", "Excellente"};
	private static Crous instance;
	private int qualite;
	private double prixVente;
	
	private Crous(int qualite, double prixVente) {
		this.qualite = qualite;
		this.prixVente = prixVente;
		// TODO : décommenter la ligne suivante pour ajouter l'impact quand on merge avec develop
		//JourSuivant.getInstance().addImpactCourtTerme(this);
	}
	
	/**
	 * Permet de récupérer le CRous.
	 * @return le Crous
	 */
	public static Crous getInstance(int qualite, double prixVente) {
		if (instance == null) {
			instance = new Crous(qualite, prixVente);
		}
		return instance;
	}

	public int getQualite() {
		return qualite;
	}

	public void setQualite(int qualite) {
		this.qualite = qualite;
		this.setChanged();
		this.notifyObservers(this);
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
		this.setChanged();
		this.notifyObservers(this);
	}

	public double getMarge() {
		
		Connection connexionDB = null;
		double marge = 0;
		int nbEleves = Partie.getInstance().getNombreEleves();
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
			// requête à la base de données
			String query = "SELECT prix FROM RepasCrous WHERE id_repas = " + (this.qualite + 1);
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
			marge = this.prixVente - resultDB.getDouble("prix");
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération du prix d'achat des repas dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		return Math.round(marge * 100.0 ) / 100.0 * nbEleves;
	}
	
	public double getMarge(int indexQualite, double prixVente) {
		int tempQualite = this.qualite;
		double tempPrixVente = this.prixVente;
		double valRetour;
		try {
			this.qualite = indexQualite;
			this.prixVente = prixVente;
			valRetour = getMarge();
		} 
		finally {
			this.qualite = tempQualite;
			this.prixVente = tempPrixVente;
		}
		return valRetour;
	}
	
	public String[] getListeQualites() {
		Connection connexionDB = null;
		List<String> listeRetour = new ArrayList<String>();
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
			// requête à la base de données
			String query = "SELECT * FROM RepasCrous";
			ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
			while (resultDB.next()) {
				double prix = resultDB.getDouble("prix");
				int indexQualite = resultDB.getInt("qualite");
				listeRetour.add(QUALITE_STR[indexQualite - 1] + " : " + "%.2f".formatted(prix));
			}
			
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération des repas dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		String[] retour = new String[listeRetour.size()];
		for (int i = 0; i < listeRetour.size(); i ++) {
			retour[i] = listeRetour.get(i);
		}
		return retour;
	}

	@Override
	public void effectuerImpactJourSuivantCourtTerme() {
		double marge = getMarge();
		Partie instance = Partie.getInstance();
		instance.getJaugeArgent().ajouter((int)marge);
		double margeIndividuelle = getMarge() / instance.getNombreEleves();
		if (margeIndividuelle > 1) {
			instance.getJaugeBonheur().ajouter(- 10);
		}
		else if(margeIndividuelle < 1) {
			instance.getJaugeBonheur().ajouter(2 * qualite);
		}
	}
	
	/* Problème : le pointeur se ferme quand la connexion à la BD se ferme
	private Object utiliserBD(String query) {
		Connection connexionDB = null;
		ResultSet resultDB;
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
			resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
			return resultDB.;
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération du prix d'achat des repas dans la base de données.");
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.closeDBConnexion(connexionDB);
			} catch (Exception e) {
				System.err.println("Erreur lors de la fermeture de la connexion");
				e.printStackTrace();
			}
		}
		// Cet élément de return est juste pour tromper le compilateur
		return new String("");
	}
	*/
}
