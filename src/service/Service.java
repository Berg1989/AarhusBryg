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
	public static void opretProdukt(ProduktKategori pk, String navn, double pris, String str) {
		Produkt p = new Produkt(navn, pris, str);
		Storage.addProdukt(pk, p);
	}

}
