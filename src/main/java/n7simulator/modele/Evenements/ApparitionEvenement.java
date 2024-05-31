package n7simulator.modele.Evenements;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import n7simulator.database.DatabaseConnection;

public class ApparitionEvenement {

    private

    public ApparitionEvenement() {

    }

    private List< LinkedHashMap <String, Object>> recupDonneeEvenement(){
        Connection conn = DatabaseConnection.getDBConnexion();
        int nbEvenement = DatabaseConnection.effectuerRequete("SELECT MAX(idPartie) as maxId FROM Partie;", conn).getInt("maxId");
    }
}
