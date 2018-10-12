package storage;

import java.util.ArrayList;

import model.KlippeKort;
import model.Produkt;
import model.ProduktKategori;
import model.Rundvisning;
import model.SalgSted;

public class Storage {
	private static ArrayList<ProduktKategori> produktKategoriList = new ArrayList<>();
	private static ArrayList<KlippeKort> klippeKortList = new ArrayList<>();
	private static ArrayList<Rundvisning> rundvisningList = new ArrayList<>();
	private static ArrayList<SalgSted> salgStedList = new ArrayList<>();

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

	public static ArrayList<KlippeKort> getAllKlippeKort() {
		return new ArrayList<>(klippeKortList);
	}

	public static void addKlippeKort(KlippeKort kk) {
		klippeKortList.add(kk);
	}

	public static void removeKlippeKort(KlippeKort kk) {
		klippeKortList.remove(kk);
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

}
