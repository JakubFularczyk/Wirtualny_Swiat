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
        swiat.dodajOrganizm(this);
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

    protected Polozenie losowaniePolozenia() {
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        do {
            Kierunek nowyKierunek = Kierunek.losuj();
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
        } while (polozenieNiepoprawne);
        return nowePolozenie;
    }

}
