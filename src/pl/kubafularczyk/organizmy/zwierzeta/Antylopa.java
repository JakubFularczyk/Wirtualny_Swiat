package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.exceptions.BrakWolnegoPolozeniaException;
import pl.kubafularczyk.nawigacja.Kierunek;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;
import pl.kubafularczyk.utils.Komentator;

import java.util.Random;
import java.util.function.BiFunction;

public class Antylopa extends Zwierze {

    private int dlugoscKroku;

    public Antylopa(Polozenie polozenie, Swiat swiat){
        super(polozenie,swiat);
        this.inicjatywa = 4;
        this.sila = 4;
        this.typ = TypOrganizmu.ANTYLOPA;
        this.dlugoscKroku = 2;
    }

    @Override
    public void akcja() {
        super.akcja();
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        super.kolizja(atakowanyOrganizm);
    }

    @Override
    protected Polozenie losowaniePolozenia() throws BrakWolnegoPolozeniaException {
        Polozenie nowePolozenie;
        boolean polozenieNiepoprawne;
        do {
            Kierunek nowyKierunek = wybierzKierunek();
            BiFunction<Kierunek, Integer, Polozenie> funkcjaPrzesuwajaca = getFunkcjaPrzesuwajaca();
            nowePolozenie = funkcjaPrzesuwajaca.apply(nowyKierunek, dlugoscKroku);
            polozenieNiepoprawne = !nowePolozenie.czyPoprawne(swiat.getSzerokosc(), swiat.getWysokosc());
        } while (polozenieNiepoprawne);
        return nowePolozenie;
    }

    BiFunction<Kierunek, Integer, Polozenie> getFunkcjaPrzesuwajaca() {
        return polozenie::stworzPrzesunietaKopie;
    }

    @Override
    protected void bronSie(Organizm atakujacyOrganizm) {
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            try {
                Polozenie nowePolozenie = losowanieWolnegoPolozenia();
                Polozenie polozeniePrzedUcieczka = atakujacyOrganizm.getPolozenie();
                Komentator.ucieczkaOdAtaku(this, atakujacyOrganizm);
                przeniesOrganizm(atakujacyOrganizm, nowePolozenie, swiat.getPlansza());
                przeniesOrganizm(this, polozeniePrzedUcieczka, swiat.getPlansza());
            } catch (BrakWolnegoPolozeniaException e) {
                e.printStackTrace();
            }
        }
        walczZ(atakujacyOrganizm);
    }
}
