package n7simulator.modele.professeur;

import java.util.List;
import java.util.Observable;

public class GestionProfesseurs extends Observable {
	private List<Professeur> professeursEmbauches;
    private List<Professeur> professeursNonEmbauches;

    public GestionProfesseurs(List<Professeur> professeursEmbauches, List<Professeur> professeursNonEmbauches) {
        this.professeursEmbauches = professeursEmbauches;
        this.professeursNonEmbauches = professeursNonEmbauches;
    }
    
    public List<Professeur> getProfesseursEmbauches() {
        return professeursEmbauches;
    }

    public List<Professeur> getProfesseursNonEmbauches() {
        return professeursNonEmbauches;
    }

    public void embaucherProfesseur(Professeur professeur) {
        if (professeursNonEmbauches.remove(professeur)) {
            professeursEmbauches.add(professeur);
            this.setChanged();
    		this.notifyObservers(this);
        }
    }

    public void licencierProfesseur(Professeur professeur) {
        if (professeursEmbauches.remove(professeur)) {
            professeursNonEmbauches.add(professeur);
            this.setChanged();
    		this.notifyObservers(this);
        }
    }

}
