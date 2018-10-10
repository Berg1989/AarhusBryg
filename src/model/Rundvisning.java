package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning {

    private String kundeNavn;
    private LocalDate dato;
    private LocalTime tid;
    private int antalGaester;
    private double pris;
    private boolean betalt;
    private String rundviser;
    private boolean spisning;
    private int antalSpisende;


    public Rundvisning(String kunde, LocalDate dato, int gaester, LocalTime tid) {
         this.kundeNavn = kunde;
         this.dato = dato;
         this.tid = tid;
         this.antalGaester = gaester;
    }

   

    public void setKunde(String s) {
         this.kundeNavn = s;
    }

   

    public void setDate(LocalDate d) {
         this.dato = d;
    }

   

    public void setTime(LocalTime t) {
         this.tid = t;
    }

   

    public void setAntalGaester(int a) {
         this.antalGaester = a;
    }

   

    public void setPris(double p) {
         this.pris = p;
    }

   

    public void setBetalt() {
         this.betalt = true;
    }

   

    public void setRundviser(String rv) {
         this.rundviser = rv;
    }

   

    public void setSpisning(boolean b) {
         this.spisning = b;
    }

   

    public void setAntalSpisende(int as) {
         this.antalSpisende = as;
    }

   

    public String getKunde() {
         return this.kundeNavn;
    }

   

    public LocalDate getDato() {
         return this.dato;
    }

   

    public LocalTime getTid() {
         return this.tid;
    }

   

    public double getPris() {
         return this.pris;
    }

   

    public boolean isBetalt() {
         return this.betalt;
    }

   

    public String getRundviser() {
         return this.rundviser;
    }

   

    public int getAntalGaester() {
         return this.antalGaester;
    }

   

    public boolean hasSpisning() {
         return this.spisning;
    }

   

    public int antalSpisende() {
         return this.antalSpisende;
    }

   

    public boolean isTilgaengeligt() {

         if (this.dato.getDayOfWeek().toString().equals("SATURDAY") || this.dato.getDayOfWeek().toString().equals("SUNDAY")) { //Make Pretty
              return false;
         }

         else if (this.dato.getDayOfWeek().toString().equals("FRIDAY") && this.tid.isAfter(LocalTime.of(15, 59))) { //Make Pretty
              return false;
         }
         return true;
    }

   

    public double beregnPris() {
         double pris = 0;
         LocalTime fyraftenNomral = LocalTime.of(16, 00).minusMinutes(1);
         LocalTime fyraftenFredag = LocalTime.of(16, 00).minusMinutes(1);
         if ((this.tid.isAfter(fyraftenFredag) && this.dato.getDayOfWeek().toString().equals("FRIDAY")) || this.tid.isAfter(fyraftenNomral)) { //Make Pretty
              pris += this.antalGaester * 120;
         }
        
         else {
              pris += this.antalGaester * 100;
         }
         if (this.spisning) {
        	 pris += this.antalSpisende * 130;
         }
         return pris;
    }
    
    //Made's push

}
