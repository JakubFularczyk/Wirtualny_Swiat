package pl.kubafularczyk;


import org.jetbrains.annotations.NotNull;

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
        Polozenie nowePolozenie = losowaniePolozenia();
        Organizm organizm = swiat.getOrganizm(nowePolozenie);
        if(organizm != null) {
            kolizja(organizm);
        } else {
            ruchOrganizmu(nowePolozenie, plansza);
        }
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        // TODO chwilowe rozwiazanie :) atakujacy poki co zawsze wygrywa
        if(this.getClass().equals(atakowanyOrganizm.getClass())){
            // TODO poki co istnieje szansa ze nowy organizm powstanie w miejscu starego - do poprawienia (musimy losowac puste miejsce)
            Polozenie nowePolozenie = losowaniePolozenia();
            Komentator.rozmnozenieZwierzecia(this, atakowanyOrganizm, nowePolozenie);
            this.stworz(nowePolozenie, swiat);
        } else {
            Komentator.kolizjaPostaci(polozenie, atakowanyOrganizm);
            Komentator.usmierceniePostaci(this, atakowanyOrganizm);
            atakowanyOrganizm.zabij();
            Organizm[][] plansza = swiat.getPlansza();
            ruchOrganizmu(atakowanyOrganizm, plansza);
        }
    }

    // TODO do przeniesienia do Organizmu + dodanie flagi ktora bedzie kontrolowala czy ma byc losowane dowolne poprawne polozenie
    // czy dowolne puste (w przypadku dowolnego pustego nie bedzie adnotacji NotNull


    /**
     * Przemieszcza organizm na pole atakowanego organizmu i aktualizuje stare polozenie na planszy.
     * @param atakowanyOrganizm Organizm
     * @param plansza Organizm[][]
     */
    protected void ruchOrganizmu(Organizm atakowanyOrganizm, Organizm[][] plansza){
        ruchOrganizmu(atakowanyOrganizm.getPolozenie(), plansza);
    }
    /**
     * Przemieszcza organizm na pole wskazywane przez nowe polozenie i aktualizuje stare polozenie na planszy.
     * @param docelowePolozenie Polozenie
     * @param plansza Organizm[][]
     */
    protected void ruchOrganizmu(Polozenie docelowePolozenie , Organizm[][] plansza){
        Polozenie starePolozenie = polozenie;
        polozenie = docelowePolozenie;
        swiat.dodajOrganizmDoPlanszy(this);
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
    }

}
