package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Owca extends Zwierze {

    public Owca(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.sila = 4;
        this.inicjatywa = 4;
        this.typ = TypOrganizmu.OWCA;
    }

}
