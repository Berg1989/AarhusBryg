package service;

import model.Produkt;
import model.ProduktKategori;
import storage.Storage;

public class Service {
    private static Service service;
    private Storage storage;

    private Service() {
        storage = new Storage();
    }

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
    public ProduktKategori opretProduktKategori(String navn) {
        ProduktKategori pk = new ProduktKategori(navn);
        Storage.addProduktKategori(pk);
        return pk;
    }

    public Produkt opretProdukt(ProduktKategori pk, String navn, double pris, String str) {
        Produkt p = new Produkt(navn, pris, str);
        Storage.addProdukt(pk, p);
        return p;
    }

    // -----------------------------------------------------------------------------------------

    public void initStorage() {

        // Produk Kategorier
        ProduktKategori pk1 = opretProduktKategori("Kategori1");
        ProduktKategori pk2 = opretProduktKategori("Kategori2");
        ProduktKategori pk3 = opretProduktKategori("Kategori3");

        // Produkter
        opretProdukt(pk1, "Øl", 35, "0.33 L");
        opretProdukt(pk1, "Tis", 22, "0.20 CL");
        opretProdukt(pk2, "Vodka", 120, "1 L");
        opretProdukt(pk3, "Single Malt Whisky", 350, "0.66 L");
        opretProdukt(pk3, "Saftevand", 10, "1.5 L");
    }

}
