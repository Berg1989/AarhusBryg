package model;

public class SalgsLinie {

    // Attributer til klassen
    private int antal;
    private Produkt produkt;
    private double produktpris;

    // Constructoren til klassen
    public SalgsLinie(int antal, Produkt produkt, SalgSted sted) {
        this.antal = antal;
        this.produkt = produkt;
        if (produkt.getStedPrisPris(sted) == 0) {
            this.produktpris = produkt.getPris();
        } else {
            this.produktpris = produkt.getStedPrisPris(sted);
        }
    }

    // denne metode returnere hvad der står i pris attributen
    public double getPris() {
        double pris = this.antal * this.produktpris;
        return pris;
    }

    // denne metode returnere hvad der står i produktpris attributen
    public double getProduktPris() {
        return this.produktpris;
    }

    @Override
    public String toString() {
        return "" + this.antal + "x " + this.produkt.getNavn() + " (" + this.produktpris + ") " + getPris();
    }

}
