package model;

public class Anlaeg implements Rentable {

    // Attributes til klassen
    private String type;
    private double pris;
    private double pant;

    // The Counstructor
    public Anlaeg(String type, double pris, double pant) {
        this.type = type;
        this.pris = pris;
        this.pant = pant;
    }

    // denne metode gør det muligt at lave en type, hvis typen ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setType(String type) {
        this.type = type;
    }

    // denne metode gør det muligt at sætte prisen, hvis prisen ikke allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.

    public void setPris(double pris) {
        this.pris = pris;
    }

    // denne metode gør det muligt at sætte panten, hvis panten ikke allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setPant(double pant) {
        this.pant = pant;
    }

    // denne metode returnere hvad der står i type attributen.
    public String getType() {
        return this.type;
    }

    // denne metode returnere hvad der står i pris attributen
    @Override
    public double getPris() {
        return this.pris;
    }

    // denne metode returnere hvad der står i pant attributen
    @Override
    public double getPant() {
        return this.pant;
    }

    // Denne metode skaffer et anlægs pris, hvor panten er lagt oveni
    @Override
    public double getTotal() {
        return this.pant + this.pris;
    }

    @Override
    public String toString() {
        return this.type + " pris: " + this.pris + " kr (pant: " + this.pant + " kr)";
    }

}
