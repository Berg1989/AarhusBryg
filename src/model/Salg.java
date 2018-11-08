package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Salg {

    private SalgSted sted;
    private ArrayList<SalgsLinie> produkter;
    private Betalingsmetode betalingmetode;
    private LocalDate dato;
    private LocalTime tid;
    private double pris;
    private double rabat;
    private static int counter = 0;
    private int id;
    private boolean studerende;
    private static double studierabat = 20.0;

    /**
     * Constructor
     *
     * @param sted
     */
    public Salg(SalgSted sted) {
        this.sted = sted;
        produkter = new ArrayList<>();
        counter++;
        id = counter;
        this.studerende = false;

    }

    /**
     * Denne metode opretter en salgslinie og tilføjer den til arraylisten
     * "produkter", hvis antal er større end 0.
     *
     * @param antal
     * @param produkt
     */

    public void opretSalgslinie(int antal, Produkt produkt) {
        if (antal > 0) {
            SalgsLinie linie = new SalgsLinie(antal, produkt, this.sted);
            produkter.add(linie);
        }
    }

    public void sletSalgsLinje(SalgsLinie sl) {
        this.produkter.remove(sl);
    }

    /**
     *
     * @return alle produkter i arraylisten
     */
    public ArrayList<SalgsLinie> getProdukter() {
        return produkter;
    }

    public LocalTime getTid() {
        return this.tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    public LocalDate getDato() {
        return this.dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public void setStudieRabat(double procent) {
    	if (procent >= 0) {
    		studierabat = procent;
    	}
        
    }

    /**
     * Beregner en total pris for en salgslinie
     *
     * @return den lokale variable
     */
    public double getTotalPris() {
        double p = 0;
        for (SalgsLinie s : this.produkter) {
            p += s.getPris();

        }
        p -= this.rabat;
        if (this.studerende) {
            p = p * (1 - (studierabat / 100));
        }
        this.pris = p;
        return p;
    }

    public void setStuderende(boolean b) {
        this.studerende = b;
    }

    public void setPris(double nypris) {
        if (nypris > 0) {
            this.pris = nypris;
        }
    }

    /**
     * Sætter rabatten til 0.
     */
    public void fjernRabat() {
        this.rabat = 0;
        getTotalPris();
    }

    /**
     * Sætter rabatten til hvad der står i inputRabat
     *
     * @param inputRabat
     */
    public void givRabatAbsolut(double inputRabat) {
        if (inputRabat > 0) {
            this.rabat = inputRabat;
            getTotalPris();
        }

    }

    /**
     * Laver rabatten til procent og benytter sig af inputRabat,
     *
     * @param inputRabat
     */
    public void givRabatProcent(double inputRabat) {
        if (inputRabat > 0) {
            this.rabat = 0;
            this.rabat = getTotalPris() * (1 - (inputRabat / (100)));
            getTotalPris();
        }

    }

    public Betalingsmetode getBetalingsMetode() {
        return this.betalingmetode;
    }

    public void setBetalingsMetode(Betalingsmetode metode) {
        this.betalingmetode = metode;
    }

}
