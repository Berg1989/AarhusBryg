package model;

import java.util.ArrayList;

public class SalgSted {

    private String stedNavn;
    private ArrayList<ProduktKategori> pkList = new ArrayList<>();

    /**
     * Constructoren
     *
     * @param stedNavn
     */
    public SalgSted(String stedNavn) {
        this.stedNavn = stedNavn;
    }

    public String getBarNavn() {
        return stedNavn;
    }

    public void setBarNavn(String barNavn) {
        this.stedNavn = barNavn;
    }

    /**
     *
     * @return returnere en ny arraylist med alle objecter i pkList
     */
    public ArrayList<ProduktKategori> getProduktKategorier() {
        return new ArrayList<>(pkList);
    }

    /**
     * Tilfoejer pk til pkList
     *
     * @param pk
     */
    public void addProduktKategori(ProduktKategori pk) {
        pkList.add(pk);
    }

    /**
     * Fjerner pk fra pkList
     * 
     * @param pk
     */
    public void removeProduktKategori(ProduktKategori pk) {
        pkList.remove(pk);
    }

    @Override
    public String toString() {
        return stedNavn;
    }

}
