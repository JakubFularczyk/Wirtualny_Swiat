package pl.kubafularczyk;

import org.jetbrains.annotations.NotNull;
import pl.kubafularczyk.exceptions.NiedozwolonyKierunekException;
import pl.kubafularczyk.organizmy.FabrykaOrganizmow;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;
import pl.kubafularczyk.organizmy.zwierzeta.czlowiek.Czlowiek;
import pl.kubafularczyk.utils.ParametryStartowe;
import pl.kubafularczyk.utils.Utility;
import pl.kubafularczyk.utils.Komentator;
import pl.kubafularczyk.nawigacja.Polozenie;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.stream.Collectors;

public class Swiat {
    private List<Organizm> organizmy;
    public Komentator komentator = new Komentator();
    // TODO pod koniec projektu: sprobowac wyrzucic bezposrednie zmienianie planszy przez organizmy i zastapic budowaniem planszy na podstawie listy
    private Organizm[][] plansza;
    private int wysokosc;
    private int szerokosc;
    private int liczbaOrganizmow;
    private List<TypOrganizmu> typyOrganizmow;
    private final Random random;
    private final ParametryStartowe parametry;

    public Swiat(ParametryStartowe parametry) {
        this.parametry = parametry;
        this.szerokosc = parametry.getSzerokosc();
        this.wysokosc = parametry.getWysokosc();
        this.liczbaOrganizmow = parametry.getLiczbaOrganizmow();
        this.typyOrganizmow = parametry.getTypyOrganizmow();
        this.organizmy  = new ArrayList<>();
        this.plansza = new Organizm[wysokosc][szerokosc];
        this.random = new Random();
        wyczyscLog();
    }

    public void uruchom() {
        stworzOrganizmy();
        while(czyKontynuowacRozgrywke()){
            rysujSwiat();
            try {
                wykonajTure();
            } catch (NiedozwolonyKierunekException e) {
                System.out.println("Wybrano niedozwolony kierunek. " + e.getMessage());
            }
        }
        System.out.println("Gra zakonczona");
    }

    private void posortujOrganizmyPoInicjatywie(){
        for (int j = 0; j < organizmy.size(); j++) {
            for (int i = 0; i < organizmy.size() - 1; i++) {
                Organizm organizm1 = organizmy.get(i);
                Organizm organizm2 = organizmy.get(i + 1);
                if (organizm1.getInicjatywa() < organizm2.getInicjatywa()) {
                    organizmy.set(i + 1, organizm1);
                    organizmy.set(i, organizm2);
                }
            }
        }
    }

    private void wykonajTure() {
        Scanner scanner = new Scanner(System.in);
        Optional<Czlowiek> czlowiek = wyszukajCzlowieka();
        czlowiek.ifPresentOrElse(Czlowiek::pobierzKolejnyRuch, scanner::nextLine);

        posortujOrganizmyPoInicjatywie();
        for (int i = 0; i < organizmy.size(); i++) {
            Organizm organizm = organizmy.get(i);
            if(organizm.czyZyje() && !organizm.czyWykonalRuch()) {
                organizm.akcja();
            }
        }

        organizmy = organizmy.stream()
                .filter(Organizm::czyZyje) // to samo co: organizm -> organizm.czyZyje()
                .peek(Organizm::wlaczRuch)
                .collect(Collectors.toList());
    }

    private void rysujSwiat(){
        final int dlugosc = 3;
        for(int i = -1; i < wysokosc; i++){
            for(int j = -1; j < szerokosc; j++){
                String tekst;
                if (i == -1 && j == -1) {
                    tekst = "\\";
                } else if(i == -1){
                    tekst = j + "";
                } else if(j == -1){
                    tekst = i + "";
                } else if(plansza[i][j] != null){
                    tekst = plansza[i][j].getSymbol();
                } else {
                   tekst = ".";
                }
                String symbol = Utility.uzupelnijSpacjami(tekst, dlugosc);
                System.out.print(symbol);
                Komentator.zapisDoPliku(symbol);
            }
            System.out.println();
            Komentator.zapisDoPliku("\n");

        }
    }


    /**
     * Metoda tworzy organizmy na planszy na podstawie losowo wygenerowanych położeń. Ilość organizmów zależna jest
     * od wielkości i szerokości planszy.
     */
    // TODO mozemy tworzyc organizmy korzystajac z metody stworz i wywolywac ja na jakims zbiorze organizmow zamknietych w tablicy/liscie
    // po dodaniu wspolnego konstruktora ta metoda jest nieco trudniejsza do napisania
    private void stworzOrganizmy() {
        for (TypOrganizmu typ : typyOrganizmow) {
            if (typ == TypOrganizmu.CZLOWIEK) {
                continue;
            }
            for (int i = 0; i < liczbaOrganizmow; i++) {
                Polozenie polozenie = losujPrawidlowePolozenie();
                FabrykaOrganizmow.stworz(typ, polozenie, this);
            }
        }
        Polozenie polozenieCzlowieka = losujPrawidlowePolozenie();
        if(typyOrganizmow.contains(TypOrganizmu.CZLOWIEK)) {
            FabrykaOrganizmow.stworz(TypOrganizmu.CZLOWIEK, polozenieCzlowieka, this);
        }
    }

    /**
     * Sprawdza czy rozgrywka powinna byc kontynuowana.
     * @return boolean
     */
    private boolean czyKontynuowacRozgrywke() {

        /*return organizmy.stream() //
            .map(Organizm::getTyp) //
            .filter(TypOrganizmu.CZLOWIEK::equals) //
            .count() == 1;*/

        /*return organizmy.stream() //
            .filter(o -> TypOrganizmu.CZLOWIEK.equals(o.getTyp())) //
            .count() == 1;*/

        return !typyOrganizmow.contains(TypOrganizmu.CZLOWIEK) || organizmy.stream() //
            .anyMatch(o -> TypOrganizmu.CZLOWIEK.equals(o.getTyp()));

        /*for(Organizm organizm: organizmy) {
            if(TypOrganizmu.CZLOWIEK.equals(organizm.getTyp())) {
                return true;
            }
        }
        return false;*/
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
     * Sprawdza czy polozenie nie wychodzi poza obszar planszy.
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

    private void wyczyscLog() {
        try (RandomAccessFile file = new RandomAccessFile(Utility.getLogFileName(), "rw")) { // tryb rw - read/write
            file.setLength(0); // ustawienie dlugosci pliku na 0 czyli wyczyszczenie go
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Polozenie> getPolozeniaOrganizmow(@NotNull TypOrganizmu typ) {

        // zapis lambd do zmiennej - czesc programowania funkcyjnego
        // Function<Organizm, Integer> organizmIntegerFunction = (Organizm organizm) -> organizm.getSila();
        // Predicate<Organizm> organizmFilter = (Organizm organizm) -> organizm.czyZyje();

        return organizmy.stream() //
                        .filter(organizm -> organizm.getTyp().equals(typ)) //
                        .map(Organizm::getPolozenie) // odpowiednik organizm -> organizm.getPolozenie()
                        .collect(Collectors.toList());

    }

    public Organizm[][] getPlansza() {
        return plansza;
    }
    /**
     * Pobiera organizm z planszy z konkretnego  polozenia
     * @param polozenie polozenie
     */
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

    private Optional<Czlowiek> wyszukajCzlowieka() {
        for (Organizm organizm : organizmy) {
            if (TypOrganizmu.CZLOWIEK.equals(organizm.getTyp())) {
                return Optional.of((Czlowiek)organizm);
            }
        }
        return Optional.empty();
    }


    public int getWysokosc() {
        return wysokosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }
}
