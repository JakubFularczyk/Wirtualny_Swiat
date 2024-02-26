package pl.kubafularczyk;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Swiat {
    private List<Organizm> organizmy;
    public Komentator komentator = new Komentator();
    // TODO pod koniec projektu: sprobowac wyrzucic bezposrednie zmienianie planszy przez organizmy i zastapic budowaniem planszy na podstawie listy
    private Organizm[][] plansza;
    private int wysokosc;
    private int szerokosc;
    private int liczbaOrganizmow;
    private final Random random;
    public Swiat(int wysokosc, int szerokosc) {
        this.organizmy  = new ArrayList<>();
        this.plansza = new Organizm[wysokosc][szerokosc];
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        this.liczbaOrganizmow = szerokosc * wysokosc / 10;
        this.random = new Random();
    }


    public void uruchom() {
        Scanner scanner = new Scanner(System.in); // TODO do usuniecia gdy pojawi sie gracz w rozgrywce
        stworzOrganizmy();
        while(czyKontynuowacRozgrywke()){
            rysujSwiat();
            scanner.nextLine(); // TODO do usuniecia gdy pojawi sie gracz w rozgrywce
            wykonajTure();
        }
        System.out.println("Gra zakonczona");
    }

    private void wykonajTure() {

        // dla wszystkich organizmow chcemy wywolac akcje
        // jesli w trakcie akcji dojdzie do kolizji, to chcemy na danym organizmie wywolac
        // metode kolizja

        // Taki zapis da: ConcurrentModificationException przez dodawanie organizmow w trakcie przechodzenia po nich
        /*for(Organizm organizm : organizmy) {
            organizm.akcja();
        }*/

        for (int i = 0; i < organizmy.size(); i++) {
            Organizm organizm = organizmy.get(i);
            if(organizm.czyZyje()) {
                organizm.akcja();
            }
        }

        organizmy = organizmy.stream()
                .filter(Organizm::czyZyje) // to samo co: organizm -> organizm.czyZyje()
                .collect(Collectors.toList());
    }

    private void rysujSwiat(){
        final int dlugosc = 3;
        for(int i = -1; i < wysokosc; i++){
            for(int j = -1; j < szerokosc; j++){
                if (i == -1 && j == -1) {
                    System.out.print(Utility.uzupelnijSpacjami("\\", dlugosc));
                } else if(i == -1){
                    System.out.print(Utility.uzupelnijSpacjami(j,dlugosc));
                } else if(j == -1){
                    System.out.print(Utility.uzupelnijSpacjami(i,dlugosc));
                } else if(plansza[i][j] != null){
                    System.out.print(Utility.uzupelnijSpacjami(plansza[i][j].getSymbol(),dlugosc));
                } else {
                    System.out.print(Utility.uzupelnijSpacjami(".",dlugosc));
                }
            }
            System.out.println();
        }
    }



    /**
     * Metoda tworzy organizmy na planszy na podstawie losowo wygenerowanych położeń. Ilość organizmów zależna jest
     * od wielkości i szerokości planszy. W tej chwili wspierane są dwa rodzaje organizmów: wilk i trawa.
     */
    // TODO mozemy tworzyc organizmy korzystajac z metody stworz i wywolywac ja na jakims zbiorze organizmow zamknietych w tablicy/liscie
    // po dodaniu wspolnego konstruktora ta metoda jest nieco trudniejsza do napisania
    private void stworzOrganizmy() {
        int liczbaUnikalnychOrganizmow = 2;

        // przyklad
        /*List<Organizm> organizmyDoKopiowania = List.of(
                new Wilk(null, this),
                new Trawa(null, this));*/

        for(int i = 0; i < liczbaOrganizmow; i++){
            Polozenie polozenie = losujPrawidlowePolozenie();
            if (i < liczbaOrganizmow/liczbaUnikalnychOrganizmow) {
                new Wilk(polozenie, this); // nie potrzebujemy tego obiektu po utworzeniu bo jest od razu przypisywany do planszy
            } else {
                new Trawa(polozenie, this);
            }
        }


    }

    /**
     * Sprawdza czy rozgrywka powinna byc kontynuowana.
     * TODO Docelowo metoda powinna weryfikowac czy gracz zyje
     * @return boolean
     */
    private boolean czyKontynuowacRozgrywke() {

        return true;
    }

    /**
     * Losuje polozenie uwzgledniajac istniejace na planszy organizmy.
     * @return wylosowane prawidlowe polozenie.
     */
    private Polozenie losujPrawidlowePolozenie(){
        Polozenie losowePolozenie;
        int x,y;
        do {
            x = random.nextInt(this.szerokosc);
            y = random.nextInt(this.wysokosc);
            losowePolozenie = new Polozenie(x, y);
        } while (plansza[y][x] != null);

        return losowePolozenie;
    }

    /**
     * TODO do opisania przypadek z polozeniem poza plansza
     * @param polozenie polozenie do sprawdzenia
     * @return boolean
     */
    public boolean czyPozycjaWolna(Polozenie polozenie) {
        if (!polozenie.czyPoprawne(szerokosc, wysokosc)) {
            return false; // mogloby byc rzucenie wyjatku w przyszlosci
        }
        return getOrganizm(polozenie) == null;
    }

    /**
     * Dodaje organizm do listy organizmow oraz do planszy.
     * Nadpisuje to co znajdowalo sie w danym miejscu planszy.
     * @param organizm dodawany organizm.
     */
    public void dodajOrganizm(Organizm organizm) {
        organizmy.add(organizm);
        dodajOrganizmDoPlanszy(organizm);
    }

    public Organizm[][] getPlansza() {
        return plansza;
    }

    public Organizm getOrganizm(@NotNull Polozenie polozenie) {
        return plansza[polozenie.getY()][polozenie.getX()];
    }

    /**
     * Dodaje organizmy jedynie na planszy nie zmieniajac
     * przy tym listy organizmow.
     * @param organizm organizm
     */
    public void dodajOrganizmDoPlanszy(@NotNull Organizm organizm) {
        Polozenie polozenie = organizm.getPolozenie();
        plansza[polozenie.getY()][polozenie.getX()] = organizm;
    }

    public int getWysokosc() {
        return wysokosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }
}
