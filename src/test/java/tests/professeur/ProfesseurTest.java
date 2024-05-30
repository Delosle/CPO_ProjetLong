package tests.professeur;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import n7simulator.modele.professeur.Matiere;
import n7simulator.modele.professeur.Professeur;

import java.util.Observer;
import java.util.Observable;
import static org.mockito.Mockito.*;

public class ProfesseurTest {

    private Professeur professeur;
    private Matiere matiere;

    @Before
    public void setUp() {
        matiere = mock(Matiere.class); // Utilisation de Mockito pour simuler la classe Matiere
        professeur = new Professeur(1, "Nom", "Prenom", "Description", matiere, 5, 20);
    }

    @Test
    public void testGetId() {
        assertEquals(1, professeur.getId());
    }

    @Test
    public void testGetNom() {
        assertEquals("Nom", professeur.getNom());
    }

    @Test
    public void testGetPrenom() {
        assertEquals("Prenom", professeur.getPrenom());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Description", professeur.getDescription());
    }

    @Test
    public void testGetSalaireActuel() {
    	//au debut salaireMin == salaireActuel
        assertEquals(20, professeur.getSalaireActuel());
    }

    @Test
    public void testSetSalaireActuel() {
        professeur.setSalaireActuel(30);
        assertEquals(30, professeur.getSalaireActuel());
    }

    @Test
    public void testGetMatiere() {
        assertEquals(matiere, professeur.getMatiere());
    }
    
    @Test
    public void testGetSalaireMin() {
        assertEquals(20, professeur.getSalaireActuel());
    }
    
    @Test
    public void testGetNiveau() {
        assertEquals(5, professeur.getNiveau());
    }
    
    @Test
    public void testGetNbHeuresTravaillees() {
        assertEquals(0, professeur.getNbHeuresTravaillees());
    }
    
    @Test
    public void testSetNbHeuresTravaillees() {
        professeur.setNbHeuresTravaillees(3);
        assertEquals(3, professeur.getNbHeuresTravaillees());
    }
    
    @Test
    public void testModifierContrat() {
        professeur.modifierContrat(25, 15);
        assertEquals(25, professeur.getSalaireActuel());
        assertEquals(15, professeur.getNbHeuresTravaillees());
    }
}
 
