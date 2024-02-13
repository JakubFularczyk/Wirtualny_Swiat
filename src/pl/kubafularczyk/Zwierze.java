package pl.kubafularczyk;

import java.util.HashSet;
import java.util.Set;

public abstract class Zwierze extends Organizm {

    public Zwierze(Polozenie polozenie, Swiat swiat) {
        super(polozenie,swiat);
    }

    /**
     * Metoda odpowiedzialna za wykonanie akcji przemieszczenia organizmu
     * Akcja polega na wybraniu losowego kierunku (góra, dół, lewo, prawo) i zmianie położenia organizmu na planszy
     * z uwzględnieniem ograniczeń wynikających z rozmiarów planszy. Proces wyboru nowego położenia
     * jest powtarzany, dopóki nie zostanie znalezione położenie poprawne czyli takie które mieści się w granicach planszy
     * Po wybraniu poprawnego nowego położenia organizm jest przemieszczany na planszy.
     * Jego poprzednia pozycja jest czyszczona (ustawiana na null), a na nowej pozycji ustawiany jest organizm
     */
    @Override
    protected void akcja() {

        Organizm[][] plansza = swiat.getPlansza();
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        do {
            Kierunek nowyKierunek = Kierunek.losuj();
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
        } while(polozenieNiepoprawne);

        Polozenie starePolozenie = polozenie;
        polozenie = nowePolozenie;
        plansza[polozenie.getY()][polozenie.getX()] = this;
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
    }

    @Override
    protected void kolizja() {

    }


}
