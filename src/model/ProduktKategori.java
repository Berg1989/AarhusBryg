package model;

import java.util.ArrayList;

public class ProduktKategori {

    private String navn;
    private ArrayList<Produkt> produkter = new ArrayList<>();

    /**
     * Constructor
     *
     * @param navn
     */
    public ProduktKategori(String navn) {
        this.navn = navn;
    }

    /**
     * Opretter et produkt og tilføjer det til arraylisten af produkter
     *
     * @param navn
     * @param pris
     * @param str
     * @return et produkt
     */
    public Produkt createProdukt(String navn, double pris, String str) {
        Produkt p = new Produkt(navn, pris, str);
        produkter.add(p);
        return p;
    }

    /**
     * Tilføjer et produkt til arraylisten af produkter.
     *
     * @param p
     */
    public void addProdukt(Produkt p) {
        produkter.add(p);
    }

    /**
     * Sletter produktet i parameteren fra arraylisten af produkter.
     *
     * @param p
	public void removeProdukt(Produkt p) {
		produkter.remove(p);
	}

    /**
     * Rediger på produkktets navn.
     *
     * @param p
     * @param navn
     */
    public void redigerNavn(Produkt p, String navn) {
        p.setNavn(navn);
    }

    /**
     * Rediger på produktets pris
     *
     * @param p
     * @param pris
     */
    public void redigerPris(Produkt p, double pris) {
        p.setPris(pris);
    }

    /**
     * Rediger på produktets str
     *
     * @param p
     * @param str
     */
    public void redigerStr(Produkt p, String str) {
        p.setStr(str);
    }

    /**
     *
     * @return en ny arrayList af produkter
     */
    public ArrayList<Produkt> getProdukter() {
        return new ArrayList<>(produkter);
    }

    /**
     *
     * @param p
     * @return det produkt som er i parameteren
     */
    public Produkt getProdukt(Produkt p) {
        return p;
    }

    @Override
    public String toString() {
        return navn;
    }

}
