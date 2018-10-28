package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;
import model.Salg;
import model.SalgSted;

public class SalgTest {
	ProduktKategori pk;
	Produkt p1;
	Produkt p2;
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
	public void test1() {
		s.opretSalgslinie(-5, p2);
		assertEquals(0.0, s.getTotalPris(), 0.0001);
	}
	
	@Test
	public void test2() {
		s.opretSalgslinie(0, p2);
		assertEquals(0.0, s.getTotalPris(), 0.0001);
	}
	
	
	@Test
	public void test3() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(10, p2);
		assertEquals(650.0, s.getTotalPris(), 0.0001);
	}
	
	@Test
	public void test4() {
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(10, p2);
		s.opretSalgslinie(20, p1);
		assertEquals(1250.0, s.getTotalPris(), 0.0001);
	}
	
}
