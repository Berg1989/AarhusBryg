package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.KlippeKort;
import model.Rundvisning;

public class RundvisningTest {
	private Rundvisning rv;

	@Before
	public void setUp() {
		rv = new Rundvisning("Bug Day Celebration", LocalDate.of(1902, 12, 9), LocalTime.of(13, 37), 42);
	}
	
	@Test
	public void testRundvisning() {
		assertNotNull(rv);
	}
	
	@Test
	public void testKundeNavn() {
		assertEquals("Bug Day Celebration", rv.getKunde());
	}
	
	@Test
	public void testDato() {
		assertEquals(LocalDate.of(1902, 12, 9), rv.getDato());
	}
	
	@Test
	public void testTid() {
		assertEquals(LocalTime.of(13, 37), rv.getTid());
	}
	
	public void testGaester() {
		assertTrue(42== rv.getAntalGaester());
	}

}
