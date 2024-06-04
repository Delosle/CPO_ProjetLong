package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CreationBddAdminTest {

	@Before public void setUp() {
		n7simulator.database.CreationBddAdmin.initialiserBddAdmin();
	}
	
	@Test
	public void testInitialisationBDD() {
		assertEquals(true, n7simulator.database.CreationBddAdmin.initialiserBddAdmin());
	}

}
