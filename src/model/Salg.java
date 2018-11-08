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

     */
     * @param sted
     *
     * Constructor
    /**
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

    }

	}
	
	public void setStudieRabat(double procent) {
		studierabat = procent;
	}

    // Fuldt testet hertil. Fortsaet herfra :)

    /**
     * Beregner en total pris for en salgslinie
     *
     * @return
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


	
	//Fuldt testet hertil. Fortsaet herfra :)
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

	// Denne metode ændre rabatten til hvad som der står i parameteren
	public void givRabatAbsolut(double inputRabat) {
		if (inputRabat > 0) {
			this.rabat = inputRabat;
			getTotalPris();
		}
		
     * Sætter rabatten til hvad der står i inputRabat
    /**
     *
     * @param inputRabat
     */

    }

	public void givRabatProcent(double inputRabat) {
		if (inputRabat > 0) {
			this.rabat = 0;
			this.rabat = getTotalPris() * (1 - (inputRabat/(100)));
			getTotalPris();
		}
		
	}

    /**
     * Laver rabatten til procent og benytter sig af inputRabat,
     *
     * @param inputRabat
     */
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
