package model;

public class StedPris {
    private SalgSted sted;
    private Produkt produkt;
    private double pris;

    public StedPris(SalgSted sted, Produkt produkt, double pris) {
        this.sted = sted;
        this.produkt = produkt;
        this.pris = pris;
    }

    public SalgSted getSted() {
        return sted;
    }

    public void setSted(SalgSted sted) {
        this.sted = sted;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return getSted() + ", " + pris + "kr.";
    }

}
