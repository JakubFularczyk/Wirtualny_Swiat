package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.exceptions.BrakWolnegoPolozeniaException;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Czlowiek extends Zwierze {

    public Czlowiek(Polozenie polozenie, Swiat swiat){
        super(polozenie, swiat);
        this.sila = 5;
        this.inicjatywa = 4;
        this.typ = TypOrganizmu.CZLOWIEK;
    }

    @Override
    public void akcja() {
        super.akcja();
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        super.kolizja(atakowanyOrganizm);
    }

    public void pobierzKolejnyRuch() {
      // pobranie ruchu musi miec wplyw na pozniejsze losowanie pozycji
    }

    @Override
    protected Polozenie losowaniePolozenia() {

        return null;
    }
}
