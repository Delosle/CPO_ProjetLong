package n7simulator.modele.evenements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import n7simulator.modele.Partie;
import java.sql.Connection;
import n7simulator.database.DatabaseConnection;


public class Evenement_Regulier extends Evenement{
    private int impactBonheurNeg, impactArgentNeg, impactPedagogieNeg;
    private LocalDate dateApparition;

    /**
     * Constructeur
     * @param id l'identifiant de l'événement
     * @param dateApparition la date d'apparition de l'événement
     */
    public Evenement_Regulier(int id, LocalDate dateApparition){
        super(id);
        Connection connexion = null;
        try {
            /*ouverture de connexion à la base de données*/
            connexion = DatabaseConnection.getDBConnexion();

            /*récupération des informations de l'événement*/
            String query = "SELECT titre, description, impactBonheurPos, impactArgentPos, " +
                    "impactPedagogiePos, impactBonheurNeg, impactArgentNeg, impactPedagogieNeg " +
                    "FROM evenement_regulier " +
                    "WHERE id_eve_reg = " + id;

            ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexion);

            // récupération des informations de l'événement
            while (resultDB.next()) {
                titre = resultDB.getString("titre");
                description = resultDB.getString("description");
                impactBonheur = resultDB.getInt("impactBonheurPos");
                impactArgent = resultDB.getInt("impactArgentPos");
                impactPedagogie = resultDB.getInt("impactPedagogiePos");
                impactBonheurNeg = resultDB.getInt("impactBonheurNeg");
                impactArgentNeg = resultDB.getInt("impactArgentNeg");
                impactPedagogieNeg = resultDB.getInt("impactPedagogieNeg");
            }

            this.dateApparition = dateApparition;


        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des données " +
                    "de l'evenement irregulier dans la base de données.");
            e.printStackTrace();

        } finally {
            try {
                DatabaseConnection.closeDBConnexion(connexion);

            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture de la connexion");
                e.printStackTrace();
            }
        }

    }
    /** @return la date d'apparition de l'événement
     */
    public LocalDate getDateApparition(){
        return dateApparition;
    }

    /**
     * Appliquer l'impact de l'événement sur la partie
     * @param p la partie sur laquelle appliquer l'impact
     */
    public void appliquerImpact(Partie p, boolean choix) {
        if (choix) {
            p.getJaugeArgent().ajouter(impactArgent);
            p.getJaugeBonheur().ajouter(impactBonheur);
            p.getJaugePedagogie().ajouter(impactPedagogie);
        } else {
            p.getJaugeArgent().ajouter(impactArgentNeg);
            p.getJaugeBonheur().ajouter(impactBonheurNeg);
            p.getJaugePedagogie().ajouter(impactPedagogieNeg);
        }
    }
}
