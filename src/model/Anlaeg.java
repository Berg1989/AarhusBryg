package model;

public class Anlaeg implements Rentable{
	private String type;
	private double pris;
	private double pant;
	
	public Anlaeg(String type, double pris, double pant) {
		this.type = type;
		this.pris = pris;
		this.pant = pant;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPris(double pris) {
		this.pris = pris;
	}
	
	public void setPant(double pant) {
		this.pant = pant;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getPris() {
		return this.pris;
	}
	
	public double getPant() {
		return this.pant;
	}
	
	public double getTotal() {
		return this.pant + this.pris;
	}
	
	@Override
	public String toString() {
		return this.type + " pris: " + this.pris + " kr (pant: " + this.pant +" kr)";
	}

}
