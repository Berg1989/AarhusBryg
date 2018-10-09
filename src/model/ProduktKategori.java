package model;

import java.util.ArrayList;

public class ProduktKategori {
    private String navn;
    private ArrayList<Produkt> produkter = new ArrayList<>();

    public ProduktKategori(String navn) {
        this.navn = navn;
    }

    public Produkt createProdukt(String navn, double pris, String str) {
        Produkt p = new Produkt(navn, pris, str);
        produkter.add(p);
        return p;
    }

    public void addProdukt(Produkt p) {
        produkter.add(p);
    }

    public void sletProdukt(Produkt p) {
        produkter.remove(p);
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

    @Override
    public String toString() {
        return navn;
    }

}
