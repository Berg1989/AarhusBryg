package storage;

import java.util.ArrayList;

import model.Produkt;
import model.ProduktKategori;
import model.Rundvisning;
import model.SalgSted;

public class Storage {
	private static ArrayList<ProduktKategori> produktKategoriList;
	private static ArrayList<Rundvisning> rundvisningList;
	private static ArrayList<SalgSted> salgStedList;

	public Storage() {
		produktKategoriList = new ArrayList<>();
		rundvisningList = new ArrayList<>();
		salgStedList = new ArrayList<>();
	}

	public static ArrayList<ProduktKategori> getAllProduktKategorier() {
		return new ArrayList<>(produktKategoriList);
	}

	public static void addProduktKategori(ProduktKategori pk) {
		produktKategoriList.add(pk);
	}

	public static void removeProduktKategori(ProduktKategori pk) {
		produktKategoriList.remove(pk);
	}

	public static void addProdukt(ProduktKategori pk, Produkt p) {
		pk.addProdukt(p);
	}

	public static ArrayList<Rundvisning> getAllRundvisninger() {
		return new ArrayList<>(rundvisningList);
	}

	public static void addRundvisning(Rundvisning r) {
		rundvisningList.add(r);
	}

	public static void removeRundvisning(Rundvisning r) {
		rundvisningList.remove(r);
	}

	public static void addSalgSted(SalgSted b) {
		salgStedList.add(b);
	}

	public static ArrayList<SalgSted> getAllSalgSted() {
		return new ArrayList<>(salgStedList);
	}

	public static void removeSalgSted(SalgSted ss) {
		salgStedList.remove(ss);
	}

}
