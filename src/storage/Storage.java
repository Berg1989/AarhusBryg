package storage;

import java.util.ArrayList;
import java.util.List;

import model.Produkt;
import model.ProduktKategori;
import model.Rundvisning;
import model.SalgSted;

public class Storage {
	private List<ProduktKategori> produktKategoriList;
	private List<Rundvisning> rundvisningList;
	private List<SalgSted> salgStedList;

	public Storage() {
		produktKategoriList = new ArrayList<>();
		rundvisningList = new ArrayList<>();
		salgStedList = new ArrayList<>();
	}

	public ArrayList<ProduktKategori> getAllProduktKategorier() {
		return new ArrayList<>(produktKategoriList);
	}

	public void addProduktKategori(ProduktKategori pk) {
		produktKategoriList.add(pk);
	}

	public void removeProduktKategori(ProduktKategori pk) {
		produktKategoriList.remove(pk);
	}

	public void addProdukt(ProduktKategori pk, Produkt p) {
		pk.addProdukt(p);
	}

	public ArrayList<Rundvisning> getAllRundvisninger() {
		return new ArrayList<>(rundvisningList);
	}

	public void addRundvisning(Rundvisning r) {
		rundvisningList.add(r);
	}

	public void removeRundvisning(Rundvisning r) {
		rundvisningList.remove(r);
	}

	public void addSalgSted(SalgSted b) {
		salgStedList.add(b);
	}

	public ArrayList<SalgSted> getAllSalgSted() {
		return new ArrayList<>(salgStedList);
	}

	public void removeSalgSted(SalgSted ss) {
		salgStedList.remove(ss);
	}

}
