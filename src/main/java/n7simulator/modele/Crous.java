package n7simulator.modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

import n7simulator.database.DatabaseConnection;

public class Crous extends Observable{
	private static Crous instance;
	private int qualite;
	private double prixVente;

	private Crous(int qualite, double prixVente) {
		this.qualite = qualite;
		this.prixVente = prixVente;
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
		try {
			// connexion à la base de données
			connexionDB = DatabaseConnection.getDBConnexion();
			// requête à la base de données
			String query = "SELECT prix FROM RepasCrous WHERE id_repas = " + this.qualite;
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
		return Math.round(marge * 100.0 ) / 100.0;
	}
	
}
