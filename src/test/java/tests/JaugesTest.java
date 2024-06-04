package tests;

import static org.junit.Assert.assertEquals;

import n7simulator.modele.jauges.Jauge;
import n7simulator.modele.jauges.JaugeBornee;
import org.junit.Before;
import org.junit.Test;

public class JaugesTest {

    private Jauge jauge1, jauge2;
    private JaugeBornee jauge3;

    @Before
    public void setUp(){
        jauge1 = new Jauge("jaugeClassique1");
        jauge2 = new Jauge("jaugeClassique2", 10);
        jauge3 = new JaugeBornee("jaugeBornee1");
    }

    /**
     * Test vérifiant les noms des jauges
     */
    @Test
    public void testNoms(){
        assertEquals("jaugeClassique1", jauge1.getNom());
        assertEquals("jaugeClassique2", jauge2.getNom());
        assertEquals("jaugeBornee1", jauge3.getNom());
    }

    /**
     * Test vérifiant les valeurs initiales des jauges
     */
    @Test
    public void testValeuresInitiales(){
        assertEquals(0, jauge1.getValue());
        assertEquals(10, jauge2.getValue());
        assertEquals(0, jauge3.getValue());
    }

    /**
     * Test vérifiant que les valeurs des jauges sont correctement
     * modifiées en ajoutant des valeurs
     */
    @Test
    public void testAjouter(){
        jauge1.ajouter(15);
        jauge2.ajouter(-5);
        jauge3.ajouter(30);
        assertEquals(15, jauge1.getValue());
        assertEquals(5, jauge2.getValue());
        assertEquals(30, jauge3.getValue());
    }

    /**
     * Test pour se rassurer que la valeure des
     * jauges n'est jamais inférieure à zéro
     */
    @Test
    public  void testValeurLimitesInferieur(){
        jauge1.ajouter(-100);
        jauge2.ajouter(-10);
        jauge3.ajouter(-60);
        assertEquals(0, jauge1.getValue());
        assertEquals(0, jauge2.getValue());
        assertEquals(0, jauge3.getValue());
    }

    /**
     * Pour une jauge Bornée, on veut vérifier que
     * la valeur de la jauge ne dépasse pas la valeur
     * maximale.
     */
    @Test
    public void testValeurLimiteSuperieur(){
        jauge3.ajouter(150);
        assertEquals(JaugeBornee.BORNE_MAX, jauge3.getValue());
    }
}
