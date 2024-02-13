package pl.kubafularczyk;

import java.util.HashSet;
import java.util.Set;

public class Lis extends Zwierze {

    public Lis(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
    }

    @Override
    protected void akcja() {
        // TODO do weryfikacji po zaimplementowaniu poprawnej akcji i kolizji dla zwierzat
        Organizm[][] plansza = swiat.getPlansza();
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        boolean polozenieZajete;
        Set<Kierunek> wykorzystaneKierunki = new HashSet<>();
        do {
            if(wykorzystaneKierunki.size() == Kierunek.values().length) {
                return;
            }
            Kierunek nowyKierunek = Kierunek.losuj();
            wykorzystaneKierunki.add(nowyKierunek);
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
            polozenieZajete = !nowePolozenie.czyWolne(plansza);
        } while(polozenieNiepoprawne || polozenieZajete);

        Polozenie starePolozenie = polozenie;
        polozenie = nowePolozenie;
        plansza[polozenie.getY()][polozenie.getX()] = this;
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
    }

    @Override
    public Organizm stworz(Polozenie polozenie, Swiat swiat) {
        return null;
    }
}
