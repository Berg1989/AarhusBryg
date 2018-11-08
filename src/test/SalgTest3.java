package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;
import model.Salg;
import model.SalgSted;

public class SalgTest3 {
	ProduktKategori pk;
	Produkt p1, p2;
	SalgSted ss;
	Salg s;
	
	@Before
	public void setUp() {
		pk = new ProduktKategori("Oel");
		p1 = pk.createProdukt("BerlinerWeisse", 30.0, "33cL");
		p2 = pk.createProdukt("Passionfruit IPA", 50.0, "50 cL");
		ss = new SalgSted("The Mended Drum");
		ss.addProduktKategori(pk);
		s = new Salg(ss);
	
	}
	
	
	@Test
	public void TC01Salg() {
		Salg salg = new Salg(ss);
		assertNotNull(salg);
		
	}
	
	public void TC02Salg() {
		Salg salg = new Salg();
		assertNull(salg);
	}
	
	public void TC01pris() {
		s.opretSalgslinie(1,	p1);
		assertEquals(30.00, s.getTotalPris(), 0.001);
	}
	
	public void TC02pris() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(2, p2);
		assertEquals(250.00, s.getTotalPris(), 0.001);
	}
	public void TC03pris() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(2, p2);
		s.opretSalgslinie(4, p2);
		assertEquals(450.00, s.getTotalPris(), 0.001);
	}
	
	public void TC04pris() {
		assertEquals(0.00, s.getTotalPris(), 0.001);
	}

	
	

}

