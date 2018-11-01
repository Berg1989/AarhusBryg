package model;

import model.Fustage;
import model.Anlaeg;
import java.time.LocalDate;
import java.util.ArrayList;

public class Udlejning {
	private String kundeNavn;
	private String kundeTlf;
	private String kundeEmail;
	private ArrayList<Rentable> fustager;
	private static double fustagepant = 200.0;
	private ArrayList<Anlaeg> anlaeg;
	private LocalDate dato;
	private String kommentar;
	private boolean levering;
	private static double leveringspris = 800.0;
	private ArrayList<Kulsyre> kulsyre;
	private static double kulsyrepant = 1000.0;
	private boolean betalt;
	private double betaltBeloeb;
	private double pantLagt;
	
	public Udlejning() {
		this.anlaeg = new ArrayList<>();
		this.fustager = new ArrayList<>();
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

	public ArrayList<Fustage> getFustager() {
		return new ArrayList<>(this.fustager);
	}

	public void addFustge(Fustage f) {
		this.fustager.add(f);
	}
	
	public ArrayList<Anlaeg> getAnlaeg() {
		return new ArrayList<>(this.anlaeg);
	}
	
	public void addAnlaeg(Anlaeg a) {
		this.anlaeg.add(a);
	}
	

	public double getPant() {
		double p = 0.0;
		//Kulsyrepant
		p += this.kulsyre.size() * this.kulsyrepant;
		//alaegspant
		for (Anlaeg a : this.anlaeg) {
			p += a.getPant();
		}
		//fustagepant
		p += this.fustager.size() * this.fustagepant;
		return p;
	}
	
	public void setLeveringsPris(double pris) {
		this.leveringspris = pris;
	}
	
	public double getLeveringsPris() {
		if (this.levering) {
			return this.leveringspris;
		}
		else {
			return 0.0;
		}
	
	}
	
	public double getSamletPris() {
		double p = 0.0;
		//leverings
		p += this.getLeveringsPris();
		//kulsyrepris
		for (Kulsyre k : this.kulsyre) {
			p += k.getPris();
		}
		//anlaegspris
		for (Anlaeg a : this.anlaeg) {
			p += a.getPris();
		}
		//fustagepris
		for (Fustage f : this.fustager) {
			p += f.getPris();
		}
	
		return p;
	}
	
	public double getSamletPrisMedPant() {
		double p = 0.0;
		p += this.getLeveringsPris();
		p += this.getSamletPris();
		p += this.getPant();
		return 0.0;
	}
	
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

}
