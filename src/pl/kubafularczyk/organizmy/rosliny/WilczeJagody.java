package pl.kubafularczyk.organizmy.rosliny;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.TypOrganizmu;

import java.util.Random;

public class WilczeJagody extends Roslina{
    public WilczeJagody(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.sila = 99;
        this.typ = TypOrganizmu.WILCZE_JAGODY;
    }

    @Override
    protected boolean czyZasieje() {
        Random random = new Random();
        int szansaNaZasianie = random.nextInt(100);
        return szansaNaZasianie == 0;
    }
}
