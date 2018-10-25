package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.wsdl.writer.document.Service;

import model.ProduktKategori;

public class ServiceTest {
	private Service s = Service.getService();
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
	
	public void test2() {
		Service.remove
	}

}
