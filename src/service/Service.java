package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Anlaeg;
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

	// Udlejnings metoder
	public Udlejning opretUdlejning() {
		Udlejning u = new Udlejning();
		return u;
	}
	
	public void gemUdlejning(Udlejning u) {
		storage.addUdlejning(u);
	}
	
	public List<Udlejning> getAllUdlejninger() {
		return storage.getAllUdlejninger();
	}

	// Fustage metoder
	public Fustage opretFustage(String stoerrelse, String oeltype, double pris) {
		Fustage f = new Fustage(stoerrelse, oeltype, pris);
		storage.addFustage(f);
		return f;
	}
	
	public List<Fustage> getAllFustager() {
		return storage.getAllFustager();
	}

	// Kulsure metoder
	public Kulsyre opretKulsyre(double stoerrelse, double pris) {
		Kulsyre k = new Kulsyre(stoerrelse, pris);
		storage.addKulsyre(k);
		return k;
	}
	
	public List<Kulsyre> getAllKulsyre() {
		return storage.getAllKulsyre();
	}

	// anlaeg metoder
	public Anlaeg opretAnlaeg(String type, double pris, double pant) {
		Anlaeg a = new Anlaeg(type, pris, pant);
		storage.addAnlaeg(a);
		return a;
	}

	public List<Anlaeg> getAllAnleag() {
		return storage.getAllAnlaeg();
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
		SalgSted fredagsBar = opretSalgSted("Fredagsbar"); // Slet ikke
		SalgSted butik = opretSalgSted("Butik"); // Slet ikke
		SalgSted bar = opretSalgSted("Bar");

		// StedPriser
		StedPris sp1 = opretStedPris(fredagsBar, p1, 50);
		StedPris sp2 = opretStedPris(butik, p1, 75);
		StedPris sp3 = opretStedPris(bar, p1, 150);

		// Rundvisninger
		Rundvisning r1 = opretRundvisning("Madeleine", LocalDate.of(2018, 6, 26), 42, LocalTime.of(13, 37));
		r1.isStuderende();
		r1.tilmeldSpsning(21);
		r1.setBetalt();

		Rundvisning r2 = opretRundvisning("Mads", LocalDate.of(2018, 5, 4), 20, LocalTime.of(10, 00));
		r2.tilmeldSpisning();

		Rundvisning r3 = opretRundvisning("Daniel", LocalDate.of(2018, 3, 14), 65, LocalTime.of(9, 30));
		r3.setBetalt();

		//
		// Fra opgaven
		//
		ArrayList<Produkt> tempList = new ArrayList<>();
		// flaske Oel
		ProduktKategori flaske = opretProduktKategori("Flaskeoel");
		Produkt kloster = opretProdukt(flaske, "Klosterbryg", 36.0, "60cL");
		tempList.add(kloster);
		Produkt georgie = opretProdukt(flaske, "Sweet Georgie Brown", 36.0, "60cL");
		tempList.add(georgie);
		Produkt epils = opretProdukt(flaske, "Extra Pilsener", 36.0, "60cL");
		tempList.add(epils);
		Produkt celebration = opretProdukt(flaske, "Celebration", 36.0, "60cL");
		tempList.add(celebration);
		Produkt blondie = opretProdukt(flaske, "Blonie", 36.0, "60cL");
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

		Produkt monster = opretProdukt(flaske, "Black Monster", 50.0, "60.0cL");
		opretStedPris(fredagsBar, monster, 50.0);
		opretStedPris(butik, monster, 50.0);

		// Fadoel
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

		// Spiritus
		ProduktKategori spiritus = opretProduktKategori("Spiritus");
		Produkt soa = opretProdukt(spiritus, "Spirit of Aarhus", 300, "flaske");
		opretStedPris(fredagsBar, soa, 300.0);
		opretStedPris(butik, soa, 300.0);
		Produkt soaP = opretProdukt(spiritus, "Spirit of Aarhus med pind", 350.0, "flaske");
		opretStedPris(fredagsBar, soaP, 350.0);
		opretStedPris(butik, soaP, 350.0);
		Produkt whisky = opretProdukt(spiritus, "Whisky", 500.0, "flaske");
		opretStedPris(fredagsBar, whisky, 500.0);
		opretStedPris(butik, whisky, 500.0);
		Produkt loa = opretProdukt(spiritus, "Liquor of Aarhus", 175.0, "flaske");
		opretStedPris(fredagsBar, loa, 175.0);
		opretStedPris(butik, loa, 175.0);

		// fustage
		opretFustage("20 Liter", "Klosterbryg", 775.0);
		opretFustage("25 Liter", "Jazz Classic", 625.0);
		opretFustage("25 Liter", "Extra Pilsner", 575.0);
		opretFustage("20 Liter", "Celebration", 775.0);
		opretFustage("25 Liter", "Blondie", 700.0);
		opretFustage("20 Liter", "Foraarsbryg", 775.0);
		opretFustage("20 Liter", "India Pale Ale", 775.0);
		opretFustage("20 Liter", "Julebryg", 775.0);
		opretFustage("20 Liter", "Imperial Stout", 775.0);

		// Glass
		ProduktKategori glas = opretProduktKategori("Glas");
		opretProdukt(glas, "Stort Glas", 15.0, "50cL");
		opretProdukt(glas, "Lille Glas", 15.0, "30cL");
		opretProdukt(glas, "Krus", 60.0, "Krus-stoerrelse");

		// kulsyre
		opretKulsyre(6, 400.0);
		opretKulsyre(4, 400.0);
		opretKulsyre(10, 400.0);

		// Malt
		ProduktKategori bryg = opretProduktKategori("Brygtilbehoer");
		opretProdukt(bryg, "Malt", 300.0, "25 kg sæk");

		// Toej
		ProduktKategori toej = opretProduktKategori("Beklaedning");
		Produkt tshirt = opretProdukt(toej, "T-shirt", 70.0, "XS - XL");
		opretStedPris(fredagsBar, tshirt, 70.0);
		opretStedPris(butik, tshirt, 70.0);
		Produkt polo = opretProdukt(toej, "Polo", 100.0, "S - L");
		opretStedPris(fredagsBar, polo, 100.0);
		opretStedPris(butik, polo, 100.0);
		Produkt cap = opretProdukt(toej, "Cap", 30.0, "One size fits all");
		opretStedPris(fredagsBar, cap, 30.0);
		opretStedPris(butik, cap, 30.0);

		// Anlaeg
		opretAnlaeg("1-hane", 250.0, 0.0);
		opretAnlaeg("2-haner", 400.0, 0.0);
		opretAnlaeg("Bar med flere haner", 500.0, 0.0);

		// Gavepakker
		ProduktKategori gave = opretProduktKategori("Gaveaeske");
		Produkt g1 = opretProdukt(gave, "2 oel, 2 glas", 100.0, "gaveaeske");
		Produkt g2 = opretProdukt(gave, "4 oel", 130.0, "gaveaeske");
		Produkt g3 = opretProdukt(gave, "6 oel", 240.0, "traekasse");
		Produkt g4 = opretProdukt(gave, "6 oel, 2 glas", 250.0, "gavekurv");
		Produkt g5 = opretProdukt(gave, "6 oel, 6 glas", 290.0, "traekasse");
		Produkt g6 = opretProdukt(gave, "12 oel", 390.0, "treakasse");
		Produkt g7 = opretProdukt(gave, "12 oel", 360.0, "papkasse");

		// NOT DONE

		// Tilføjelse af PK til salgsted

		tilfoejKategori(butik, flaske);
		tilfoejKategori(butik, fad);
		tilfoejKategori(butik, spiritus);
		tilfoejKategori(butik, glas);
		tilfoejKategori(butik, bryg);
		tilfoejKategori(butik, toej);
		tilfoejKategori(butik, gave);
		tilfoejKategori(fredagsBar, flaske);
		tilfoejKategori(fredagsBar, fad);
		tilfoejKategori(fredagsBar, spiritus);
		tilfoejKategori(fredagsBar, toej);
		tilfoejKategori(fredagsBar, gave);
		
		
		//Udlejninger
		Udlejning u1 = opretUdlejning();
		u1.setKundeNavn("Madeleine");
		u1.setDato(LocalDate.of(2018, 11, 6));
		u1.setKundeEmail("madeleine.harbom@gmail.com");
		u1.setKundeTlf("60653173");
		u1.setLevering(true);
		gemUdlejning(u1);
		

	}

}
