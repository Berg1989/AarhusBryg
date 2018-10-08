package model;

import java.util.ArrayList;

public class ProduktKategori {
	private String navn;
	private ArrayList<Produkt> produkter;

	public ProduktKategori(String navn) {
		this.navn = navn;
	}

	public void addProdukt(Produkt p) {
		produkter.add(p);
	}

	public void redigerNavn(Produkt p, String navn) {
		p.setNavn(navn);
	}

	public void redigerPris(Produkt p, double pris) {
		p.setPris(pris);
	}

	public void redigerStr(Produkt p, String str) {
		p.setStr(str);
	}

	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	public Produkt getProdukt(Produkt p) {
		return p;
	}

}
