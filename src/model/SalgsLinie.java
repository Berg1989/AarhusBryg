package model;

public class SalgsLinie {

    private int antal;
    private Produkt produkt;
    private double produktpris;

    /**
     * Constructoren
     *
     * @param antal
     * @param produkt
     * @param sted
     */
    public SalgsLinie(int antal, Produkt produkt, SalgSted sted) {
        this.antal = antal;
        this.produkt = produkt;
        if (produkt.getStedPrisPris(sted) == 0) {
            this.produktpris = produkt.getPris();
        } else {
            this.produktpris = produkt.getStedPrisPris(sted);
        }
    }

    public double getPris() {
        double pris = this.antal * this.produktpris;
        return pris;
    }

    public double getProduktPris() {
        return this.produktpris;
    }

    @Override
    public String toString() {
        return "" + this.antal + "x " + this.produkt.getNavn() + " (" + this.produktpris + ") " + getPris();
    }

}
