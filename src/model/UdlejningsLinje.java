package model;

public class UdlejningsLinje {

    private Rentable produkt;
    private int antal;

    /**
     * Constructor
     *
     * @param produkt
     * @param antal
     */
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

    /**
     * Beregner en totalpris, hvor man laegger pant og prisen sammen.
     * 
     * @return
     */
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
