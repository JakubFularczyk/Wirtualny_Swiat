package pl.kubafularczyk.nawigacja;

import java.util.Random;

public enum Kierunek {
    GORA(0, -1),
    DOL(0, 1),
    LEWO(-1, 0),
    PRAWO(1, 0);

    private final int x;
    private final int y;

    Kierunek(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Losuje kierunek z listy ENUM GORA,DOL,LEWO,PRAWO
     * @return wylosowany kierunek.
     */
    public static Kierunek losuj() {
        Random random = new Random();
        Kierunek[] kierunki = Kierunek.values();
        return kierunki[random.nextInt(kierunki.length)];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}
