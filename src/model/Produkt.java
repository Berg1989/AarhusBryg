package model;

import java.util.ArrayList;

public class Produkt {

    private String navn;
    private double pris;
    private String str;
    private ArrayList<StedPris> stedPriser;

    public Produkt(String navn, double pris, String str) {
        this.navn = navn;
        this.pris = pris;
        this.str = str;
        stedPriser = new ArrayList<>();
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void addStedPris(StedPris sp) {
        stedPriser.add(sp);
    }

    public ArrayList<StedPris> getStedPriser() {
        return new ArrayList<>(stedPriser);
    }

    public double getStedPrisPris(SalgSted sted) {
        double stedPris = 0;
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                stedPris = stedPriser.get(i).getPris();
            }
        }
        return stedPris;
    }

    public StedPris stedPris(SalgSted sted) {
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                return stedPriser.get(i);
            }
        }
        return null;

    }

    public String toStringSted(SalgSted sted) {
        double stedPris = 0;
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                stedPris = stedPriser.get(i).getPris();
            }
        }
        return navn + ", " + str + ", " + Double.toString(stedPris) + "kr.";
    }

    @Override
    public String toString() {
        return navn + ", " + str + ", " + pris + "kr.";
    }

}
