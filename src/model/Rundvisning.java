package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning {
	private String kundeNavn;
	private LocalDate dato;
	private LocalTime tid;
	private double pris;
	private boolean betalt;
	private String rundviser;
	
	
	public Rundvisning(String kunde, LocalDate dato, LocalTime tid) {
		this.kundeNavn = kunde;
		this.dato = dato;
		this.tid = tid;
	}
	
	public void setKunde(String s) {
		this.kundeNavn = s;
	}
	
	public void setDate(LocalDate d) {
		this.dato = d;
	}
	
	public void setTime(LocalTime t) {
		this.tid = t;
	}
	
	public void setPris(double p) {
		this.pris = p;
	}
	
	public void setBetalt() {
		this.betalt = true;
	}
	
	public void setRundviser(String rv) {
		this.rundviser = rv;
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
	
	public boolean isBetalt() {
		return this.betalt;
	}
	
	public String getRundviser() {
		return this.rundviser;
	}
	
	
}
