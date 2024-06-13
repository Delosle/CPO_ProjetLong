package n7simulator.database;

import n7simulator.modele.consommableFoy.ConsommableFoy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsommableFoyDAO {
	
	private ConsommableFoyDAO() {}

    public static List<ConsommableFoy> getAllConsommableFoy(){

        List<ConsommableFoy> consommableFoys = new ArrayList<>();
        Connection connexionDB = null;
        try {
            // connexion à la base de données
            connexionDB = DatabaseConnection.getDBConnexion();

            // requête à la base de données
            String query = "SELECT * FROM ConsommableFoy";
            ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);
            ConsommableFoy consommableFoy;

            // parcours du résultat pour instancier les objets profs
            while (resultDB.next()) {
                int id = resultDB.getInt("idConsommable");
                String nom = resultDB.getString("nom");
                Double prix = resultDB.getDouble("prix");
                Double prixLimite = resultDB.getDouble("prixLimite");
                String image = resultDB.getString("image");

                consommableFoy = new ConsommableFoy(id ,nom, prix, prixLimite, image);
                consommableFoys.add(consommableFoy);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des Repas Foy dans la base de données.");
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeDBConnexion(connexionDB);
            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture de la connexion");
                e.printStackTrace();
            }
        }
        return consommableFoys;
    }
}
