package pl.kubafularczyk.organizmy.zwierzeta;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.exceptions.BrakWolnegoPolozeniaException;
import pl.kubafularczyk.exceptions.NiedozwolonyKierunekException;
import pl.kubafularczyk.nawigacja.Kierunek;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.TypOrganizmu;


import java.util.List;

public class CyberOwca extends Owca {

    public CyberOwca(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.sila = 11;
        this.inicjatywa = 4;
        this.typ = TypOrganizmu.CYBER_OWCA;
    }

    @Override
    protected Polozenie losowaniePolozenia() throws BrakWolnegoPolozeniaException {
        Polozenie polozenieBarszczu = znajdzNajblizszyBarszcz();
        if (null != polozenieBarszczu) {
            return stworzPolozenieWKierunkuBarszczu(polozenieBarszczu);
        }
        return super.losowaniePolozenia();
    }

    private Polozenie znajdzNajblizszyBarszcz() {
        List<Polozenie> polozeniaBarszczy = swiat.getPolozeniaOrganizmow(TypOrganizmu.BARSZCZ_SOSNOWSKIEGO);
        Polozenie polozenieNajblizszegoBarszczu = null;
        double najmniejszaOdleglosc = Double.MAX_VALUE;
        for(Polozenie polozenieBarszczu : polozeniaBarszczy) {
            double odleglosc = obliczOdleglosc(polozenieBarszczu);
            if(odleglosc < najmniejszaOdleglosc) {
                najmniejszaOdleglosc = odleglosc;
                polozenieNajblizszegoBarszczu = polozenieBarszczu;
            }
        }
        return polozenieNajblizszegoBarszczu;
    }

    private double obliczOdleglosc(Polozenie polozenie) {
        double roznicaX = Math.abs(this.polozenie.getX() - polozenie.getX());
        double roznicaY = Math.abs(this.polozenie.getY() - polozenie.getY());
        return Math.sqrt(Math.pow(roznicaX, 2) + Math.pow(roznicaY,2));
    }

    private Polozenie stworzPolozenieWKierunkuBarszczu(Polozenie polozeniebarszczu) {
        Kierunek kierunek = znajdzKierunekWStroneBarszczu(polozeniebarszczu);
        System.out.println("CyberOwca: " + polozenie + " Barszcz: " + polozeniebarszczu + " Kierunek: " + kierunek.name());
        return polozenie.stworzPrzesunietaKopie(kierunek);
    }

    protected Kierunek znajdzKierunekWStroneBarszczu(Polozenie polozenieBarszczu)  {

        int kierunekX = Integer.compare(polozenieBarszczu.getX(), this.polozenie.getX());
        int kierunekY = Integer.compare(polozenieBarszczu.getY(), this.polozenie.getY());

        for (Kierunek kierunek : Kierunek.values()) {
            if (kierunek.getX() == kierunekX && kierunekX != 0) {
                return kierunek;
            }
            if (kierunek.getY() == kierunekY && kierunekY != 0) {
                return kierunek;
            }
        }
        Polozenie niepoprawnyKierunek = new Polozenie(kierunekX, kierunekY);
        throw new NiedozwolonyKierunekException("Niepoprawny kierunek dla owcy: " + niepoprawnyKierunek);
    }
}
