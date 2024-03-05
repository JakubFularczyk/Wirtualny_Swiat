package pl.kubafularczyk;

import java.util.HashSet;
import java.util.Set;

public class Lis extends Zwierze {

    public Lis(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.symbol = "L";
        this.inicjatywa = 7;
    }

    @Override
    protected void akcja() {
        // TODO do weryfikacji po zaimplementowaniu poprawnej akcji i kolizji dla zwierzat
        Organizm[][] plansza = swiat.getPlansza();
        Polozenie nowePolozenie;
        try {
            nowePolozenie = losowanieWolnegoPolozenia();
        } catch (BrakWolnegoPolozeniaException e) {
            // TODO 05.03.2024 docelowo komentator:
            System.out.println("Lis nie ma sie gdzie poruszyc");
            return;
        }

        Polozenie starePolozenie = polozenie;
        polozenie = nowePolozenie;
        swiat.dodajOrganizmDoPlanszy(this);
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
    }

    @Override
    public Organizm stworz(Polozenie polozenie, Swiat swiat) {
        return null;
    }
}
