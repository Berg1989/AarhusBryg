package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning {

    // attributter til klassen
    private String kundeNavn;
    private LocalDate dato;
    private LocalTime tid;
    private int antalGaester;
    private double pris;
    private boolean betalt;
    private String rundviser;
    private boolean spisning;
    private int antalSpisende;
    private boolean studerende;

    // Constructoren til klassen
    public Rundvisning(String kunde, LocalDate dato, LocalTime tid, int gaester) {
        this.kundeNavn = kunde;
        this.dato = dato;
        this.tid = tid;
        this.antalGaester = gaester;
        this.betalt = false;
        this.spisning = false;
        this.antalSpisende = 0;
        this.studerende = false;
    }

    // denne metode gør det muligt at lave kundenavn, hvis kundenavn ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setKunde(String s) {
        this.kundeNavn = s;
    }

    // denne metode gør det muligt at sætte en dato, hvis dato'en ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setDate(LocalDate d) {
        this.dato = d;
    }

    // denne metode gør det muligt at sætte et tidspunkt, hvis tidspunktet ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setTime(LocalTime t) {
        this.tid = t;
    }

    // denne metode gør det muligt at sætte et antal gæster, hvis det ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setAntalGaester(int a) {
        this.antalGaester = a;
    }

    // denne metode gør det muligt at sætte prisen, hvis prisen ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setPris(double p) {
        this.pris = p;
    }

    // denne metode gør det muligt at sætte rundvisningen som betalt.
    public void setBetalt() {
        this.betalt = true;
    }

    // denne metode gør det muligt at sætte rundvisning, hvis rundvisningen ikke
    // allerede
    // eksistere. Hvis den gør, så overrider den det der stod der før til det som
    // står i parameteren.
    public void setRundviser(String rv) {
        this.rundviser = rv;
    }

    // denne metode gør det muligt at tilmelde sig spisning, idet at spisning er en
    // boolean, som man sætter til true, hvor den som default sætter alle som er til
    // rundvisningen til at spise med.
    public void tilmeldSpisning() {
        this.spisning = true;
        this.antalSpisende = this.antalGaester;
    }

    // denne metode gør det muligt at sige om det er studerende der kommer til
    // rundvisning.
    public void setStuderende(boolean b) {
        this.studerende = b;
    }

    // denne metode gør det muligt at tilmelde sig spisning, idet at spisning er en
    // boolean, som man sætter til true, hvor den sætter antal spisende til det
    // antal som der står i parameteren
    public void tilmeldSpsning(int spisende) {
        this.spisning = true;
        this.antalSpisende = spisende;
    }

    // denne metode gør det muligt at framelde sig til spisning.
    public void frameldSpisning() {
        this.spisning = false;
    }

    // denne metode gør det muligt at sætte hvor mange der kommer til spisning
    public void setAntalSpisende(int as) {
        this.antalSpisende = as;
    }

    // denne metode returnere hvad der står i attributen "kundenavn".
    public String getKunde() {
        return this.kundeNavn;
    }

    // denne metode returnere hvad der står i attributen "dato".
    public LocalDate getDato() {
        return this.dato;
    }

    // denne metode returnere hvad der står i attributen "tid".
    public LocalTime getTid() {
        return this.tid;
    }

    // denne metode returnere hvad der står i attributen "pris".
    public double getPris() {
        return this.pris;
    }

    // denne metode gør det muligt at se om der er blevet betalt eller ej.
    public boolean isBetalt() {
        return this.betalt;
    }

    // denne metode returnere hvad der står i attributen "rundvisner".
    public String getRundviser() {
        return this.rundviser;
    }

    // denne metode returnere hvad der står i attributen "antalGaester".
    public int getAntalGaester() {
        return this.antalGaester;
    }

    // denne metode gør det muligt at se om der er blevet valgt spisning eller ej.
    public boolean hasSpisning() {
        return this.spisning;
    }

    // denne metode returnere hvad der står i attributen "AntalSpisende".
    public int antalSpisende() {
        return this.antalSpisende;
    }

    // denne metode gør det muligt at se om der er blevet valgt studerende eller ej.
    public boolean isStuderende() {
        return this.studerende;
    }

    // denne metode tjekker om dato'en, man har valgt er indenfor AB's åbningstider.
    // Hvis dato'en er lørdag eller søndag, så vil der blive returneret false,
    // derefter tjekker den om dato'en er fredag, og om slut tiden er efter 15:00,
    // hvis dette er tilfældet så vil der blive returneret false. Hvis ingen af
    // disse if statements betingelser bliver mødt, så er dato'en en hverdag før
    // fredag kl 15:00 og der vil blive returneret true

    public boolean isTilgaengeligTid() {

        LocalTime slut = slutTid();

        if (this.dato.getDayOfWeek().toString().equals("SATURDAY")
                || this.dato.getDayOfWeek().toString().equals("SUNDAY")) { // Make Pretty
            return false;
        }

        else if (this.dato.getDayOfWeek().toString().equals("FRIDAY") && slut.isAfter(LocalTime.of(15, 00))) { // Make
                                                                                                               // Pretty
            return false;
        }
        return true;
    }

    // denne metode tjekker først om sluttiden er efter kl 16:00, hvis dette er
    // tilfældet, så tjekker den om antalGaester er imellem 75, hvis dette er
    // tilfældet return true, hvis ikke return false. Hvis sluttiden er før
    // kl 16:00, så sker der det samme tjek angående antalGaester som før, bare at
    // differencen er fra 15 til 75 istedet.
    public boolean isTilgaengeligtAntalGaester() {
        if (slutTid().isAfter(LocalTime.of(16, 00))) {
            if (this.antalGaester < 20 || this.antalGaester > 75) {
                return false;
            } else {
                return true;
            }
        } else {
            if (this.antalGaester < 15 || this.antalGaester > 75) {
                return false;
            } else {
                return true;
            }
        }
    }

    // Denne metode returnere den tid som en rundvisning tager, hvis spisning er
    // med, så tillægges der en ekstra 30 minutter.

    public LocalTime slutTid() {
        int tid = 90;
        if (this.spisning) {
            tid += 30;
        }
        LocalTime slut = this.tid.plusMinutes(tid);
        return slut;
    }

    // Denne metode beregner prisen for en rundvisning, hvor den har en lokal
    // variabel som er double og hedder "pris". Hvorefter metoden går igennem
    // adskillige if statements, hvorefter hvis betingelserne i disse if statements
    // er opfyldte så vil der ske visse ændringere til "pris"
    public double beregnPris() {
        double pris = 0.0;
        if (isTilgaengeligTid() && isTilgaengeligtAntalGaester()) {
            if (slutTid().isAfter(LocalTime.of(16, 00))) {
                pris += this.antalGaester * 120;
            } else {
                pris += this.antalGaester * 100;
            }
            if (this.spisning == true) {
                pris += this.antalSpisende * 130;
            }
        }
        return pris;
    }

    @Override
    public String toString() {
        return "" + getDato().toString() + "kl " + this.tid.toString() + "(" + this.kundeNavn + ")";
    }

}
