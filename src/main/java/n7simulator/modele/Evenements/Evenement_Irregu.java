package n7simulator.modele.Evenements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import n7simulator.modele.Partie;
import java.sql.Connection;
import n7simulator.database.DatabaseConnection;

public class Evenement_Irregu extends Evenement{
    private LocalDate dateApparition;
    private boolean bonus;

    /**
     * Constructeur
     * @param id l'identifiant de l'événement
     * @param dateApparition la date d'apparition de l'événement
     */
    public Evenement_Irregu(int id, LocalDate dateApparition){
        super(id);

        Connection connexion = null;
        try {
            /*ouverture de connexion à la base de données*/
            connexion = DatabaseConnection.getDBConnexion();

            /*récupération des informations de l'événement*/
            String query = "SELECT Titre, description, impactBonheur, impactArgent, " +
                    "impactPedagogie, bonus " +
                    "FROM evenement_irregulier " +
                    "WHERE id_eve_irre = " + id;

            ResultSet resultDB = DatabaseConnection.effectuerRequete(query, connexion);

            // récupération des informations de l'événement
            while (resultDB.next()) {
                titre = resultDB.getString("Titre");
                description = resultDB.getString("description");
                impactBonheur = resultDB.getInt("impactBonheur");
                impactArgent = resultDB.getInt("impactArgent");
                impactPedagogie = resultDB.getInt("impactPedagogie");
                bonus = resultDB.getBoolean("bonus");
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

    /*
        * @return si l'événement est un bonus
     */
    public boolean isBonus() {
        return bonus;
    }


    /**
     * Appliquer l'impact de l'événement sur la partie
     * @param p la partie sur laquelle appliquer l'impact
     */
    public void appliquerImpact(Partie p){
        p.getJaugeArgent().ajouter(impactArgent);
        p.getJaugeBonheur().ajouter(impactBonheur);
        p.getJaugePedagogie().ajouter(impactPedagogie);
    }
}
