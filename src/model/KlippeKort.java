package model;

public class KlippeKort {

	private String navn;
	private int antalKlip;
	private double pris;

	public KlippeKort(String navn, int antalKlip, double pris) {
		this.navn = navn;
		this.antalKlip = antalKlip;
		this.pris = pris;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getAntalKlip() {
		return antalKlip;
	}

	public void setAntalKlip(int antalKlip) {
		this.antalKlip = antalKlip;
	}

	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	@Override
	public String toString() {
		return navn + ", " + antalKlip + ", " + pris;
	}

}
