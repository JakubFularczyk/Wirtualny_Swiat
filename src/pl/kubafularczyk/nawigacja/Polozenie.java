package pl.kubafularczyk.nawigacja;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.Organizm;

import java.util.ArrayList;
import java.util.List;

public class Polozenie {

    private int x;
    private int y;

    public Polozenie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Tworzy kopie aktualnego polozenia przesuwając ja we wskazanym kierunku.
     * @param kierunek Kierunek przesuniecia aktualnego polozenia.
     * @return Nowe polozenie.
     */
    public Polozenie stworzPrzesunietaKopie(Kierunek kierunek) {
        return new Polozenie(kierunek.getX() + x, kierunek.getY() + y);
    }

    /**
     * Sprawdza czy polozenie odpowiada pozycji na planszy.
     * @param szerokoscPlanszy szerokosc planszy.
     * @param wysokoscPlanszy wysokosc planszy.
     * @return boolean czy polozenie jest poprawne.
     */
    public boolean czyPoprawne(int szerokoscPlanszy, int wysokoscPlanszy) {
        return y < wysokoscPlanszy && x < szerokoscPlanszy && y >= 0 && x >= 0;
    }
    public List<Polozenie> pobierzPolozeniaDookola(Polozenie polozenie, Swiat swiat) {
        List<Polozenie> polozenia = new ArrayList<>();
        for(Kierunek kierunek : Kierunek.values()){
            Polozenie przesunietePolozenie = polozenie.stworzPrzesunietaKopie(kierunek);
            if (przesunietePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc())) {
                polozenia.add(przesunietePolozenie);
            }
        }
        return polozenia;
    }


    /**
     * Sprawdza czy polozenie na planszy nie jest zajete przez inny organizm.
     * @return czy polozenie jest wolne.
     */
    public boolean czyWolne(Organizm[][] plansza){
        return plansza[this.y][this.x] == null;
    }

    @Override
    public String toString() {
        return "Polozenie{" + "x=" + x + ", y=" + y + "}";
    }
}
