package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class Salg {
	private Map<Integer, Produkt> produkter;
	private Betalingsmetode betalingmetode;
	private LocalDate dato;
	private LocalTime tid;
	private double rabat;
	private static int counter = 0;
	private int id;
	
	public Salg() {
		produkter = new HashMap<Integer, Produkt>();
		counter ++;
		id  = counter;
		
	}
	

}
