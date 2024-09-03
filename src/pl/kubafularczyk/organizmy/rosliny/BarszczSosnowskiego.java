package pl.kubafularczyk.organizmy.rosliny;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.nawigacja.Kierunek;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;
import pl.kubafularczyk.organizmy.WalczacaPara;
import pl.kubafularczyk.organizmy.zwierzeta.Zwierze;



import java.util.List;

public class BarszczSosnowskiego extends Roslina {

    public BarszczSosnowskiego(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.sila = 10;
        this.typ = TypOrganizmu.BARSZCZ_SOSNOWSKIEGO;
    }

    @Override
    public void akcja(){
        super.akcja();
        //biore polozenie wlasne
        //sprawdzam wszystkie pola dookola mnie
        //zabijam wszystkie zwierzeta dookola mnie

        List<Polozenie> polozenia = polozenie.pobierzPolozeniaDookola(this.polozenie, this.swiat);
        for(Polozenie polozenie : polozenia) {
            Organizm organizm = swiat.getOrganizm(polozenie);
            if(organizm instanceof Zwierze && organizm.getTyp() != TypOrganizmu.CYBER_OWCA) {
                organizm.zabij();
            }
        }
    }


    @Override
    protected WalczacaPara stworzWalczacaPare(Organizm atakowanyOrganizm) {
        if(!TypOrganizmu.CYBER_OWCA.equals(atakowanyOrganizm.getTyp())){
            return new WalczacaPara(atakowanyOrganizm, this);
        } else {
            return new WalczacaPara(this, atakowanyOrganizm);
        }
    }
}
