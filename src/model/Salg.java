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
    private double rabat;
    private double pris;
    private static int counter = 0;
    private int id;

    public Salg(SalgSted sted) {
        this.sted = sted;
        produkter = new ArrayList<>();
        counter++;
        id = counter;

    }

    public void opretSalgslinie(int antal, Produkt produkt) {
        SalgsLinie linie = new SalgsLinie(antal, produkt, this.sted);
        produkter.add(linie);

    }

    public ArrayList<SalgsLinie> getProdukter() {
        return produkter;
    }

    public double getTotalPris() {
        double p = 0;
        for (SalgsLinie s : this.produkter) {
            pris += s.getPris();
        }
        p -= this.rabat;
        this.pris = p;
        return p;
    }

    public void givStudieRabat() {
        // TODO
        // opdatere pris
    }

    public void fjernRabat() {
        // TODO
        // opdatere pris
    }

    public void givRabatAbsolut(double inputRabat) {
        this.rabat = inputRabat;
        getTotalPris();

    }

    public void givRabarProcent(double inputRabat) {
        this.rabat = 0;
        this.rabat = getTotalPris() * (1 - (inputRabat * 0.01));
        getTotalPris();
    }

    public Betalingsmetode getBetalingsMetode() {
        return this.betalingmetode;
    }

    public void setBetalingsMetode(Betalingsmetode metode) {
        this.betalingmetode = metode;
    }

}
