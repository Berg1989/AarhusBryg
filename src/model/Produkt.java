package model;

import java.util.ArrayList;

public class Produkt {

    // Attributter til klassen
    private String navn;
    private double pris;
    private String str;
    private ArrayList<StedPris> stedPriser;

    public Produkt(String navn, double pris, String str) { // Skal vaere private?
        this.navn = navn;
        this.pris = pris;
        this.str = str;
        stedPriser = new ArrayList<>();
    }

    // denne metode returnere hvad der står i attributen "navn".
    public String getNavn() {
        return navn;
    }

    // denne metode gør det muligt at sætte navn, hvis navn ikke
    // allerede
    // eksistere. Hvis den gør, så overrider man den tidligere navn
    public void setNavn(String navn) {
        this.navn = navn;
    }

    // denne metode returnere hvad der står i attributen "pris".
    public double getPris() {
        return pris;
    }

    // denne metode gør det muligt at sætte pris, hvis pris ikke
    // allerede
    // eksistere. Hvis den gør, så overrider man den tidligere pris
    public void setPris(double pris) {
        this.pris = pris;
    }

    // denne metode returnere hvad der står i attributen "str".
    public String getStr() {
        return str;
    }

    // denne metode gør det muligt at sætte str, hvis str ikke
    // allerede
    // eksistere. Hvis den gør, så overrider man den tidligere str
    public void setStr(String str) {
        this.str = str;
    }

    // denne metode tilføjer tilføjer en stedpris til produktet.
    public void addStedPris(StedPris sp) {
        stedPriser.add(sp);
    }

    // denne metode returnere en ny arraylist med alle stedpriser
    public ArrayList<StedPris> getStedPriser() {
        return new ArrayList<>(stedPriser);
    }

    // denne metode kører alle stedpriserne igennem og tjekker om stedpris's sted er
    // det samme som det salgssted som er i parameteren, hvis det er det samme, så
    // sætter man den lokale attribut til den pris, som stedprisen er og returnere
    // det. Hvis ikke, så returnere den 0
    public double getStedPrisPris(SalgSted sted) {
        double stedPris = 0;
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                stedPris = stedPriser.get(i).getPris();
            }
        }
        return stedPris;
    }

    // denne metode kører alle arraylisten's stedpriserne igennem og tjekker om
    // stedpris's sted er
    // det samme som det salgssted som er i parameteren, hvis det er det samme, så
    // returnere den stedprisen for det salgssted. Hvis ikke, så returnere den null
    public StedPris stedPris(SalgSted sted) {
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                return stedPriser.get(i);
            }
        }
        return null;

    }

    // denne metode kører alle arraylisten's stedpriser igennem og tjekker om
    // stedpris's sted er det samme som det salgssted som er i parameteren, hvis det
    // er det samme, så fjerner den stedprisen.
    public void removeStedPris(SalgSted sted) {
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                stedPriser.remove(i);
            }
        }
    }

    public String toStringSted(SalgSted sted) {
        double stedPris = 0;
        for (int i = 0; i < stedPriser.size(); i++) {
            if (stedPriser.get(i).getSted() == sted) {
                stedPris = stedPriser.get(i).getPris();
            }
        }
        return navn + ", " + str + ", " + Double.toString(stedPris) + "kr.";
    }

    @Override
    public String toString() {
        return navn + ", " + str + ".";
    }

}
