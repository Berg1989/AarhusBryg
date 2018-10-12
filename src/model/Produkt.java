package model;

import java.util.ArrayList;

public class Produkt {

	private String navn;
	private double pris;
	private String str;
	private ArrayList<StedPris> stedPriser;

	public Produkt(String navn, double pris, String str) {
		this.navn = navn;
		this.pris = pris;
		this.str = str;
		stedPriser = new ArrayList<>();
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void addStedPris(StedPris sp) {
		stedPriser.add(sp);
	}

	@Override
	public String toString() {
		return navn + ", " + str + ", " + pris + "kr" + ".";
	}

}
