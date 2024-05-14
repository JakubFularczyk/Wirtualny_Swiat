package pl.kubafularczyk.organizmy.rosliny;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.TypOrganizmu;

import java.util.Random;

public class Mlecz extends Roslina{
    public Mlecz(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.typ = TypOrganizmu.MLECZ;
    }

    @Override
    protected boolean czyZasieje() {
//        boolean czyZasialo = false;
//        for(int i = 0; i < 3; i++){
//            czyZasialo |= super.czyZasieje();
//        }
//        return czyZasialo;
        return super.czyZasieje() || super.czyZasieje() || super.czyZasieje();
    }
}
