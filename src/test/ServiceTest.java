package test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;
import model.SalgSted;
import model.StedPris;
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
		ss = new SalgSted("The Flying Swan");
		ss.addProduktKategori(pk);

		
	}
	
	//Stedpriserne ligger paa produktet

	@Test
	public void TC01stedPris() {
		StedPris sp = s.opretStedPris(ss, p1, -50.0);
		assertNull(sp);
	}
	
	@Test
	public void TC02stedPris() {
		StedPris sp = s.opretStedPris(ss, p1, 0.0);
		assertNotNull(sp);
	}
	
	@Test
	public void TC03stedPris() {
		StedPris sp = s.opretStedPris(ss, p1, 30.0);
		assertNotNull(sp);
	}
	
	
	
	
	

}
