package pl.kubafularczyk.organizmy.rosliny;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Guarana extends Roslina{

    public Guarana(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.typ = TypOrganizmu.GUARANA;
    }

    @Override
    public void akcja() {
        super.akcja();
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        super.kolizja(atakowanyOrganizm);
    }
}
