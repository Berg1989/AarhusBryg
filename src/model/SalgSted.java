package model;

import java.util.ArrayList;

public class SalgSted {

    // Attributter til klassen
    private String stedNavn;
    private ArrayList<ProduktKategori> pkList = new ArrayList<>();

    // Constructoren til klassen
    public SalgSted(String stedNavn) {
        this.stedNavn = stedNavn;
    }

    // denne metode returnere hvad der står i stedNavn attributen
    public String getBarNavn() {
        return stedNavn;
    }

    // denne metode gør det muligt at sætte et stedNavn, hvis stedNavn ikke allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setBarNavn(String barNavn) {
        this.stedNavn = barNavn;
    }

    // denne metode returnere en ny arraylist med alle objecter i pkList
    public ArrayList<ProduktKategori> getProduktKategorier() {
        return new ArrayList<>(pkList);
    }

    // denne metode tilføjer det produktkategori som der står i parameteren til
    // pkList
    public void addProduktKategori(ProduktKategori pk) {
        pkList.add(pk);
    }

    // denne metode sletter det produktkategori som der står i parameteren fra
    // pkList
    public void removeProduktKategori(ProduktKategori pk) {
        pkList.remove(pk);
    }

    @Override
    public String toString() {
        return stedNavn;
    }

}
