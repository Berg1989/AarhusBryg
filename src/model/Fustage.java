package model;

public class Fustage implements Rentable {

    // Attributter til klassen
    private String stoerrelse;
    private String oeltype;
    private static double pant = 200.0;
    private double pris;

    // Constructoren til klassen
    public Fustage(String stoerrelse, String oeltype, double pris) {
        this.stoerrelse = stoerrelse;
        this.oeltype = oeltype;
        this.pris = pris;
    }

    // denne metode gør det muligt at sætte størrelsen, hvis størrelsen ikke
    // allerede
    // eksistere, hvis den gør, så overrider man den tidligere størrelse
    public void setStoerrelse(String stoerrelse) {
        this.stoerrelse = stoerrelse;
    }

    // denne metode gør det muligt at sætte Oeltype, hvis Oeltype ikke allerede
    // eksistere, hvis den gør, så overrider man den tidligere Oeltype
    public void setOeltype(String oeltype) {
        this.oeltype = oeltype;
    }

    // denne metode gør det muligt at sætte pris, hvis pris ikke allerede
    // eksistere, hvis den gør, så overrider man den tidligere pris
    public void setPris(double pris) {
        this.pris = pris;
    }

    // denne metode returnere hvad der står i attributen "stoerrelse"
    public String getStoerrelse() {
        return this.stoerrelse;
    }

    // denne metode returnere hvad der står i attributen "Oeltype"
    public String getOeltype() {
        return this.oeltype;
    }

    // denne metode returnere hvad der står i attributen "Pant"
    @Override
    public double getPant() {
        return pant;
    }

    // denne metode returnere hvad der står i attributen "pris"
    @Override
    public double getPris() {
        return this.pris;
    }

    // denne metode returnere prisen for en fustage, hvor panten er inklusivt.
    @Override
    public double getTotal() {
        return pant + this.pris;
    }

    @Override
    public String toString() {
        return this.stoerrelse + " " + this.oeltype + " (" + this.pris + ")";
    }

}
