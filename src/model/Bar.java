package model;

import java.util.ArrayList;

public class Bar {
    private String barNavn;
    private ArrayList<ProduktKategori> pk = new ArrayList<>();

    public Bar(String barNavn) {
        setBarNavn(barNavn);

    }

    public String getBarNavn() {
        return barNavn;
    }

    public void setBarNavn(String barNavn) {
        this.barNavn = barNavn;
    }

    public ArrayList<ProduktKategori> getProduktKategori() {
        return new ArrayList<>(pk);
    }

    @Override
    public String toString() {
        return barNavn + pk;
    }

}
