package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Produkt;
import model.ProduktKategori;
import model.Salg;
import model.SalgSted;
import model.SalgsLinie;

public class SalgTest2 {
	ProduktKategori pk;
	Produkt p1, p2;
	SalgSted ss;
	Salg s;
	SalgsLinie sl;
	
	
	@Before
	public void setUp1() {
		pk = new ProduktKategori("Oel");
		p1 = pk.createProdukt("BerlinerWeisse", 30.0, "33cL");
		p2 = pk.createProdukt("Passionfruit IPA", 50.0, "50 cL");
		ss = new SalgSted("The Mended Drum");
		ss.addProduktKategori(pk);
		s = new Salg(ss);
		s.opretSalgslinie(5, p1);
		s.opretSalgslinie(2, p2);
		s.opretSalgslinie(7, p2);
		s.setTid(LocalTime.of(13, 37));	
		s.setDato(LocalDate.of(2016, 5, 4));
	}
	

	
	@Test
	public void TC01slet() {
		sl = s.getProdukter().get(0);
		s.sletSalgsLinie(sl);
		assertEquals(2, s.getProdukter().size());
	}
	
	public void TC02slet() {
		sl = s.getProdukter().get(0);
		s.sletSalgsLinie(sl);
		sl = s.getProdukter().get(0);
		s.sletSalgsLinie(sl);
		assertEquals(1, s.getProdukter().size());
	}
	
	public void TC03slet() {
		ArrayList<SalgsLinie> produkter = new ArrayList<>(s.getProdukter());
		for (SalgsLinie linje : produkter) {
			s.sletSalgsLinie(linje);
		}
		assertEquals(0, s.getProdukter().size());
	}
	
	public void TC04slet() {
		SalgsLinie idontexist;
		s.sletSalgsLinie(idontexist);
		assertEquals(3, s.getProdukter().size());
	}
	
	public void TC01getP() {
		assertEquals(3, s.getProdukter().size());
	}
	
	public void TC02getP() {
		assertEquals(30.0, s.getProdukter().get(0).getProduktPris(), 0.0001);
	}
	
	public void TC01getTid() {
		assertEquals(LocalTime.of(13, 37), s.getTid());
	}
	
	public void TC02getTid() {
		s.setTid(LocalTime.of(14,42));
		assertEquals(LocalTime.of(14, 42), s.getTid());
	}
	
	public void TC01getDato() {
		assertEquals(LocalDate.of(2016, 5, 4), s.getDato());
	}
	
	public void TC02getDato() {
		s.setDato(LocalDate.of(1912, 4, 14));
		assertEquals(LocalDate.of(1912, 4, 14), s.getDato());
	}
	

	
}
