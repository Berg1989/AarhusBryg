package test;
import model.ProduktKategori;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import model.Produkt;

public class KategoriTest {
	ProduktKategori k;
	Produkt p;
	
	@Before
	public void setUp() {
		k = new ProduktKategori("Oel");
	}
	
	@Test
	public void testCreateProcukt() {
		p = new Produkt("Pilser",50.0, "50 cL");
		assertNotNull(p);
	}
	
	
	
	
}
