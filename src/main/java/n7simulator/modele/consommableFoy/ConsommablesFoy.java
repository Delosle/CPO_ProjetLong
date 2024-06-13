package n7simulator.modele.consommableFoy;

import java.util.List;

/**
 * Classe representant la liste des consommables de la partie
 */
public class ConsommablesFoy {

    private static List<ConsommableFoy> consommables;
    
    private ConsommablesFoy(){
    }
    
    public static void setConsommablesListe(List<ConsommableFoy> newConsommables) {
    	consommables = newConsommables;
    }
    

    public static List<ConsommableFoy> getConsommables() {
        return consommables;
    }
}
