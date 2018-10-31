package storage;

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
import model.Udlejning;

public class Storage {
	private List<ProduktKategori> produktKategoriList;
	private List<Rundvisning> rundvisningList;
	private List<SalgSted> salgStedList;
	private List<Salg> salgListe;
	private List<Udlejning> udlejninger;
	private List<Fustage> fustager;
	private List<Kulsyre> kulsyre;
	private List<Anlaeg> anlaeg;

	public Storage() {
		produktKategoriList = new ArrayList<>();
		rundvisningList = new ArrayList<>();
		salgStedList = new ArrayList<>();
		salgListe = new ArrayList<>();
		udlejninger = new ArrayList<>();
		fustager = new ArrayList<>();
		kulsyre = new ArrayList<>();
		anlaeg = new ArrayList<>();
	}

	public ArrayList<Salg> getAllSalg() {
		return new ArrayList<>(salgListe);
	}

	public void addSalg(Salg salg) {
		salgListe.add(salg);
	}

	public void removeSalg(Salg salg) {
		salgListe.remove(salg);
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
	
	public void addUdlejning(Udlejning u) {
		udlejninger.add(u);
	}
	
	public void removeUdlejning(Udlejning u) {
		udlejninger.remove(u);
	}
	
	public ArrayList<Udlejning> getAllUdlejninger() {
		return new ArrayList<>(udlejninger);
	}
	
	public void addFustage(Fustage f) {
		fustager.add(f);
	}
	
	public void removeFustage(Fustage f) {
		fustager.remove(f);
	}
	
	public ArrayList<Fustage> getAllFustager() {
		return new ArrayList<>(fustager);
	}
	
	public void addKulsyre(Kulsyre k) {
		kulsyre.add(k);
	}
	
	public void removeKuilsyre(Kulsyre k) {
		kulsyre.remove(k);
	}
	
	public ArrayList<Kulsyre> getAllKulsyre() {
		return new ArrayList<Kulsyre>(kulsyre);
	}
	
	public void addAnlaeg(Anlaeg a) {
		anlaeg.add(a);
	}

	public void removeAnlaeg(Anlaeg a) {
		anlaeg.remove(a);
	}
	
	public ArrayList<Anlaeg> getAllAnlaeg() {
		return new ArrayList<>(anlaeg);
	}
}
