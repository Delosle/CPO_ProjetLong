package tests.professeur;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import n7simulator.modele.professeur.Matiere;

public class MatiereTest {

    private Matiere matiere;

    @Before
    public void setUp() {
        matiere = new Matiere(1, "Mathématiques", 50, 80);
    }

    @Test
    public void testGetId() {
        assertEquals(1, matiere.getId());
    }

    @Test
    public void testGetNom() {
        assertEquals("Mathématiques", matiere.getNom());
    }

    @Test
    public void testGetBonheur() {
        assertEquals(50, matiere.getBonheur());
    }

    @Test
    public void testGetPedagogie() {
        assertEquals(80, matiere.getPedagogie());
    }
}
