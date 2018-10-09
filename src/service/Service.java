package service;

import model.KlippeKort;
import model.Produkt;
import model.ProduktKategori;
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

	public ProduktKategori opretProduktKategori(String navn) {
		ProduktKategori pk = new ProduktKategori(navn);
		Storage.addProduktKategori(pk);
		return pk;
	}

	public KlippeKort opretKlippeKort(String navn, int antalKlip, double pris) {
		KlippeKort kk = new KlippeKort(navn, antalKlip, pris);
		return kk;
	}

	public void initStorage() {

		// Produk Kategorier
		ProduktKategori pk1 = opretProduktKategori("Kategori1");
		ProduktKategori pk2 = opretProduktKategori("Kategori2");
		ProduktKategori pk3 = opretProduktKategori("Kategori3");

		// Produkter
		opretProdukt(pk1, "Øl", 35, "0.33 L");
		opretProdukt(pk1, "Tis", 22, "0.20 CL");
		opretProdukt(pk2, "Vodka", 120, "1 L");
		opretProdukt(pk3, "Single Malt Whisky", 350, "0.66 L");
		opretProdukt(pk3, "Saftevand", 10, "1.5 L");
	}

}
