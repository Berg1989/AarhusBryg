package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning {

    // attributter til klassen
    private String kundeNavn;
    private LocalDate dato;
    private LocalTime tid;
    private int antalGaester;
    private double pris;
    private boolean betalt;
    private String rundviser;
    private boolean spisning;
    private int antalSpisende;
    private boolean studerende;

    /**
     * Constructor
     *
     * @param kunde
     * @param dato
     * @param tid
     * @param gaester
     */
    public Rundvisning(String kunde, LocalDate dato, LocalTime tid, int gaester) {
        this.kundeNavn = kunde;
        this.dato = dato;
        this.tid = tid;
        this.antalGaester = gaester;
        this.betalt = false;
        this.spisning = false;
        this.antalSpisende = 0;
        this.studerende = false;
    }

    public void setKunde(String s) {
        this.kundeNavn = s;
    }

    public void setDato(LocalDate d) {
        this.dato = d;
    }

    public void setTid(LocalTime t) {
        this.tid = t;
    }

    public void setAntalGaester(int a) {
        this.antalGaester = a;
    }

    public void setPris(double p) {
        this.pris = p;
    }

    /**
     * Denne metode aendrer betalt til true
     */
    public void setBetalt() {
        this.betalt = true;
    }

    public void setRundviser(String rv) {
        this.rundviser = rv;
    }

    /**
     * Denne metode aendrer spisning til true og saetter antalspisende til det samme
     * antal som er til rundvisning
     */
    public void tilmeldSpisning() {
        this.spisning = true;
        this.antalSpisende = this.antalGaester;
    }

    public void setStuderende(boolean b) {
        this.studerende = b;
    }

    /**
     * Denne metode aendrer spisning til true og saetter antalspisende til det i
     * parameteren
     *
     * @param spisende
     */
    public void tilmeldSpsning(int spisende) {
        this.spisning = true;
        this.antalSpisende = spisende;
    }

    /**
     * Denne metode aendrer spisning til false;
     */
    public void frameldSpisning() {
        this.spisning = false;
    }

    /**
     * Denne metode aendrer antal spisende det som i parameteren
     *
     * @param as
     */
    public void setAntalSpisende(int as) {
        this.antalSpisende = as;
    }

    public String getKunde() {
        return this.kundeNavn;
    }

    public LocalDate getDato() {
        return this.dato;
    }

    public LocalTime getTid() {
        return this.tid;
    }

    public double getPris() {
        return this.pris;
    }

    /**
     * Denne metode ser om der er blevet betalt eller ej.
     *
     * @return
     */
    public boolean isBetalt() {
        return this.betalt;
    }

    public String getRundviser() {
        return this.rundviser;
    }

    public int getAntalGaester() {
        return this.antalGaester;
    }

    /**
     * Denne metode ser om der er spisning til
     *
     * @return
     */
    public boolean hasSpisning() {
        return this.spisning;
    }

    public int antalSpisende() {
        return this.antalSpisende;
    }

    /**
     * Denne metode ser om det er studerende
     *
     * @return
     */
    public boolean isStuderende() {
        return this.studerende;
    }

    /**
     * Tjekker om dato'en for rundvisning er indenfor de gyldige dage og tidspunkt
     * for rundvisning.
     *
     * @return true, hvis dato og tidspunkt er indenfor de acceptable dato'er og
     *         tidsrum. Ellers return false
     */

    public boolean isTilgaengeligTid() {

        LocalTime slut = slutTid();

        if (this.dato.getDayOfWeek().toString().equals("SATURDAY")
                || this.dato.getDayOfWeek().toString().equals("SUNDAY")) { // Make Pretty
            return false;
        }

        else if (this.dato.getDayOfWeek().toString().equals("FRIDAY") && slut.isAfter(LocalTime.of(15, 00))) { // Make
                                                                                                               // Pretty
            return false;
        }
        return true;
    }

    /**
     * Tjekker om tidsrummet er efter eller faarkl 16:00 og hvis antalGaester er
     * indenfor accaptable graenser Require: (Efter 16:00) AntalGaester < 20 || > 75
     * eller (Faar 16:00) antalGaester < 15 || > 75
     *
     * @return true hvis antalGaester er inden for accaptable graenser. Ellers return
     *         false
     */
    public boolean isTilgaengeligtAntalGaester() {
        if (slutTid().isAfter(LocalTime.of(16, 00))) {
            if (this.antalGaester < 20 || this.antalGaester > 75) {
                return false;
            } else {
                return true;
            }
        } else {
            if (this.antalGaester < 15 || this.antalGaester > 75) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Saetter tiden for rundvisning og eventuelt spisning.
     *
     * @return hvor lang tid en rundvisning og eventuelt spisning vil tage i
     *         minutter
     */
    public LocalTime slutTid() {
        int tid = 90;
        if (this.spisning) {
            tid += 30;
        }
        LocalTime slut = this.tid.plusMinutes(tid);
        return slut;
    }

    /**
     * Beregner prisen for en rundvisning. Hvor prisen er differenseret angaaende
     * antalGaester og hvis spisning er taget med
     *
     * @return en pris
     */
    public double beregnPris() {
        double pris = 0.0;
        if (isTilgaengeligTid() && isTilgaengeligtAntalGaester()) {
            if (slutTid().isAfter(LocalTime.of(16, 00))) {
                pris += this.antalGaester * 120;
            } else {
                pris += this.antalGaester * 100;
            }
            if (this.spisning == true) {
                pris += this.antalSpisende * 130;
            }
        }
        return pris;
    }

    @Override
    public String toString() {
        return "" + getDato().toString() + "kl " + this.tid.toString() + "(" + this.kundeNavn + ")";
    }

}
