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

	// Attributter for klassen
	private static Service service;
	private Storage storage;

	// Denne metode laver en ny Storage
	private Service() {
		storage = new Storage();
	}

	// Denne metode tjekker om service eksistere, hvis ikke så laves der en ny
	// service. (Singleton)
	public static Service getService() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	// Denne metode returnere den nye service
	public static Service getTestService() {
		return new Service();
	}

	// ------------------- Produkt Metoder ---------------------------
	// Denne metode opretter et produkt på en produktkategori.
	public Produkt opretProdukt(ProduktKategori pk, String navn, double pris, String str) {
		Produkt p = pk.createProdukt(navn, pris, str);
		return p;
	}

	// Denne metode sletter et produkt fra en produktkategori
	public void sletProdukt(ProduktKategori pk, Produkt p) {
		pk.removeProdukt(p);
	}

	// Denne metode redigere et produkt fra en produktkategori
	public void RedigerProdukt(ProduktKategori pk, Produkt p, String navn, double pris, String str) {
		pk.redigerNavn(p, navn);
		pk.redigerPris(p, pris);
		pk.redigerStr(p, str);
	}

	// ------------------- Produktkategori Metoder ---------------------------
	// Denne metode opretter en produkt kategori, og tilføjer den til storage
	public ProduktKategori opretProduktKategori(String navn) {
		ProduktKategori pk = new ProduktKategori(navn);
		storage.addProduktKategori(pk);
		return pk;
	}

	// Denne metode sletter en produktkategori fra storage
	public void sletProduktKategori(ProduktKategori pk) {
		storage.removeProduktKategori(pk);
	}

	// Denne metode returnere alle produktkategori objekter fra storage
	public List<ProduktKategori> getAllProduktKategorier() {
		return storage.getAllProduktKategorier();
	}

	// Denne metode tilføjer en produktkategori til et salgssted
	public void tilfoejKategori(SalgSted ss, ProduktKategori pk) {
		ss.addProduktKategori(pk);
	}

	// Denne metode viser alle mulige produktkategorier som ikke allerede eksister
	// hos det enkelte salgssted.
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
	// ------------------- Rundvisnings Metoder ---------------------------

	// Denne metode oprreter en rundvisning
	public Rundvisning opretRundvisning(String kunde, LocalDate dato, int antal, LocalTime tid) {
		Rundvisning r = new Rundvisning(kunde, dato, tid, antal);
		return r;
	}

	// Denne metoder tilføjer rundvisningen fra parameteren til storage
	public void gemRundvisning(Rundvisning r) {
		storage.addRundvisning(r);
	}

	// Denne metode sletter rundvisningen i parameteren fra storage
	public void sletRundvisning(Rundvisning r) {
		storage.removeRundvisning(r);
	}

	// Denne metode returnere alle rundvisnings objekter fra storage
	public List<Rundvisning> getAllRundvisninger() {
		return storage.getAllRundvisninger();
	}

	// ------------------- Salgs Metoder ---------------------------

	// Denne metode returnere alle produkt objekter fra en salgslinie
	public List<SalgsLinie> getAllSalgsLinier(Salg s) {
		return s.getProdukter();
	}

	// Denne metode tilføjer en salgslinie til et salg
	public void tilfojProdukt(Salg s, int antal, Produkt p) {
		s.opretSalgslinie(antal, p);

	}

	// Denne metode opretter et salgssted og tilføjer det til storage
	public SalgSted opretSalgSted(String navn) {
		SalgSted ss = new SalgSted(navn);
		storage.addSalgSted(ss);
		return ss;

	}

	// Denne metode sletter et salgssted samt det salgssteds stedpriser.
	public void sletSalgSted(SalgSted ss) {
		for (int i = 0; i < ss.getProduktKategorier().size(); i++) {
			ProduktKategori pk = ss.getProduktKategorier().get(i);
			for (int j = 0; j < pk.getProdukter().size(); j++) {
				pk.getProdukter().get(j).removeStedPris(ss);
			}
		}
		storage.removeSalgSted(ss);
	}

	// ------------------- Salgssted Metoder ---------------------------

	// Denne metode opretter en stedpris og tilføjer derefter stedprisen på
	// produktet i parameteren
	public StedPris opretStedPris(SalgSted ss, Produkt p, double pris) {
		// Tjek saa produktkategorien findes paa salgssstedet?????
		StedPris sp = new StedPris(ss, p, pris);
		p.addStedPris(sp);
		return sp;
	}

	// Denne metode returnere et produkts stedpris
	public double getStedPris(SalgSted ss, Produkt p) {
		return p.getStedPrisPris(ss);
	}

	// ???????????????????????++
	public Salg createSalg(SalgSted sted) {
		return new Salg(sted);
	}

	// Denne metode tilføjer et salg til storage
	public void completeSalg(Salg salg) {
		storage.addSalg(salg);
	}

	// Denne metode returnere alle salgssted objekter fra storage
	public List<SalgSted> getAllSalgSted() {
		return storage.getAllSalgSted();
	}

	// Denne metode returnere alle salg objekter fra storage
	public List<Salg> getAllSalg() {
		return storage.getAllSalg();

	}

	// ------------------- Udlejnings Metoder ---------------------------

	// Denne metode opretter en udlejning
	public Udlejning opretUdlejning() {
		Udlejning u = new Udlejning();
		return u;
	}

	// Denne metode tilføjer en udlejning til storage
	public void gemUdlejning(Udlejning u) {
		storage.addUdlejning(u);
	}

	// Denne metode returnere alle udlejnings objekter fra storage
	public List<Udlejning> getAllUdlejninger() {
		return storage.getAllUdlejninger();
	}

	// Denne metode sletter udlejningen som der er i parameteren fra storage
	public void sletUdlejning(Udlejning u) {
		storage.removeUdlejning(u);
	}

	// ------------------- Fustage Metoder ---------------------------

	// Denne metode opretter et fustage objekt og tilføjer den til storage
	public Fustage opretFustage(String stoerrelse, String oeltype, double pris) {
		Fustage f = new Fustage(stoerrelse, oeltype, pris);
		storage.addFustage(f);
		return f;
	}

	// Denne metode returnere alle fustage objekter fra storage
	public List<Fustage> getAllFustager() {
		return storage.getAllFustager();
	}

	// ------------------- Kulsyre Metoder ---------------------------

	// Denne metode opretter et kulsyre objekt og tilføjer det til storage
	public Kulsyre opretKulsyre(double stoerrelse, double pris) {
		Kulsyre k = new Kulsyre(stoerrelse, pris);
		storage.addKulsyre(k);
		return k;
	}

	// Denne metode returnere alle kulsyre objekter fra storage
	public List<Kulsyre> getAllKulsyre() {
		return storage.getAllKulsyre();
	}

	// ------------------- Anlægs Metoder ---------------------------

	// Denne metode opretter et anlægs objekt og tilføjer det til storage
	public Anlaeg opretAnlaeg(String type, double pris, double pant) {
		Anlaeg a = new Anlaeg(type, pris, pant);
		storage.addAnlaeg(a);
		return a;
	}

	// Denne metode returnere alle Anlægs objekter fra storage
	public List<Anlaeg> getAllAnleag() {
		return storage.getAllAnlaeg();
	}

	// INITIAL STUFF

	public void initStorage() {

		// Bar
		SalgSted fredagsBar = opretSalgSted("Fredagsbar"); // Slet ikke
		SalgSted butik = opretSalgSted("Butik"); // Slet ikke

		// Rundvisninger
		Rundvisning r1 = opretRundvisning("Madeleine", LocalDate.of(2018, 6, 26), 42, LocalTime.of(13, 37));
		r1.isStuderende();
		r1.tilmeldSpsning(21);
		r1.setBetalt();
		gemRundvisning(r1);

		Rundvisning r2 = opretRundvisning("Mads", LocalDate.of(2018, 5, 4), 20, LocalTime.of(10, 00));
		r2.tilmeldSpisning();
		gemRundvisning(r2);

		Rundvisning r3 = opretRundvisning("Daniel", LocalDate.of(2018, 3, 14), 65, LocalTime.of(9, 30));
		r3.setBetalt();
		gemRundvisning(r3);

		// Fra opgaven
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
		Fustage fu1 = opretFustage("20 Liter", "Klosterbryg", 775.0);
		Fustage fu2 = opretFustage("25 Liter", "Jazz Classic", 625.0);
		Fustage fu3 = opretFustage("25 Liter", "Extra Pilsner", 575.0);
		Fustage fu4 = opretFustage("20 Liter", "Celebration", 775.0);
		Fustage fu5 = opretFustage("25 Liter", "Blondie", 700.0);
		Fustage fu6 = opretFustage("20 Liter", "Foraarsbryg", 775.0);
		Fustage fu7 = opretFustage("20 Liter", "India Pale Ale", 775.0);
		Fustage fu8 = opretFustage("20 Liter", "Julebryg", 775.0);
		Fustage fu9 = opretFustage("20 Liter", "Imperial Stout", 775.0);

		// Glass
		ProduktKategori glas = opretProduktKategori("Glas");
		opretProdukt(glas, "Stort Glas", 15.0, "50cL");
		opretProdukt(glas, "Lille Glas", 15.0, "30cL");
		opretProdukt(glas, "Krus", 60.0, "Krus-stoerrelse");

		// Kulsyre
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

		// Udlejninger
		Udlejning u1 = opretUdlejning();
		u1.setKundeNavn("Madeleine");
		u1.setDato(LocalDate.of(2018, 11, 6));
		u1.setKundeEmail("madeleine.harbom@gmail.com");
		u1.setKundeTlf("60653173");
		u1.setLevering(true);
		gemUdlejning(u1);
		u1.createOrdreLinje(5, fu4);
		u1.createOrdreLinje(7, fu7);

	}

}
