package model;

public class StedPris {
	private Bar sted;
	private Produkt produkt;
	private double pris;

	public StedPris(Bar sted, Produkt produkt, double pris) {
		this.sted = sted;
		this.produkt = produkt;
		this.pris = pris;
	}

	public Bar getSted() {
		return sted;
	}

	public void setSted(Bar sted) {
		this.sted = sted;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

}
