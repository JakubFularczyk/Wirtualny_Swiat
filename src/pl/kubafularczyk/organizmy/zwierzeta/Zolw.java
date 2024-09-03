package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;

import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.sila = 2;
        this.inicjatywa = 1;
        this.typ = TypOrganizmu.ZOLW;
    }

    @Override
    public void akcja() {

        Random random = new Random();
        int szansaNaRuch = random.nextInt(4);
        if(szansaNaRuch == 0) {
            super.akcja();
        }
    }
    //TODO poprawic kolizje, aktualnie zolw podczas ataku nie atakuje organizmow ponizej 5 sily

    @Override
    protected void bronSie(Organizm atakujacyOrganizm) {
        if(atakujacyOrganizm.getSila() < 5) {

        } else {
        }
    }
}
