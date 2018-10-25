package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning {

	private String kundeNavn;
	private LocalDate dato;
	private LocalTime tid;
	private int antalGaester;
	private double pris;
	private boolean betalt;
	private String rundviser;
	private boolean spisning;
	private int antalSpisende;

	public Rundvisning(String kunde, LocalDate dato, LocalTime tid, int gaester) {
		this.kundeNavn = kunde;
		this.dato = dato;
		this.tid = tid;
		this.antalGaester = gaester;
		this.betalt = false;
		this.spisning = false;
		this.antalSpisende = 0;
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

	public void setAntalGaester(int a) {
		this.antalGaester = a;
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

	public void tilmeldSpisning() {
		this.spisning = true;
		this.antalSpisende = this.antalGaester;
	}

	public void tilmeldSpsning(int spisende) {
		this.spisning = true;
		this.antalSpisende = spisende;
	}

	public void frameldSpisning() {
		this.spisning = false;
	}

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

	public boolean isBetalt() {
		return this.betalt;
	}

	public String getRundviser() {
		return this.rundviser;
	}

	public int getAntalGaester() {
		return this.antalGaester;
	}

	public boolean hasSpisning() {
		return this.spisning;
	}

	public int antalSpisende() {
		return this.antalSpisende;
	}

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

	public LocalTime slutTid() {
		int tid = 90;
		if (this.spisning) {
			tid += 30;
		}
		LocalTime slut = this.tid.plusMinutes(tid);
		return slut;
	}

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
		return "" + this.getDato().toString() + "kl " + this.tid.toString() + "(" + this.kundeNavn + ")";
	}

}
