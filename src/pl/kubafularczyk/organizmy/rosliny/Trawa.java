package pl.kubafularczyk.organizmy.rosliny;

import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Trawa extends Roslina {

    public Trawa(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.typ = TypOrganizmu.TRAWA;
    }
}
