package storage;

import java.util.ArrayList;

import model.Produkt;
import model.ProduktKategori;

public class Storage {
	private static ArrayList<ProduktKategori> produktKategoriList = new ArrayList<>();

	public static ArrayList<ProduktKategori> getAllProduktKategorier() {
		return new ArrayList<>(produktKategoriList);
	}

	public static void addProduktKategori(ProduktKategori pk) {
		produktKategoriList.add(pk);
	}

	public static void addProdukt(ProduktKategori pk, Produkt p) {
		pk.addProdukt(p);
	}

}
