package model;

import java.util.ArrayList;

public class ProduktKategori {

	// Attributter til klassen
	private String navn;
	private ArrayList<Produkt> produkter = new ArrayList<>();

	// Constructoren til klassen
	public ProduktKategori(String navn) {
		this.navn = navn;
	}

	// denne metode opretter et produkt og tilføjer det oprettet produkt til
	// arraylisten af produkter.
	public Produkt createProdukt(String navn, double pris, String str) {
		Produkt p = new Produkt(navn, pris, str);
		produkter.add(p);
		return p;
	}

	// Denne metode tilføjer et produkt til arraylisten af produkter
	public void addProdukt(Produkt p) {
		produkter.add(p);
	}

	// denne metode fjerner produktet som er skrevet i parameteren fra arraylisten
	// af produkter.
	public void sletProdukt(Produkt p) {
		produkter.remove(p);
	}

	// Denne metode redigere attributten "navn" på det produkt som er i parameteren.
	public void redigerNavn(Produkt p, String navn) {
		p.setNavn(navn);
	}

	// denne metode redigere attributten "pris" på det produkt som er i parameteren.
	public void redigerPris(Produkt p, double pris) {
		p.setPris(pris);
	}

	// denne metode redigere attributten "str" på det produkt som er i parameteren.
	public void redigerStr(Produkt p, String str) {
		p.setStr(str);
	}

	// denne metode returnere en ny arraylist med alle produkter.
	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	// denne metode returnere det produkt, som er i parameteren.
	public Produkt getProdukt(Produkt p) {
		return p;
	}

	@Override
	public String toString() {
		return navn;
	}

}
