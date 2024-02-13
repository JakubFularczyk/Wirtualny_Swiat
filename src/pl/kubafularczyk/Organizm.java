package pl.kubafularczyk;


public abstract class Organizm {

    private boolean zyje;
    protected int sila;
    protected int inicjatywa;
    protected Polozenie polozenie;
    protected Swiat swiat;
    protected String symbol;


    public Organizm(Polozenie polozenie, Swiat swiat) {
        this.polozenie = polozenie;
        this.swiat = swiat;
        this.zyje = true;
    }

    protected abstract void akcja();

    protected abstract void kolizja(Organizm atakowanyOrganizm);

    public String getSymbol(){
        return symbol;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public abstract Organizm stworz(Polozenie polozenie, Swiat swiat);

    public boolean czyZyje() {
        return zyje;
    }
    public void zabij() {
        zyje = false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
