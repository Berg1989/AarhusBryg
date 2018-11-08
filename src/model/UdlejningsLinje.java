package model;

public class UdlejningsLinje {

    // Attributter til klassen
    private Rentable produkt;
    private int antal;

    // Constructor til klassen
    public UdlejningsLinje(Rentable produkt, int antal) {
        this.produkt = produkt;
        this.antal = antal;
    }

    public double getPant() {
        return produkt.getPant() * antal;
    }

    public double getPris() {
        return produkt.getPris() * antal;
    }

    // denne metode beregner en total pris
    public double getTotalPris() {
        return getPant() + getPris();
    }

    @Override
    public String toString() {
        return this.antal + " stk " + produkt.toString();
    }

    public Rentable getProdukt() {
        return this.produkt;
    }

}
