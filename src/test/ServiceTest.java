package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ProduktKategori;

public class ServiceTest {
	private ProduktKategori pk;

	
	@Before
	public void setUp() {
		pk = new ProduktKategori("testkategori");
		pk.createProdukt("testProdukt", 50.00, "stor");
		pk.createProdukt("testprodukt", 30.0, "lille");
		
	}

	@Test
	public void test() {
		assertEquals(2, pk.getProdukter().size());
	}

}
