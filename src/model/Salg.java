package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Salg {

    // Attributter til klassen
    private SalgSted sted;
    private ArrayList<SalgsLinie> produkter;
    private Betalingsmetode betalingmetode;
    private LocalDate dato;
    private LocalTime tid;
    private double pris;
    private double rabat;
    private static int counter = 0;
    private int id;

    // Constructor til klassen
    public Salg(SalgSted sted) {
        this.sted = sted;
        produkter = new ArrayList<>();
        counter++;
        id = counter;

    }

    // Denne metode opretter en salgslinie og tilføjer den til arraylisten
    // "produkter", hvis antal paramenteren er større end 0.

    public void opretSalgslinie(int antal, Produkt produkt) {
        if (antal > 0) {
            SalgsLinie linie = new SalgsLinie(antal, produkt, this.sted);
            produkter.add(linie);
        }

	public ArrayList<SalgsLinie> getProdukter() {
		return produkter;
	}
 
	public LocalTime getTid() {
		return this.tid;
	}
	
	public void setTid(LocalTime tid) {
		this.tid = tid;
	}

    // Denne metode returnere alle produkter i arraylisten
    public ArrayList<SalgsLinie> getProdukter() {
        return produkter;
    }

    // denne metode gør det muligt at sætte en tid, hvis tiden ikke allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    // denne metode returnere hvad der står i dato attributen
    public LocalDate getDato() {
        return dato;

    }

	//TROR DENNE SKAL SLETTES, HAR IKKE TESTS
	public void setPris(double pris) {
		this.pris = pris;
	}
	
	//Fuldt testet hertil. Fortsaet herfra :)

    /// KIG HER///
    // Denne metode gør at man kan sætte hele nye objekter fra en anden salgslinie
    /// til en anden
    public void setProdukter(ArrayList<SalgsLinie> produkter) {
        this.produkter = produkter;
    }

    // denne metode gør det muligt at sætte en pris, hvis prisen ikke allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setPris(double pris) {
        this.pris = pris;
    }

    public void givStudieRabat() {
        // TODO
        // opdatere pris
    }

    // Denne metode sætter rabat til 0, derved fjerner rabatten
    public void fjernRabat() {
        this.rabat = 0;
        getTotalPris();
    }

    // Denne metode ændre rabatten til hvad som der står i parameteren
    public void givRabatAbsolut(double inputRabat) {
        this.rabat = inputRabat;
        getTotalPris();

    }

    // Denne metode gør det muligt at give % rabat
    public void givRabarProcent(double inputRabat) {
        this.rabat = 0;
        this.rabat = getTotalPris() * (1 - (inputRabat * 0.01));
        getTotalPris();
    }

    // Denne metode returnere en betalingsmetode fra Enum klassen
    public Betalingsmetode getBetalingsMetode() {
        return this.betalingmetode;
    }

    // Denne metode sætter betalingsmetoden til hvad der står i parameteren
    public void setBetalingsMetode(Betalingsmetode metode) {
        this.betalingmetode = metode;
    }

}
