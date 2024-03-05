package pl.kubafularczyk;


import java.util.HashSet;
import java.util.Set;

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

    protected Polozenie losowanieWolnegoPolozenia() throws BrakWolnegoPolozeniaException {
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        boolean polozenieZajete;
        Set<Kierunek> wykorzystaneKierunki = new HashSet<>();
        do {
            polozenieZajete = false;
            if(wykorzystaneKierunki.size() == Kierunek.values().length) {
                throw new BrakWolnegoPolozeniaException();
            }
            Kierunek nowyKierunek = Kierunek.losuj();
            wykorzystaneKierunki.add(nowyKierunek);
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
            if (polozenieNiepoprawne) {
                continue;
            }
            polozenieZajete = !nowePolozenie.czyWolne(swiat.getPlansza());
        } while (polozenieNiepoprawne || polozenieZajete);
        return nowePolozenie;
    }

}
