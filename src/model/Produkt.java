package model;

public class Produkt {
	private String navn;
	private double pris;
	private String str;

	public Produkt(String navn, double pris, String str) {
		this.navn = navn;
		this.pris = pris;
		this.str = str;
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

}
