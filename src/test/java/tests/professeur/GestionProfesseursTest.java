package tests.professeur;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import n7simulator.modele.professeur.GestionProfesseurs;
import n7simulator.modele.professeur.Professeur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class GestionProfesseursTest {

    private GestionProfesseurs gestionProfesseurs;
    private List<Professeur> professeursEmbauches;
    private List<Professeur> professeursNonEmbauches;
    private Professeur professeurA;
    private Professeur professeurB;

    @Before
    public void setUp() {
        professeursEmbauches = new ArrayList<>();
        professeursNonEmbauches = new ArrayList<>();
        professeurA = Mockito.mock(Professeur.class);
        professeurB = Mockito.mock(Professeur.class);
        professeursEmbauches.add(professeurA);
        professeursNonEmbauches.add(professeurB);
        gestionProfesseurs = new GestionProfesseurs(professeursEmbauches, professeursNonEmbauches);
    }

    @Test
    public void testGetProfesseursEmbauches() {
        assertEquals(professeursEmbauches, gestionProfesseurs.getProfesseursEmbauches());
        assertEquals(professeurA, gestionProfesseurs.getProfesseursEmbauches().get(0));
    }

    @Test
    public void testGetProfesseursNonEmbauches() {
        assertEquals(professeursNonEmbauches, gestionProfesseurs.getProfesseursNonEmbauches());
        assertEquals(professeurB, gestionProfesseurs.getProfesseursNonEmbauches().get(0));
    }

    @Test
    public void testEmbaucherProfesseur() {
        Observer observer = Mockito.mock(Observer.class);
        gestionProfesseurs.addObserver(observer);

        gestionProfesseurs.embaucherProfesseur(professeurB);

        assertTrue(professeursEmbauches.contains(professeurB));
        assertFalse(professeursNonEmbauches.contains(professeurB));
        Mockito.verify(observer).update(Mockito.eq(gestionProfesseurs), Mockito.any());
    }

    @Test
    public void testLicencierProfesseur() {
        professeursEmbauches.add(professeurB);
        Observer observer = Mockito.mock(Observer.class);
        gestionProfesseurs.addObserver(observer);

        gestionProfesseurs.licencierProfesseur(professeurB);

        assertFalse(professeursEmbauches.contains(professeurB));
        assertTrue(professeursNonEmbauches.contains(professeurB));
        Mockito.verify(observer).update(Mockito.eq(gestionProfesseurs), Mockito.any());
    }
}
