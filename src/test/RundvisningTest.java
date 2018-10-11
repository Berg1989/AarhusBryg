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
	
	@Test
	public void testGaester() {
		assertTrue(42== rv.getAntalGaester());
	}
	
	@Test
	public void testBetaling1antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), -5);
		rv.tilmeldSpsning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal2antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 0);
		rv.tilmeldSpsning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal3antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 14);
		rv.tilmeldSpsning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal4antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 15);
		rv.tilmeldSpsning();
		assertEquals(3450.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal5antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 19);
		rv.tilmeldSpsning();
		assertEquals(4370.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal6antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 45);
		rv.tilmeldSpsning();
		assertEquals(10350.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal7antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 74);
		rv.tilmeldSpsning();
		assertEquals(17020.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal8antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 76);
		rv.tilmeldSpsning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal9antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 100);
		rv.tilmeldSpsning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}

}
