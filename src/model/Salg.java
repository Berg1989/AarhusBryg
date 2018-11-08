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
	private boolean studerende;
	private static double studierabat = 20.0;

	// Constructor til klassen
	public Salg(SalgSted sted) {
		this.sted = sted;
		produkter = new ArrayList<>();
		counter++;
		id = counter;
		this.studerende = false;

	}

	// Denne metode opretter en salgslinie og tilføjer den til arraylisten
	// "produkter", hvis antal paramenteren er større end 0.

	public void opretSalgslinie(int antal, Produkt produkt) {
		if (antal > 0) {
			SalgsLinie linie = new SalgsLinie(antal, produkt, this.sted);
			produkter.add(linie);
		}
	}
	
	public void sletSalgsLinje(SalgsLinie sl) {
		this.produkter.remove(sl);
	}

	public ArrayList<SalgsLinie> getProdukter() {
		return produkter;
	}

	public LocalTime getTid() {
		return this.tid;
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
	
	public void setStudieRabat(double procent) {
		studierabat = procent;
	}

	public LocalDate setDato(LocalDate dato) {
		return this.dato = dato;
	}

	/**
	 * 
	 * @return double with the total price of the purchase, with discouts subtracted
	 */
	public double getTotalPris() {
		double p = 0;
		for (SalgsLinie s : this.produkter) {
			p += s.getPris();

		}
		p -= this.rabat;
		if (this.studerende) {
			p = p * (1- (studierabat/ 100));
		}
		this.pris = p;
		return p;
	}


	
	//Fuldt testet hertil. Fortsaet herfra :)
	public void setStuderende(boolean b) {
		this.studerende = b;
	}
	
	public void setPris(double nypris) {
		if (nypris > 0) {
			this.pris = nypris;
		}
	}

	// Denne metode sætter rabat til 0, derved fjerner rabatten
	public void fjernRabat() {
		this.rabat = 0;
		getTotalPris();
	}

	// Denne metode ændre rabatten til hvad som der står i parameteren
	public void givRabatAbsolut(double inputRabat) {
		if (inputRabat > 0) {
			this.rabat = inputRabat;
			getTotalPris();
		}
		

	}

	// Denne metode gør det muligt at give % rabat
	public void givRabatProcent(double inputRabat) {
		if (inputRabat > 0) {
			this.rabat = 0;
			this.rabat = getTotalPris() * (1 - (inputRabat/(100)));
			getTotalPris();
		}
		
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
