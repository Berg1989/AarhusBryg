package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udlejning {

    // Attributter til klassen
    private String kundeNavn;
    private String kundeTlf;
    private String kundeEmail;
    private ArrayList<UdlejningsLinje> ordre;
    private LocalDate dato;
    private boolean levering;
    private static double leveringspris = 800.0; // FACT CHECK
    private ArrayList<Kulsyre> kulsyre; // Why?
    private boolean betalt;
    private double betaltBeloeb;
    private double pantLagt;

    // Constuctor til klassen
    public Udlejning() {
        this.ordre = new ArrayList<>();
        this.betalt = false;
        this.betaltBeloeb = 0.0;
    }

    public void setKundeNavn(String navn) {
        this.kundeNavn = navn;
    }

    public String getKundeNavn() {
        return this.kundeNavn;
    }

    public void setDato(LocalDate d) {
        this.dato = d;
    }

    public LocalDate getDato() {
        return this.dato;
    }

    public void setKundeEmail(String email) {
        this.kundeEmail = email;
    }

    public String getKundeEmail() {
        return this.kundeEmail;
    }

    public void setKundeTlf(String tlf) {
        this.kundeTlf = tlf;
    }

    public String getKundeTlf() {
        return this.kundeTlf;
    }

    public double getLeveringspris() {
        return leveringspris;
    }

    public void setLeveringspris(double pris) {
        leveringspris = pris;
    }

    // Denne metode beregner total pant for alle objekter i ordre arraylisten.
    public double getPant() {
        double p = 0.0;
        for (UdlejningsLinje ul : this.ordre) {
            p += ul.getPant();
        }
        return p;
    }

    // Denne metode beregner den samlede pris for alle objekter i ordre arraylisten
    public double getSamletPris() {
        double p = 0.0;
        for (UdlejningsLinje ul : this.ordre) {
            p += ul.getPris();
        }
        return p;
    }

    // Denne metode beregner det totale beløb, hvor pant og prisen bliver
    // sammenlagt. Derudover bliver der lagt leveringspris oveni
    public double getSamletPrisMedPant() {
        double p = 0.0;
        for (UdlejningsLinje ul : this.ordre) {
            p += ul.getPris();
            p += ul.getPant();
        }
        p += leveringspris;
        return p;
    }

    // Denne metode sætter SADWAWDAWDAWDAWDAWDAW
    ///
    ////
    public void betal(double beloeb) {
        this.betalt = true;
        this.betaltBeloeb = beloeb;
    }

    public double getBetaltPris() {
        return this.betaltBeloeb;
    }

    public void setLevering(boolean b) {
        this.levering = b;
    }

    public boolean getLevering() {
        return this.levering;
    }

    // Denne metode oprreter en udlejningslinie, hvorefter den tilføjer den til
    // ordre arraylisten
    public void createOrdreLinje(int antal, Rentable p) {
        UdlejningsLinje ul = new UdlejningsLinje(p, antal);
        this.ordre.add(ul);

    }

    // tror denne er udeless
    public void addOrdre(UdlejningsLinje ul) {
        this.ordre.add(ul);
    }

    public void removeOrdre(UdlejningsLinje ul) {
        this.ordre.remove(ul);
    }

    public ArrayList<UdlejningsLinje> getFullOrdre() {
        return new ArrayList<>(this.ordre);
    }

    @Override
    public String toString() {
        return this.dato.toString() + " (" + getKundeNavn() + ")";
    }

}
