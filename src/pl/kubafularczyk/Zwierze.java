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

        /*
        1. wybieramy losowo kierunek (gora, dol, lewo, prawo)
            1.1. sprawdzamy czy idac w danym kierunku nie wyjdziemy poza plansze
                1.1.1. wyznaczamy na podstawie kierunku nowa pozycje
                1.1.2. sprawdzamy poprawnosc nowej pozycji (czy miesci sie na planszy)
                1.1.3. jesli nowa pozycja jest nieprawidlowa - losujemy nowy kierunek (i sprawdzamy od nowa) -> 1.1
                1.1.4 jesli nowa pozycja jest prawidlowa - idziemy dalej -> 2.
        2. zmieniamy polozenie w organizmie i na planszy
            2.1 usuwamy stare polozenie z planszy


        rob {
            nowyKierunek = losujNowyKierunek();
            nowaPozycja = staraPozycja.przesun(nowyKierunek);
        } dopoki (!nowaPozycja.czyPoprawna(wymiaryPlanszy));
            organizm.setPozycja(nowaPozycja);
            plansza[nowaPozycja] = organizm
            plansza[staraPozycja] = pusto

         */
        Organizm[][] plansza = swiat.getPlansza();
        Polozenie nowePolozenie;
        boolean przejscieDalej = false;
        do {
            Kierunek nowyKierunek = Kierunek.losuj();
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            if(nowePolozenie.czyWolne(nowyKierunek, plansza)){
                przejscieDalej = true;
            }
        } while(!nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc()) && przejscieDalej);
        /*Random random = new Random();
        int x = polozenie.getX();
        int y = polozenie.getY();



        Kierunek nowyKierunek = Kierunek.DOL;

        Kierunek[] kierunki = Kierunek.values();
        int losowyIndeks = random.nextInt(kierunki.length);
        Kierunek wybranyKierunek = kierunki[losowyIndeks];
        Polozenie nowePolozenie = polozenie.przesun(wybranyKierunek);
        if (!nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc())) {

        }


        int noweX = x + wybranyKierunek.getX();
        int noweY = x + wybranyKierunek.getY();


        int[][] kierunki = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // kierunki gora, dol, lewo, prawo
        int losowyIndeks = random.nextInt(kierunki.length);
        int[] wybranyKierunek = kierunki[losowyIndeks];
        int noweX = x + wybranyKierunek[0];
        int noweY = y + wybranyKierunek[1];
        if(noweX > plansza.length && noweY < 0) {
            losowyIndeks = random.nextInt(kierunki.length);
            wybranyKierunek = kierunki[losowyIndeks];
            noweX = x + wybranyKierunek[0];
            noweY = y + wybranyKierunek[1];

        }*/
        //polozenie.setY(noweY);
        //polozenie.setX(noweX);
        Polozenie starePolozenie = polozenie;
        polozenie = nowePolozenie;
        plansza[polozenie.getY()][polozenie.getX()] = this;
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
        //plansza[noweY][noweX] = this;
        //plansza[y][x] = null;
    }




}
