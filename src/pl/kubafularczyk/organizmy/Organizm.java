package pl.kubafularczyk.organizmy;


import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.exceptions.BrakWolnegoPolozeniaException;
import pl.kubafularczyk.nawigacja.Kierunek;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.zwierzeta.Zwierze;
import pl.kubafularczyk.utils.Komentator;

import java.util.HashSet;
import java.util.Set;

public abstract class Organizm {

    private boolean zyje;
    protected int sila;
    protected int inicjatywa;
    protected Polozenie polozenie;
    protected Swiat swiat;
    protected int wiek;
    protected TypOrganizmu typ;
    protected boolean wykonalRuch;

    public Organizm(Polozenie polozenie, Swiat swiat) {
        this.polozenie = polozenie;
        this.swiat = swiat;
        this.zyje = true;
        this.wiek = 0;
        this.wykonalRuch = false;
        swiat.dodajOrganizm(this);
    }

    public void akcja() {
        this.wiek++;
        this.wykonalRuch = true;
    }

    protected abstract void kolizja(Organizm atakowanyOrganizm);

    public String getSymbol(){
        return typ.getSymbol();
    }

    public int getInicjatywa(){
        return inicjatywa;
    }
    public TypOrganizmu getTyp() {
        return typ;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public int getSila() {
        return sila;
    }

    protected void setSila(int sila) {
        this.sila = sila;
    }

    public boolean czyZyje() {
        return zyje;
    }

    public void zabij() {
        zyje = false;
        Organizm[][] plansza = swiat.getPlansza();
        plansza[polozenie.getY()][polozenie.getX()] = null;
    }

    @Override
    public String toString() {
        return "typ=" + typ + " " + polozenie;
    }

    public boolean czyWykonalRuch() {
        return wykonalRuch;
    }

    public void wylaczRuch() {
        this.wykonalRuch = true;
    }
    public void wlaczRuch() {
        this.wykonalRuch = false;
    }

    protected Polozenie losowaniePolozenia() throws BrakWolnegoPolozeniaException {
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        do {
            Kierunek nowyKierunek = wybierzKierunek();
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
        } while (polozenieNiepoprawne);
        return nowePolozenie;
    }

    /**
     * Metoda pobierajaca kierunek nowego ruchu.
     * Wydzielona jako osobna metoda "wrapper" dla Kierunek.losuj() aby zapewnić możliwość nadpisania jej.
     * @return wybrany kierunek.
     */
    protected Kierunek wybierzKierunek() {
        return Kierunek.losuj();
    }

    protected Polozenie losowanieWolnegoPolozenia() throws BrakWolnegoPolozeniaException {
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        boolean polozenieZajete;
        Set<Kierunek> wykorzystaneKierunki = new HashSet<>();
        do {
            polozenieZajete = false;
            if(wykorzystaneKierunki.size() == Kierunek.values().length) {
                throw new BrakWolnegoPolozeniaException();
            }
            Kierunek nowyKierunek = Kierunek.losuj();
            wykorzystaneKierunki.add(nowyKierunek);
            nowePolozenie = polozenie.stworzPrzesunietaKopie(nowyKierunek);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
            if (polozenieNiepoprawne) {
                continue;
            }
            polozenieZajete = czyPolozenieZajete(nowePolozenie);
        } while (polozenieNiepoprawne || polozenieZajete);
        return nowePolozenie;
    }

    protected boolean czyPolozenieZajete(Polozenie polozenie) {
        return !polozenie.czyWolne(swiat.getPlansza());
    }

    /**
     * Metoda reprezentujaca walke z innym organizmem. Organizm o wiekszej sile wygrywa walke i zabija przeciwnika.
     * Nastepnie przemieszcza się na jego pozycje.
     * @param atakowanyOrganizm Organizm.
     */
    public void walczZ(Organizm atakowanyOrganizm) {
        WalczacaPara walczacaPara = stworzWalczacaPare(atakowanyOrganizm);
        Komentator.kolizjaPostaci(this, atakowanyOrganizm);
        atakowanyOrganizm.bronSie(this); // moze bronSie powinno zwracac jakas wartosc zeby nie triggerowac przeniesienia organizmu
        przeniesOrganizmNaMiejsceSlabszego(walczacaPara, swiat.getPlansza());
    }

    /**
     * Metoda opisujaca zachowanie organizmu w trakcie bycia atakowanym przez inny organizm.</br>
     * <b>Domyslnie organizm nie broni się.</b>
     * @param atakujacyOrganizm
     */
    protected void bronSie(Organizm atakujacyOrganizm) { }

    /**
     * Metoda tworząca "parę" zwierząt ze sobą walczących.
     * Metoda ustawia również które z zwierzat jest tym silniejszym ze wzgledu na porownanie sily zwierzat.
     * @param atakowanyOrganizm Organizm.
     */
    protected WalczacaPara stworzWalczacaPare(Organizm atakowanyOrganizm) {
        if(this.sila > atakowanyOrganizm.getSila()){
            return new WalczacaPara(atakowanyOrganizm, this);
        } else {
            return new WalczacaPara(this, atakowanyOrganizm);
        }
    }

    /**
     * Zabija slabszy organizm i przemieszcza silniejszy na pole slabszego jeśli silniejszy jest zwierzęciem.
     * Aktualizuje stare polozenie na planszy.
     * @param walczacaPara walczaca para
     * @param plansza Organizm[][]
     */
    protected void przeniesOrganizmNaMiejsceSlabszego(WalczacaPara walczacaPara, Organizm[][] plansza){
        Organizm silniejszyOrganizm = walczacaPara.getSilniejszyOrganizm();
        Organizm slabszyOrganizm = walczacaPara.getSlabszyOrganizm();
        // TODO jesli atakowany organizm umie sie bronic, to przeniesienie organizmu niekoniecznie musi zabijac slabszy!
        // ^ zwiazane z bronSie
        Komentator.usmierceniePostaci(silniejszyOrganizm, slabszyOrganizm);
        slabszyOrganizm.zabij();
        if (silniejszyOrganizm instanceof Zwierze) {
            przeniesOrganizm(silniejszyOrganizm, slabszyOrganizm.getPolozenie(), plansza);
        }
    }

    protected void przeniesOrganizm(Organizm organizm, Polozenie docelowePolozenie, Organizm[][] plansza){
        Polozenie starePolozenie = polozenie;
        polozenie = docelowePolozenie;
        swiat.dodajOrganizmDoPlanszy(organizm);
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;
    }

    public void zwiekszSile(int wartosc) {
        this.setSila(this.getSila()+wartosc);
    }

}
