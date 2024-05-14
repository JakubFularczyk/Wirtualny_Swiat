package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.exceptions.BrakWolnegoPolozeniaException;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.TypOrganizmu;


public class Lis extends Zwierze {

    public Lis(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.inicjatywa = 7;
        this.sila = 3;
        this.typ = TypOrganizmu.LIS;
    }

    @Override
    public void akcja() {
        super.akcja();
        /*Organizm[][] plansza = swiat.getPlansza();
        Polozenie nowePolozenie;
        try {
            nowePolozenie = losowanieWolnegoPolozenia();
        } catch (BrakWolnegoPolozeniaException e) {
            Komentator.brakMiejscaRuchu();
            return;
        }
        Polozenie starePolozenie = polozenie;
        polozenie = nowePolozenie;
        swiat.dodajOrganizmDoPlanszy(this);
        plansza[starePolozenie.getY()][starePolozenie.getX()] = null;*/

        // czym rozni sie akcja lisa?
        // losujemy polozenie takie, gdzie nie ma silniejszego organizmu
        // jesli polozenia brak to nie ruszamy sie wcale

        // czym rozni sie kolizja lisa
        // niczym
    }

    @Override
    protected Polozenie losowaniePolozenia() throws BrakWolnegoPolozeniaException {
        return losowanieWolnegoPolozenia();
    }

    @Override
    protected boolean czyPolozenieZajete(Polozenie polozenie) {
        return czyPolozenieZajetePrzezSilniejszyOrganizm(polozenie);
    }

    private boolean czyPolozenieZajetePrzezSilniejszyOrganizm(Polozenie polozenie) {
        boolean polozenieZajete = false;
        Organizm kolidujacyOrganizm = swiat.getOrganizm(polozenie);
        if (null == kolidujacyOrganizm || this.getTyp().equals(kolidujacyOrganizm.getTyp())) {
            polozenieZajete = false;
        } else if (kolidujacyOrganizm.getSila() > this.getSila()){
            polozenieZajete = true;
        }
        return polozenieZajete;
    }
}
