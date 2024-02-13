package pl.kubafularczyk;



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

        Organizm organizm = swiat.getOrganizm(nowePolozenie);
        if(organizm != null) {
            kolizja(organizm);
        } else {
            Polozenie starePolozenie = polozenie;
            polozenie = nowePolozenie;
            swiat.dodajOrganizmDoPlanszy(this);
            plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
        }
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        swiat.komentator.kolizjaPostaci(polozenie, atakowanyOrganizm);
        swiat.komentator.usmierceniePostaci(swiat.getOrganizm(polozenie), atakowanyOrganizm);
        // TODO chwilowe rozwiazanie :) atakujacy poki co zawsze wygrywa
        atakowanyOrganizm.zabij();
        Organizm[][] plansza = swiat.getPlansza();

        // TODO ten kawalek powtarza sie przy akcji, moze da sie z tego zrobic metode?
        Polozenie starePolozenie = polozenie;
        polozenie = atakowanyOrganizm.getPolozenie();
        swiat.dodajOrganizmDoPlanszy(this);
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
    }

}
