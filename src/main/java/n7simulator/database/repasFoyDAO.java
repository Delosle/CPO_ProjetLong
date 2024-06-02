package n7simulator.database;

import n7simulator.modele.professeur.Professeur;
import n7simulator.modele.repas.RepasFoy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class repasFoyDAO {

    public static List<RepasFoy> getAllRepasFoy(){

        List<RepasFoy> repasFoys = new ArrayList<>();
        Connection connexionDB = null;
        try {
            // connexion à la base de données
            connexionDB = DatabaseConnection.getDBConnexion();

            // requête à la base de données
            String query = "SELECT * FROM RepasFoy";
            ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexionDB);

            // parcours du résultat pour instancier les objets profs
            while (resultDB.next()) {
                String nom = resultDB.getString("nom");
                Double prix = resultDB.getDouble("prix");
                Double prixLimite = resultDB.getDouble("prixLimite");
                String image = resultDB.getString("image");

                RepasFoy repasFoy = new RepasFoy(nom, prix, prixLimite, image);

                repasFoys.add(repasFoy);
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
        return repasFoys;
    }
}
