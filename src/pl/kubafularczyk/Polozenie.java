package pl.kubafularczyk;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
    protected boolean czyWolne(Kierunek kierunek, Organizm[][] plansza){
        return plansza[kierunek.getY()][kierunek.getX()] == null;
    }
}
