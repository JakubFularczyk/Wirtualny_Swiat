package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Antylopa extends Zwierze {

    public Antylopa(Polozenie polozenie, Swiat swiat){
        super(polozenie,swiat);
        this.inicjatywa = 4;
        this.sila = 4;
        this.typ = TypOrganizmu.ANTYLOPA;
    }
}
