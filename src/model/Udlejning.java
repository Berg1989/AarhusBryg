package model;

import model.Fustage;
import model.Anlaeg;
import java.time.LocalDate;
import java.util.ArrayList;

public class Udlejning {
	private String kundeNavn;
	private String kundeTlf;
	private String kundeEmail;
	private ArrayList<Fustage> fustager;
	private double fustagepant;
	private ArrayList<Anlaeg> anlaeg;
	private LocalDate dato;
	private int glas;
	private String kommentar;
	private double pris;
	private double pant;
	private boolean levering;
	private double leveringspris;
	private int kulsyre;
	private double kulsyrepant;
	
	public Udlejning(String navn, String tlf, String email, LocalDate dato) {
		this.kundeNavn = navn;
		this.kundeTlf = tlf;
		this.kundeEmail = email;
		this.dato = dato;
		this.anlaeg = new ArrayList<>();
		this.fustager = new ArrayList<>();
		this.glas = 0;
		double leveringspris = 800.0;
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
	
	public double getPris() {
		// TODO
		return 0.0;
	}

	public double getPant() {
		// TODO
		return 0.0;
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
		//TODO
		return 0.0;
	}
	
	public double getSamletPrisMedPant() {
		//TODO
		return 0.0;
	}

}
