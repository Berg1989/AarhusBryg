package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;
import model.Salg;
import model.SalgSted;

public class SalgTest1 {
	ProduktKategori pk;
	Produkt p1, p2;
	SalgSted ss;
	Salg s;
	
	
	@Before
	public void setUp1() {
		pk = new ProduktKategori("Oel");
		p1 = pk.createProdukt("BerlinerWeisse", 30.0, "33cL");
		p2 = pk.createProdukt("Passionfruit IPA", 50.0, "50 cL");
		ss = new SalgSted("The Mended Drum");
		ss.addProduktKategori(pk);
		s = new Salg(ss);
	
	}

	@Test
	public void TC01opret() {
		s.opretSalgslinie(-2, p1);
		assertEquals(0, s.getProdukter().size());
	}
	
	@Test
	public void TC02opret() {
		s.opretSalgslinie(0, p1);
		assertEquals(0, s.getProdukter().size());
	}
	
	@Test
	public void TC03opret() {
		s.opretSalgslinie(3, p1);
		assertEquals(1, s.getProdukter().size());
	}
	
	
	
	@Test
	public void TC04opret() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(2, p2);
		s.opretSalgslinie(4, p2);
		assertEquals(3, s.getProdukter().size());
	}
	
	@Test
	public void TC01pris() {
		s.opretSalgslinie(1, p1);
		assertEquals(30.0, s.getTotalPris(), 0.001);
	}
	
	@Test
	public void TC02pris() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(2, p2);
		assertEquals(250.0, s.getTotalPris(), 0.01);
	}
	
	@Test
	public void TC03pris() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(2, p2);
		s.opretSalgslinie(4, p2);
		assertEquals(450.0, s.getTotalPris(), 0.01);
	}
	
	public void TC04pris() {
		assertEquals(0.0, s.getTotalPris(), 0.01);
	}
	
}
