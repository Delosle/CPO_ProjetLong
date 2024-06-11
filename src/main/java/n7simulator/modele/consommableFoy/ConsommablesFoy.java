package n7simulator.modele.consommableFoy;

import java.util.List;

public class ConsommablesFoy {

    public static List<ConsommableFoy> consommables;
    public  ConsommablesFoy(List<ConsommableFoy> consommablesFoy){
         consommables = consommablesFoy;
    }

    public static List<ConsommableFoy> getConsommables() {
        return consommables;
    }
}
