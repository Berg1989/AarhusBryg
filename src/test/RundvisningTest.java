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
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal2antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 0);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal3antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 14);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal4antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 15);
		rv.tilmeldSpisning();
		assertEquals(3450.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal5antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 19);
		rv.tilmeldSpisning();
		assertEquals(4370.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal6antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 45);
		rv.tilmeldSpisning();
		assertEquals(10350.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal7antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 74);
		rv.tilmeldSpisning();
		assertEquals(17020.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal8antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 76);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
		
	}
	
	@Test
	public void testBetal9antalGaester() {
		rv = new Rundvisning("eaaa", LocalDate.of(2018, 9, 28), LocalTime.of(13, 0), 100);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal1Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(9, 0), 15);
		assertEquals(1500.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal2Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(13, 59), 15);
		assertEquals(1500.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal3Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(13, 59), 15);
		rv.tilmeldSpisning();
		assertEquals(3450.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal4Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(15, 00), 15);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal5Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(15, 0), 15);
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal6Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(15, 0 ), 20);
		rv.tilmeldSpisning();
		assertEquals(5000.0, rv.beregnPris(), 0.001);
	}
	@Test
	public void testBetal7Torsdag() {
		rv = new Rundvisning("Lucia", LocalDate.of(2018, 12, 13), LocalTime.of(15, 0 ), 20);
		assertEquals(2400.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal1Lordag() {
		rv = new Rundvisning("Bryllop", LocalDate.of(2014, 12, 13), LocalTime.of(9, 0), 20);
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal2Lordag() {
		rv = new Rundvisning("Bryllop", LocalDate.of(2014, 12, 13), LocalTime.of(14, 59), 20);
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal3Lordag() {
		rv = new Rundvisning("Bryllop", LocalDate.of(2014, 12, 13), LocalTime.of(14, 59), 20);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	
	@Test
	public void testBetal4Lordag() {
		rv = new Rundvisning("Bryllop", LocalDate.of(2014, 12, 13), LocalTime.of(15, 0), 20);
		rv.tilmeldSpisning();
		assertEquals(0.0, rv.beregnPris(), 0.001);
	}
	

}
