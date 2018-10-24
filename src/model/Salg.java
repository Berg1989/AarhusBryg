package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Salg {
	private ArrayList<SalgsLinie> produkter;
	private Betalingsmetode betalingmetode;
	private LocalDate dato;
	private LocalTime tid;
	private double rabat;
	private static int counter = 0;
	private int id;
	
	public Salg() {
		produkter = new ArrayList<>();
		counter ++;
		id  = counter;
		
	}
	
	public void opretSalgslinie(int antal, Produkt produkt) {
		SalgsLinie linie = new SalgsLinie(antal, produkt);
		produkter.add(linie);
		
	}
	

}
