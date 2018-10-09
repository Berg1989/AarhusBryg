package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.KlippeKort;

public class KlippeKortTest {
	private KlippeKort k;

	@Before
	public void setUp() {
		k = new KlippeKort("Stort Pilsnerkort", 20, 400.0);
	}

	@Test
	public void createTest() {
		assertNotNull(k);
	}

	@Test
	public void getTestNavn() {
		assertEquals("Stort Pilsnerkort", k.getNavn());
	}

	@Test
	public void getKlipTest() {
		assertTrue(20 == k.getAntalKlip());
	}

	@Test
	public void getPrisTest() {
		assertEquals(400.0, k.getPris(), 0.001);
	}

}
