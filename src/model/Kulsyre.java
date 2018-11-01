package model;

public class Kulsyre implements Rentable {
	private double stoerrelseKG;
	private double pris;
	private static double pant = 1000.0;
	
	public Kulsyre(double stoerrelseKG, double pris) {
		this.stoerrelseKG = stoerrelseKG;
		this.pris = pris;
		
	}
	
	public void setStoerrelse(double kg) {
		this.stoerrelseKG = kg;
	}
	
	public void setPris(double pris) {
		this.pris = pris;
	}
	
	public double getPris() {
		return this.pris;
	}
	
	public double getPant() {
		return pant;
	}
	
	public double getTotal() {
		return pant + this.pris;
	}
	
	public double getStoerrelse() {
		return this.stoerrelseKG;
	}
	
	@Override
	public String toString() {
		return this.stoerrelseKG +" kg, " + this.pris +" kr";
	}

}
