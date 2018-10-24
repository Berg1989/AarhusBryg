package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.KlippeKort;
import model.Produkt;
import model.ProduktKategori;
import model.Rundvisning;
import model.SalgSted;
import model.StedPris;
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

	public static Produkt opretProdukt(ProduktKategori pk, String navn, double pris, String str) {
		Produkt p = pk.createProdukt(navn, pris, str);
		return p;
	}

	public static void sletProdukt(ProduktKategori pk, Produkt p) {
		pk.sletProdukt(p);
	}

	public static void RedigerProdukt(ProduktKategori pk, Produkt p, String navn, double pris, String str) {
		pk.redigerNavn(p, navn);
		pk.redigerPris(p, pris);
		pk.redigerStr(p, str);
	}

	public static ProduktKategori opretProduktKategori(String navn) {
		ProduktKategori pk = new ProduktKategori(navn);
		Storage.addProduktKategori(pk);
		return pk;
	}

	public static void sletProduktKategori(ProduktKategori pk) {
		Storage.removeProduktKategori(pk);
	}

	public static KlippeKort opretKlippeKort(String navn, int antalKlip, double pris) {
		KlippeKort kk = new KlippeKort(navn, antalKlip, pris);
		Storage.addKlippeKort(kk);
		return kk;
	}

	public static void sletKlippeKort(KlippeKort kk) {
		Storage.removeKlippeKort(kk);
	}

	public static Rundvisning opretRundvisning(String kunde, LocalDate dato, int antal, LocalTime tid) {
		Rundvisning r = new Rundvisning(kunde, dato, tid, 0);
		Storage.addRundvisning(r);
		return r;
	}

	public static void sletRundvisning(Rundvisning r) {
		Storage.removeRundvisning(r);
	}

	public static void tilfoejKategori(SalgSted ss, ProduktKategori pk) {
		ss.addProduktKategori(pk);
	}

	public static SalgSted opretSalgSted(String navn) {
		SalgSted ss = new SalgSted(navn);
		Storage.addSalgSted(ss);
		return ss;

	}

	public static void sletSalgSted(SalgSted ss) {
		// ProduktKategori pk;
		// Produkt p;
		// for (int i = 0; i < ss.getProduktKategorier().size(); i++) {
		// pk = ss.getProduktKategorier().get(i);
		// for (int j = 0; j < pk.getProdukter().size(); j++) {
		// p = pk.getProdukter().get(j);
		// p.removeStedPris(ss);
		// }
		//
		// }
		// for (ProduktKategori pk : ss.getProduktKategorier()) {
		// for (Produkt p : pk.getProdukter()) {
		// p.removeStedPris(ss);
		// }
		// }
		Storage.removeSalgSted(ss);
	}

	public static StedPris opretStedPris(SalgSted ss, Produkt p, double pris) {
		StedPris sp = new StedPris(ss, p, pris);
		p.addStedPris(sp);
		return sp;
	}

	public static double getStedPris(SalgSted ss, Produkt p) {
		return p.getStedPrisPris(ss);
	}

	public static ArrayList<ProduktKategori> getMuligeKategorier(SalgSted ss) {
		ArrayList<ProduktKategori> pkList = Storage.getAllProduktKategorier();
		ProduktKategori pk;
		for (int i = 0; i < ss.getProduktKategorier().size(); i++) {
			pk = ss.getProduktKategorier().get(i);
			for (int j = 0; j < Storage.getAllProduktKategorier().size(); j++) {
				if (pkList.contains(pk)) {
					pkList.remove(pk);
				}
			}
		}
		return pkList;
	}

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

		// Klippekort
		opretKlippeKort("Kort1", 4, 100);

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

		// System.out.println(p1.toStringSted(fredagsBar));
		// System.out.println(p1.toStringSted(butik));

	}

}
