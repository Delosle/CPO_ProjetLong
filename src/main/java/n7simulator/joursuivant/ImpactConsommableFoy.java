package n7simulator.joursuivant;

import n7simulator.modele.Partie;

public class ImpactConsommableFoy implements ImpactJourSuivant{

    private double impactBonheur;

    private double impactArgent;

    private static ImpactConsommableFoy instance;

    public ImpactConsommableFoy(){
        instance = this;
    }

    @Override
    public void effectuerImpactJourSuivant() {
        Partie laPartie = Partie.getInstance();
        laPartie.getJaugeArgent().ajouter(impactArgent*laPartie.getGestionEleves().getNombreEleves());
        laPartie.getJaugeBonheur().ajouter(impactBonheur);
    }

    public void setImpactBonheur(double newImpactB){
        impactBonheur = newImpactB;
    }

    public void setImpactArgent(double newImpactA){
        impactArgent = newImpactA;
    }

    public static ImpactConsommableFoy getInstance(){
        if(instance == null){
            instance = new ImpactConsommableFoy();
        }
        return instance;
    }
}
