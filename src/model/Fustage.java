package model;

public class Fustage implements Rentable {

    private String stoerrelse;
    private String oeltype;
    private static double pant = 200.0;
    private double pris;

    /**
     * Constructor
     *
     * @param stoerrelse
     * @param oeltype
     * @param pris
     */
    public Fustage(String stoerrelse, String oeltype, double pris) {
        this.stoerrelse = stoerrelse;
        this.oeltype = oeltype;
        this.pris = pris;
    }

    public void setStoerrelse(String stoerrelse) {
        this.stoerrelse = stoerrelse;
    }

    public void setOeltype(String oeltype) {
        this.oeltype = oeltype;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getStoerrelse() {
        return this.stoerrelse;
    }

    public String getOeltype() {
        return this.oeltype;
    }

    @Override
    public double getPant() {
        return pant;
    }

    @Override
    public double getPris() {
        return this.pris;
    }

    @Override
    public double getTotal() {
        return pant + this.pris;
    }

    @Override
    public String toString() {
        return this.stoerrelse + " " + this.oeltype + " (" + this.pris + ")";
    }

}
