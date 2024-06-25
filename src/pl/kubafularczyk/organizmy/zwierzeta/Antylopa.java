package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Antylopa extends Zwierze {

    private static final int zasiegRuchu = 2;


    public Antylopa(Polozenie polozenie, Swiat swiat){
        super(polozenie,swiat);
        this.inicjatywa = 4;
        this.sila = 4;
        this.typ = TypOrganizmu.ANTYLOPA;
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
    protected void bronSie(Organizm atakujacyOrganizm) {
        // TODO ucieczka
        super.bronSie(atakujacyOrganizm);
    }
}
