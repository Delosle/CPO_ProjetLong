package n7simulator.database;

import java.io.*;
import java.sql.*;
import java.util.*;

public class GestionBddSauvegarde {

	/**
	 * Effectue une connexion a la base de données admin
	 * 
	 * @return : la connexion
	 * @throws SQLException : si la connexion echoue
	 */
	private static Connection getDBConnexion() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:src/main/resources/baseDeDonnee/SauvegardePartie.db");
	}

	/**
	 * Effectue une requete sur la connexion à la bd
	 * 
	 * @param query     : la requête SQL
	 * @param connexion : la connexion a la base de données
	 * @return : le résultat de la requête
	 * @throws SQLException : si la requête échoue
	 */
	private static ResultSet effectuerRequete(String query, Connection connexion) throws SQLException {
		Statement statement = connexion.createStatement();
		statement.setQueryTimeout(30);
		return statement.executeQuery(query);
	}

	/**
	 * Exécute une requête de mise à jour (INSERT, UPDATE, DELETE)
	 * 
	 * @param query     : la requête SQL
	 * @param connexion : la connexion à la base de données
	 * @throws SQLException : si la requête échoue
	 */
	private static void effectuerMiseAJour(String query, Connection connexion) throws SQLException {
		try (Statement statement = connexion.createStatement()) {
			statement.setQueryTimeout(30);
			statement.executeUpdate(query);
		}
	}


	/**
	 * Permet de fermer une connexion à la base de données
	 * 
	 * @param connexion : la connexion qui doit être fermée
	 * @throws SQLException : si la fermeture échoue
	 */
	private static void closeDBConnexion(Connection connexion) throws SQLException {
		if (connexion != null) {
			connexion.close();
		}
	}

	/**
	 * Récupère les informations de la base de données SauvegardePartie
	 * 
	 * @param idPartie : l'identifiant de la partie
	 * @return : les informations de la base de données
	 */
	public static Map<String, List<Map<String, Object>>> recupererInfoBddSauvegarde(String nomPartie) {
		// Créez un HashMap pour stocker les informations
		Map<String, List<Map<String, Object>>> infoBdd = new HashMap<>();
		try (Connection conn = getDBConnexion()) {
			// Recuperation de l'id de la partie
			int idPartie = (int) recupererIdPartie(nomPartie, conn)[0];
			List<String> tables = Arrays.asList("ProfEmbauches", "EvenementEnCours", "Partie", "DateEvenementRegulier");
			for (String table : tables) {
				String query = "SELECT * FROM " + table + " WHERE idPartie = " + idPartie;
				infoBdd.put(table, peuplerDico(conn, query));
			}
			closeDBConnexion(conn);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Renvoyez le HashMap contenant les informations
		return infoBdd;
	}

	/**
	 * Peuple un dictionnaire avec les informations de la base de données
	 * 
	 * @param conn  : la connexion à la base de données
	 * @param query : la requête SQL
	 * @return : les informations de la base de données
	 */
	private static List<Map<String, Object>> peuplerDico(Connection conn, String query) {
		List<Map<String, Object>> infoList = new ArrayList<>();
		try {
			ResultSet result = effectuerRequete(query, conn);
			while (result.next()) { // boucle sur chaque ligne de résultat
				Map<String, Object> infoTable = new HashMap<>();
				int columnCount = result.getMetaData().getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = result.getMetaData().getColumnName(i);
					Object value = result.getObject(i);
					infoTable.put(columnName, value);
				}
				infoList.add(infoTable);
			}
			result.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return infoList;
	}

	/**
	 * Récupère les noms des parties sauvegardées
	 * 
	 * @return : la liste des noms des parties sauvegardées
	 * @throws SQLException : si la requête échoue
	 */
	public static ArrayList<String> recupererNomPartie() {
		String query = "SELECT nomPartie FROM Partie";
		ArrayList<String> nomPartie = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = getDBConnexion();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try (ResultSet result = effectuerRequete(query, conn)) {
			while (result.next()) {
				nomPartie.add(result.getString("nomPartie"));
			}
			result.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			closeDBConnexion(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return nomPartie;
	}

	/**
	 * Vérifie si le nom de la partie est disponible
	 * 
	 * @param nomPartie : le nom de la partie
	 * @return : true si le nom de la partie est disponible, false sinon
	 * @throws PartieExisteDejaException : si la partie existe déjà
	 */
	public static boolean nomPartieDisponible(String nomPartie) {
		ArrayList<String> listNomPartie = new ArrayList<String>();
		listNomPartie = recupererNomPartie();
		for (String nom : listNomPartie) {
			if (nom.equals(nomPartie)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Récupère l'identifiant de la partie
	 * 
	 * @param nomPartie : le nom de la partie
	 * @param conn      : la connexion à la base de données
	 * @return : l'identifiant de la partie
	 */
	private static Object[] recupererIdPartie(String nomPartie, Connection conn) {
		int maxidPartie = 0;
		boolean existeDeja = false;
		try {
			if (nomPartieDisponible(nomPartie)) {
				maxidPartie = effectuerRequete("SELECT MAX(idPartie) as maxId FROM Partie;", conn).getInt("maxId");
				maxidPartie++;
			} else {
				try {
					maxidPartie = effectuerRequete("SELECT idPartie FROM Partie WHERE nomPartie ='" + nomPartie + "';",
							conn).getInt("idPartie");
					existeDeja = true;
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object[] { maxidPartie, existeDeja };
	}

	/**
	 * Sauvegarde les données de la partie Mettre la clé primaire de la table en
	 * premier dans la map correspond à chaque table Exemple :
	 * 
	 * @param data
	 * @param nomPartie
	 */
	public static void sauvegarderDonnee(Map<String, List<Map<String, Object>>> data, String nomPartie) {
		Connection conn = null;
		try {
			conn = getDBConnexion();
			Object[] idPartie = recupererIdPartie(nomPartie, conn);
			int maxidPartie = (int) idPartie[0];
		 	

			for (Map.Entry<String, List<Map<String, Object>>> entry : data.entrySet()) { // pour chaque table
				boolean existeDeja = (boolean) idPartie[1];
				String tableName = entry.getKey(); // recupérer le nom de la table
				List<Map<String, Object>> listOfRecords = entry.getValue(); // recupérer les données de la table
				
				if(tableName == "ProfEmbauches") {
					//suppression des donnees sur les professeurs pour tout remettre de 0
					profsCleanSauvegarde(conn, maxidPartie);
				}

				if(tableName == "DateEvenementRegulier") {
					//suppression des donnees sur les dates des evenements reguliers pour tout remettre de 0
					evenementRegulierCleanSauvegarde(conn, maxidPartie);
				}
				
				for (Map<String, Object> record : listOfRecords) {
					StringBuilder columns = new StringBuilder();
					StringBuilder values = new StringBuilder();
					StringBuilder updateValues = new StringBuilder();
					String primaryKey = "idPartie"; // Clé primaire de la table

					for (Map.Entry<String, Object> field : record.entrySet()) {
						String columnName = field.getKey();
						Object value = field.getValue();
						columns.append(columnName).append(", ");
						values.append("'").append(value).append("', ");
						updateValues.append(columnName).append(" = '").append(value).append("', ");
					}

					columns.delete(columns.length() - 2, columns.length());
					values.delete(values.length() - 2, values.length());
					updateValues.delete(updateValues.length() - 2, updateValues.length());

					String query;
					if(tableName == "ProfEmbauches" || tableName == "DateEvenementRegulier") {
						// ajout de l'idPartie
						columns.append(", ").append("idPartie");
						values.append(", ").append("'" + maxidPartie + "'");
						//on considere que les donnees n'existent pas car on a tout supprimé au niveau des profs
						existeDeja = false;
					}
					
					if (existeDeja) {
						query = "UPDATE " + tableName + " SET " + updateValues + " WHERE " + primaryKey + " = "
								+ maxidPartie + ";";
					} else {
						query = "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES ("
								+ values.toString() + ");";
					}
					//System.out.println(query);
					effectuerMiseAJour(query, conn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Permet de supprimer les profs sauvegarder pour la partie idPartie
	 * @param conn : la connexion a la base de données sauvegarde
	 * @param idPartie : l'id de la partie
	 */
	private static void profsCleanSauvegarde(Connection conn, int idPartie) {
			String queryDelete = "DELETE FROM ProfEmbauches WHERE idPartie = "+idPartie+";";
            try {
				effectuerMiseAJour(queryDelete, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	private static void evenementRegulierCleanSauvegarde(Connection conn, int idPartie) {
		String queryDelete = "DELETE FROM DateEvenementRegulier WHERE idPartie = "+idPartie+";";
		try {
			effectuerMiseAJour(queryDelete, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
               
}