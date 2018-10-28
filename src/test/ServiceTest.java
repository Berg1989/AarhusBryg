package test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;
import model.SalgSted;

import service.Service;

public class ServiceTest {
	private Service s = Service.getService();
	private ProduktKategori pk;
	Produkt p1;
	Produkt p2;
	private SalgSted ss;

	
	@Before
	public void setUp() {
		pk = new ProduktKategori("Cocktials");
		p1 = pk.createProdukt("Gin and Tonic", 50.00, "stor");
		p2 = pk.createProdukt("Dark And Stormy", 30.0, "lille");
		ss = new SalgSted("The Flying Swan");
		ss.addProduktKategori(pk);
		s.opretStedPris(ss, p1, 40.0);
		s.opretStedPris(ss, p2, 20.0);
		
	}
	
	//Stedpriserne ligger paa produktet

	@Test
	public void test1() {
		assertTrue(1==p1.getStedPriser().size());
		
	}
	
	
	
	
	@Test
	public void test2() {
		s.sletSalgSted(ss);
		assertTrue(0==p1.getStedPriser().size());
		
	}


}
