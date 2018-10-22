package model;

import java.util.ArrayList;

public class SalgSted {
    private String stedNavn;
    private ArrayList<ProduktKategori> pkList = new ArrayList<>();

    public SalgSted(String barNavn) {
        setBarNavn(barNavn);

    }

    public String getBarNavn() {
        return stedNavn;
    }

    public void setBarNavn(String barNavn) {
        this.stedNavn = barNavn;
    }

    public ArrayList<ProduktKategori> getProduktKategorier() {
        return new ArrayList<>(pkList);
    }

    public void addProduktKategori(ProduktKategori pk) {
        pkList.add(pk);
    }

    public void removeProduktKategori(ProduktKategori pk) {
        pkList.remove(pk);
    }

    @Override
    public String toString() {
        return stedNavn;
    }

}
