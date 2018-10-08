package service;

import model.Produkt;
import model.ProduktKategori;
import storage.Storage;

public class Service {
	private static Service service;

	public static Service getService() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	public static Service getTestService() {
		return new Service();
	}

	// ProduktKategori
	public static ProduktKategori opretProduktKategori(String navn) {
		ProduktKategori pk = new ProduktKategori(navn);
		return pk;
	}

	public static Produkt opretProdukt(ProduktKategori pk, String navn, double pris, String str) {
		Produkt p = new Produkt(navn, pris, str);
		Storage.addProdukt(pk, p);
		return p;
	}

	// -----------------------------------------------------------------------------------------

	public static void initStorage() {
		ProduktKategori pk1 = Service.opretProduktKategori("Kategori4");
		ProduktKategori pk2 = Service.opretProduktKategori("Kategori5");
		ProduktKategori pk3 = Service.opretProduktKategori("Kategori6");
		Service.opretProdukt(pk1, "Øl", 35, "0.33 L");
		Service.opretProdukt(pk1, "Tis", 22, "0.20 CL");
		Service.opretProdukt(pk2, "Vodka", 120, "1 L");
		Service.opretProdukt(pk3, "Single Malt Whisky", 350, "0.66 L");
		Service.opretProdukt(pk3, "Saftevand", 10, "1.5 L");
	}

}
