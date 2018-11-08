package model;

public interface Rentable {

    // Dette er et interface, som bliver brugt i klasserne UdlejningsLinje Anlaeg, Fustage og Kulsyre.
    abstract double getPris();

    abstract double getPant();

    abstract double getTotal();
}
