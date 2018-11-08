package model;

public class Kulsyre implements Rentable {
    private double stoerrelseKG;
    private double pris;
    private static double pant = 1000.0;

    public Kulsyre(double stoerrelseKG, double pris) {
        this.stoerrelseKG = stoerrelseKG;
        this.pris = pris;

    }

    // denne metode gør det muligt at sætte størrelsen, hvis størrelsen ikke
    // allerede
    // eksistere, hvis den gør, så overrider man den tidligere størrelse
    public void setStoerrelse(double kg) {
        this.stoerrelseKG = kg;
    }

    // denne metode gør det muligt at sætte pris, hvis pris ikke allerede
    // eksistere, hvis den gør, så overrider man den tidligere pris
    public void setPris(double pris) {
        this.pris = pris;
    }

    // denne metode returnere hvad der står i attributen "Pris"
    @Override
    public double getPris() {
        return this.pris;
    }

    // denne metode returnere hvad der står i attributen "Pant"
    @Override
    public double getPant() {
        return pant;
    }

    // denne metode returnere hvad prisen for kulstyre inklusivt pant er.
    @Override
    public double getTotal() {
        return pant + this.pris;
    }

    // denne metode returnere hvad der står i attributen "stoerrelseKG"
    public double getStoerrelse() {
        return this.stoerrelseKG;
    }

    @Override
    public String toString() {
        return this.stoerrelseKG + " kg, " + this.pris + " kr";
    }

}
