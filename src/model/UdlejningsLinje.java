package model;

public class UdlejningsLinje {
	private Rentable produkt;
	private int antal;
	
	public UdlejningsLinje(Rentable produkt, int antal) {
		this.produkt = produkt;
		this.antal = antal;
	}
	
	public double getPant() {
		return produkt.getPant() * antal;
	}
	
	public double getPris() {
		return produkt.getPris() * antal;
	}
	
	public double getTotalPris() {
		return this.getPant() + this.getPris();
	}
	
	@Override
	public String toString() {
		return this.antal + " stk " + produkt.toString();
	}
	
	public Rentable getProdukt() {
		return this.produkt;
	}
	
	

}
