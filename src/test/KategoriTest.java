package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;

public class KategoriTest {
	private ProduktKategori k;
	private Produkt p;

	@Before
	public void setUp() {
		k = new ProduktKategori("Oel");
	}

	@Test
	public void testCreateProdukt1() {
		p = k.createProdukt("Pilser", 50.0, "50 cL");
		assertNotNull(p);
	}

	@Test(expected = java.lang.Error.class)
	public void testCreateProduct2() {
		p = k.createProdukt(50.0, 100.0, "50 cL");
	}

	@Test(expected = java.lang.Error.class)
	public void testCreateProduct3() {
		p = k.createProdukt("Bock", "Dyr", "50 cL");
	}

	@Test(expected = java.lang.Error.class)
	public void testCreateProduct4() {
		p = k.createProdukt("Pilsner", 50.0, 100.0);
	}

	@Test
	public void testGetNavn() {
		k.toString();
		assertEquals("Oel", k.toString());
	}

}
