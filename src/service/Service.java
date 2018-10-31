package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Fustage;
import model.Kulsyre;
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
	
	//Fustage metoder
	public Fustage opretFustage(String stoerrelse, String oeltype, double pris) {
		Fustage f = new Fustage(stoerrelse, oeltype, pris);
		storage.addFustage(f);
		return f;
	}
	
	//Kulsure metoder
	public Kulsyre opretKulsure(double stoerrelse, double pris) {
		Kulsyre k = new Kulsyre(stoerrelse, pris);
		storage.addKulsyre(k);
		return k;
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
		SalgSted fredagsBar = opretSalgSted("Fredagsbar"); //Slet ikke
		SalgSted butik = opretSalgSted("Butik"); //Slet ikke
		SalgSted bar = opretSalgSted("Bar");

		// StedPriser
		StedPris sp1 = opretStedPris(fredagsBar, p1, 50);
		StedPris sp2 = opretStedPris(butik, p1, 75);
		StedPris sp3 = opretStedPris(bar, p1, 150);

		// Tilføjelse af PK til salgsted

		tilfoejKategori(fredagsBar, pk1); 
		tilfoejKategori(fredagsBar, pk2);
		
		//Rundvisninger
		
		
		//kulsyre
		
		
		
		//
		//Fra opgaven
		//
		ArrayList<Produkt> tempList = new ArrayList<>();
		//flaske Oel
		ProduktKategori flaske = opretProduktKategori("Flaskeoel");
		Produkt kloster = opretProdukt(flaske, "Klosterbryg", 36.0, "60cL");
		tempList.add(kloster);
		Produkt georgie = opretProdukt(flaske, "Sweet Georgie Brown", 36.0, "60cL");
		tempList.add(georgie);
		Produkt epils = opretProdukt(flaske, "Extra Pilsener", 36.0, "60cL");
		tempList.add(epils);
		Produkt celebration = opretProdukt(flaske, "Celebration", 36.0, "60cL");
		tempList.add(celebration);
		Produkt blondie =opretProdukt(flaske, "Blonie", 36.0, "60cL");
		tempList.add(blondie);
		Produkt foraars = opretProdukt(flaske, "Foraarsbryg", 36.0, "60cL");
		tempList.add(foraars);
		Produkt ipa = opretProdukt(flaske, "Indian Pale Ale", 36.0, "60cL");
		tempList.add(ipa);
		Produkt julebryg = opretProdukt(flaske, "Julebryg", 36.0, "60cL");
		tempList.add(julebryg);
		Produkt juletonde = opretProdukt(flaske, "Juletoenden", 36.0, "60.0cL");
		tempList.add(juletonde);
		Produkt oldStrong = opretProdukt(flaske, "Old Strong Ale", 36.0, "60.0cL");
		tempList.add(oldStrong);
		Produkt fregatten = opretProdukt(flaske, "Fregratten Jylland", 36.0, "60.0cL");
		tempList.add(fregatten);
		Produkt impStout = opretProdukt(flaske, "Imperial Stout", 36.0, "60.0cL");
		tempList.add(impStout);
		Produkt tribute = opretProdukt(flaske, "Tribute", 36.0, "60.0cL");
		tempList.add(tribute);
		for (Produkt p : tempList) {
			opretStedPris(fredagsBar, p, 50.0);
			opretStedPris(butik, p, 36.0);
		}
		tempList.clear();
		Produkt monster = opretProdukt(flaske, "Black Monster", 50.0 , "60.0cL");
		opretStedPris(fredagsBar, monster, 50.0);
		opretStedPris(butik, monster, 50.0);
		
		//Fadoel
		ProduktKategori fad = opretProduktKategori("Fadoel");
		Produkt klosterF = opretProdukt(fad, "Klosterbryg", 30.0, "40cL");
		tempList.add(klosterF);
		Produkt jazzC = opretProdukt(fad, "Jazz Classic", 30.0, "40cL");
		tempList.add(jazzC);
		Produkt EpilsF = opretProdukt(fad, "Extra Pilsner", 30.0, "40cL");
		tempList.add(EpilsF);
		Produkt celebrationF = opretProdukt(fad, "Celebration", 30.0, "40cL");
		tempList.add(celebrationF);
		Produkt blondieF = opretProdukt(fad, "Blondie", 30.0, "40cL");
		tempList.add(blondieF);
		Produkt foraarF = opretProdukt(fad, "Foraarsbryg", 30.0, "40cL");
		tempList.add(foraarF);
		Produkt juleBF = opretProdukt(fad, "Julebryg", 30.0, "40cL");
		tempList.add(juleBF);
		Produkt impStoutF = opretProdukt(fad, "Imperial Stout", 30.0, "40cL");
		tempList.add(impStoutF);
		Produkt special = opretProdukt(fad, "Special", 30.0, "40cL");
		tempList.add(special);
		for (Produkt p : tempList) {
			opretStedPris(fredagsBar, p, 30.0);
		}
		tempList.clear();
		Produkt brus = opretProdukt(fad, "Special", 15.0, "40cL");
		tempList.add(brus);
		ProduktKategori virgin = opretProduktKategori("Alkoholfri");
		Produkt cola = opretProdukt(virgin, "Cola", 15.0, "40cL");
		tempList.add(cola);
		Produkt niko = opretProdukt(virgin, "Nikoline", 15.0, "40cL");
		tempList.add(niko);
		Produkt up = opretProdukt(virgin, "7-UP", 15.0, "40cL");
		tempList.add(up);
		for (Produkt p : tempList) {
			opretStedPris(fredagsBar, p, 15.0);
		}
		Produkt vand = opretProdukt(virgin, "Vand", 10.0, "40cL");
		opretStedPris(fredagsBar, vand, 10.0);
		tempList.clear();
		ProduktKategori snacks = opretProduktKategori("Snacks");
		Produkt chips = opretProdukt(snacks, "Chips", 10.0, "pose");
		opretStedPris(fredagsBar, chips, 10.0);
		Produkt nuts = opretProdukt(snacks, "Peanuts", 10.0, "sk�l");
		opretStedPris(fredagsBar, nuts, 10.0);
		
		//Spiritus
		ProduktKategori spiritus = opretProduktKategori("Spiritus");
		Produkt soa = opretProdukt(spiritus, "Spirit of Aarhus", 300, "flaske");
		opretStedPris(fredagsBar, soa, 300.0);
		opretStedPris(butik,soa, 300.0);
		Produkt soaP = opretProdukt(spiritus, "Spirit of Aarhus med pind", 350.0, "flaske");
		opretStedPris(fredagsBar, soaP, 350.0);
		opretStedPris(butik, soaP, 350.0);
		Produkt whisky = opretProdukt(spiritus, "Whisky", 500.0, "flaske");
		opretStedPris(fredagsBar, whisky, 500.0);
		opretStedPris(butik, whisky, 500.0);
		Produkt loa = opretProdukt(spiritus, "Liquor of Aarhus", 175.0, "flaske");
		opretStedPris(fredagsBar, loa, 175.0);
		opretStedPris(butik, loa, 175.0);
		
		//fustage
		opretFustage("20 Liter", "Klosterbryg", 775);
		opretFustage("25 Liter", "Jazz Classic", 625);
		opretFustage("25 Liter", "Extra Pilsner", 575);
		opretFustage("20 Liter", "Celebration", 775);
		opretFustage("25 Liter", "Blondie", 700);
		opretFustage("20 Liter", "Foraarsbryg", 775);
		opretFustage("20 Liter", "India Pale Ale", 775);
		opretFustage("20 Liter", "Julebryg", 775);
		opretFustage("20 Liter", "Imperial Stout", 775);
		
		
		
		
		
		
		//NOT DONE
	}

}
