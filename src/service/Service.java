package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Produkt;
import model.ProduktKategori;
import model.Rundvisning;
import model.Salg;
import model.SalgSted;
import model.SalgsLinie;
import model.StedPris;
import model.Udlejning;
import storage.Storage;

public class Service {
	private static Service service;
	private Storage storage;

	private Service() {
		storage = new Storage();
	}

	public static Service getService() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	public static Service getTestService() {
		return new Service();
	}

	// PRODUKT METODER
	public Produkt opretProdukt(ProduktKategori pk, String navn, double pris, String str) {
		Produkt p = pk.createProdukt(navn, pris, str);
		return p;
	}

	public void sletProdukt(ProduktKategori pk, Produkt p) {
		pk.sletProdukt(p);
	}

	public void RedigerProdukt(ProduktKategori pk, Produkt p, String navn, double pris, String str) {
		pk.redigerNavn(p, navn);
		pk.redigerPris(p, pris);
		pk.redigerStr(p, str);
	}

	// PRODUKTKATEGORI METODER
	public ProduktKategori opretProduktKategori(String navn) {
		ProduktKategori pk = new ProduktKategori(navn);
		storage.addProduktKategori(pk);
		return pk;
	}

	public void sletProduktKategori(ProduktKategori pk) {
		storage.removeProduktKategori(pk);
	}

	public List<ProduktKategori> getAllProduktKategorier() {
		return storage.getAllProduktKategorier();
	}

	public void tilfoejKategori(SalgSted ss, ProduktKategori pk) {
		ss.addProduktKategori(pk);
	}

	public ArrayList<ProduktKategori> getMuligeKategorier(SalgSted ss) {
		ArrayList<ProduktKategori> pkList = storage.getAllProduktKategorier();
		ProduktKategori pk;
		for (int i = 0; i < ss.getProduktKategorier().size(); i++) {
			pk = ss.getProduktKategorier().get(i);
			for (int j = 0; j < storage.getAllProduktKategorier().size(); j++) {
				if (pkList.contains(pk)) {
					pkList.remove(pk);
				}
			}
		}
		return pkList;
	}

	// RUNDVISNING METODER

	public Rundvisning opretRundvisning(String kunde, LocalDate dato, int antal, LocalTime tid) {
		Rundvisning r = new Rundvisning(kunde, dato, tid, antal);
		return r;
	}

	public void gemRundvisnign(Rundvisning r) {
		storage.addRundvisning(r);
	}

	public void sletRundvisning(Rundvisning r) {
		storage.removeRundvisning(r);
	}

	public List<Rundvisning> getAllRundvisninger() {
		return storage.getAllRundvisninger();
	}

	// SALGS METODER

	public List<SalgsLinie> getAllSalgsLinier(Salg s) {
		return s.getProdukter();
	}

	public void tilfojProdukt(Salg s, int antal, Produkt p) {
		s.opretSalgslinie(antal, p);

	}

	public SalgSted opretSalgSted(String navn) {
		SalgSted ss = new SalgSted(navn);
		storage.addSalgSted(ss);
		return ss;

	}

	public void sletSalgSted(SalgSted ss) {
		for (int i = 0; i < ss.getProduktKategorier().size(); i++) {
			ProduktKategori pk = ss.getProduktKategorier().get(i);
			for (int j = 0; j < pk.getProdukter().size(); j++) {
				pk.getProdukter().get(j).removeStedPris(ss);
			}
		}
		storage.removeSalgSted(ss);
	}

	// SALGSTED METODER

	public StedPris opretStedPris(SalgSted ss, Produkt p, double pris) {
		// Tjek saa produktkategorien findes paa salgssstedet
		StedPris sp = new StedPris(ss, p, pris);
		p.addStedPris(sp);
		return sp;
	}

	public double getStedPris(SalgSted ss, Produkt p) {
		return p.getStedPrisPris(ss);
	}

	public Salg createSalg(SalgSted sted) {
		return new Salg(sted);
	}

	public void completeSalg(Salg salg) {
		storage.addSalg(salg);
	}

	public List<SalgSted> getAllSalgSted() {
		return storage.getAllSalgSted();
	}

	public List<Salg> getAllSalg() {
		return storage.getAllSalg();
	}
	
	//Udlejnings metoder
	public Udlejning opretUdlejning(String navn, String tlf, String email, LocalDate dato) {
		Udlejning u = new Udlejning(navn, tlf, email, dato);
		storage.addUdlejning(u);
		return u;
	}

	// INITIAL STUFF

	public void initStorage() {

		// Produk Kategorier
		ProduktKategori pk1 = opretProduktKategori("Kategori1");
		ProduktKategori pk2 = opretProduktKategori("Kategori2");
		ProduktKategori pk3 = opretProduktKategori("Kategori3");

		// Produkter
		Produkt p1 = opretProdukt(pk1, "TestProdukt1", 35, "0.33 L");
		Produkt p2 = opretProdukt(pk1, "TestProdukt2", 22, "0.20 CL");
		Produkt p3 = opretProdukt(pk2, "TestProdukt3", 120, "1 L");
		Produkt p4 = opretProdukt(pk3, "TestProdukt4", 350, "0.66 L");
		Produkt p5 = opretProdukt(pk3, "TestProdukt5", 10, "1.5 L");

		// Bar
		SalgSted fredagsBar = opretSalgSted("Fredagsbar");
		SalgSted butik = opretSalgSted("Butik");
		SalgSted bar = opretSalgSted("Bar");

		// StedPriser
		StedPris sp1 = opretStedPris(fredagsBar, p1, 50);
		StedPris sp2 = opretStedPris(butik, p1, 75);
		StedPris sp3 = opretStedPris(bar, p1, 150);

		// Tilføjelse af PK til salgsted

		tilfoejKategori(fredagsBar, pk1);
		tilfoejKategori(fredagsBar, pk2);
		
		//
		//Fra opgaven
		//
		ProduktKategori flaske = opretProduktKategori("Flaskeoel");
		Produkt kloster = opretProdukt(flaske, "Klosterbryg", 36.0, "60cL");
		Produkt georgie = opretProdukt(flaske, "Sweet Georgie Brown", 36.0, "60cL");
		Produkt epils = opretProdukt(flaske, "Extra Pilsener", 36.0, "60cL");
		Produkt celebration = opretProdukt(flaske, "Celebration", 36.0, "60cL");
		//NOT DONE
	}

}
