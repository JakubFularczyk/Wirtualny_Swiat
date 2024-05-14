package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.TypOrganizmu;

public class Wilk extends Zwierze {

    public Wilk(Polozenie polozenie, Swiat swiat){
        super(polozenie,swiat);
        this.inicjatywa = 5;
        this.sila = 9;
        this.typ = TypOrganizmu.WILK;
    }

    @Override
    public void akcja(){
        super.akcja();
    }
}
