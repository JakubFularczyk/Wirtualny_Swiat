package pl.kubafularczyk.nawigacja;

import pl.kubafularczyk.exceptions.NiedozwolonyKierunekException;

import java.util.Random;

public enum Kierunek {
    GORA(0, -1, "w"),
    DOL(0, 1, "s"),
    LEWO(-1, 0, "a"),
    PRAWO(1, 0, "d");

    private final int x;
    private final int y;
    private final String symbol;

    Kierunek(int x, int y, String symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
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

    public static Kierunek of(String wyborKierunku) {
        for(Kierunek kierunek : Kierunek.values()){
            if(kierunek.getSymbol().equalsIgnoreCase(wyborKierunku)) {
                return kierunek;
            }
        }
        throw new NiedozwolonyKierunekException(wyborKierunku);
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSymbol() {
        return symbol;
    }
}
