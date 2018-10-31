package model;

public class Udlejning {
	private String kundeNavn;
	private String kundeTlf;
	private String kundeEmail;
	private ArrayList<Fustage> fustager;
	private double fustagepant;
	private ArrayList<Anlaeg> anlaeg;
	private int glas;
	private String kommentar;
	private double pris;
	private double pant;
	private boolean levering;
	private int kulsyre;
	private double kulsyrepant;
	
	public Udlejning(String navn, String tlf, String email) {
		this.kundeNavn = navn;
		this.kundeTlf = tlf;
		this.kundeEmail = email;
		this.anlaeg = new ArrayList<>();
		this.fustager = new ArrayList<>();
		this.glas = 0;
	}
	
	public void setKundeNavn(String navn) {
		this.kundeNavn = navn;
	}
	
	public String getKundeNavn() {
		return this.kundeNavn;
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
	
	public ArrayList<Fustage> getFustager(){
		return new ArrayList<>(this.fustager);
	}
	
	public void addFustge(Fustage f) {
		this.fustager.add(f);
	}
	
	public double getPris() {
		//TODO
		return 0.0;
	}
	
	public double getPant() {
		//TODO
		return 0.0;
	}
	
	public double getLeveringsPris() {
		//TODO
		return 0.0;
	}

}
