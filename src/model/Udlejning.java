package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udlejning {

    private String kundeNavn;
    private String kundeTlf;
    private String kundeEmail;
    private ArrayList<UdlejningsLinje> ordre;
    private LocalDate dato;
    private boolean levering;
    private static double leveringspris = 800.0;
    private ArrayList<Kulsyre> kulsyre;
    private boolean betalt;
    private double betaltBeloeb;
    private double pantLagt;

    /**
     * Constuctor
     */
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

    /**
     * Kører alle UdlejningsLinje igennem og laegger alle objekternes pant til den
     * lokale variabel
     *
     * @return den lokale variabel
     */
    public double getPant() {
        double p = 0.0;
        for (UdlejningsLinje ul : this.ordre) {
            p += ul.getPant();
        }
        return p;
    }

    /**
     * Koerer alle UdlejningsLinje igennem og laegger alle objekternes pris til den
     * lokale variabel
     *
     * @return den lokale variabel
     */
    public double getSamletPris() {
        double p = 0.0;
        for (UdlejningsLinje ul : this.ordre) {
            p += ul.getPris();
        }
        return p;
    }

    /**
     * Koerer alle UdlejningsLinje igennem og laegger alle objekternes pant og pris
     * til den lokale variabel, derudover laegger den leveringspris paa ogsaa
     *
     * @return den lokale variabel
     */
    public double getSamletPrisMedPant() {
        double p = 0.0;
        for (UdlejningsLinje ul : this.ordre) {
            p += ul.getPris();
            p += ul.getPant();
        }
        p += leveringspris;
        return p;
    }

    /**
     * Saetter betalt til true og bruger beloeb parameter som det der skal betales
     *
     * @param beloeb
     */
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

    /**
     * Opretter en Udlejningslinje, derefter tilfoejer den til arraylisten ordre
     * 
     * @param antal
     * @param p
     */
    public void createOrdreLinje(int antal, Rentable p) {
        UdlejningsLinje ul = new UdlejningsLinje(p, antal);
        this.ordre.add(ul);

    }

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
