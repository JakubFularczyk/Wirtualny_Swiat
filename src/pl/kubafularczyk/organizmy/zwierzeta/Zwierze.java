package pl.kubafularczyk.organizmy.zwierzeta;


import pl.kubafularczyk.exceptions.BrakWolnegoPolozeniaException;
import pl.kubafularczyk.organizmy.FabrykaOrganizmow;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.TypOrganizmu;
import pl.kubafularczyk.organizmy.WalczacaPara;
import pl.kubafularczyk.organizmy.rosliny.BarszczSosnowskiego;
import pl.kubafularczyk.utils.Komentator;
import pl.kubafularczyk.nawigacja.Polozenie;

import java.util.Random;

public abstract class Zwierze extends Organizm {


    public Zwierze(Polozenie polozenie, Swiat swiat) {
        super(polozenie,swiat);
    }

    /**
     * Metoda odpowiedzialna za wykonanie akcji przemieszczenia organizmu
     * Akcja polega na wybraniu losowego kierunku (góra, dół, lewo, prawo) i zmianie położenia organizmu na planszy
     * z uwzględnieniem ograniczeń wynikających z rozmiarów planszy. Proces wyboru nowego położenia
     * jest powtarzany, dopóki nie zostanie znalezione położenie poprawne czyli takie które miesci się w granicach planszy
     * Po wybraniu poprawnego nowego położenia organizm jest przemieszczany na planszy.
     * Jego poprzednia pozycja jest czyszczona (ustawiana na null), a na nowej pozycji ustawiany jest organizm
     */
    @Override
    public void akcja() {

        super.akcja();
        Organizm[][] plansza = swiat.getPlansza();
        Polozenie nowePolozenie;
        try {
            nowePolozenie = losowaniePolozenia();
        } catch (BrakWolnegoPolozeniaException e) {
            Komentator.brakMiejscaRuchu(this);
            return;
        }
        Organizm organizm = swiat.getOrganizm(nowePolozenie);
        if(organizm != null) {
            kolizja(organizm);
        } else {
            przeniesOrganizm(this, nowePolozenie, plansza);
        }
    }

    /**
     * Metoda obsługujaca kolizje z innym organizmem. Jeśli typ tego organizmu jest taki sam jak atakowanego organizmu,
     * to organizmy się rozmnażaja. W przeciwnym razie walcza.
     *
     * @param atakowanyOrganizm Organizm.
     */
    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        Random random = new Random();
        /*if (this.getTyp().equals(atakowanyOrganizm.getTyp())) {
            rozmnoz(atakowanyOrganizm);
        } else if (TypOrganizmu.BARSZCZ_SOSNOWSKIEGO.equals(atakowanyOrganizm.getTyp())) {
            //BarszczSosnowskiego atakowanyBarszcz = (BarszczSosnowskiego) atakowanyOrganizm;
            //atakowanyOrganizm.walczZ(this);
            walczZ(atakowanyOrganizm);
        } else if (TypOrganizmu.GUARANA.equals(atakowanyOrganizm.getTyp())) {
            dodajSile(this);
            walczZ(atakowanyOrganizm);
        } else if (TypOrganizmu.ANTYLOPA.equals(atakowanyOrganizm.getTyp())){
            if(random.nextInt(100) < 50) {
                try {
                    losowanieWolnegoPolozenia();
                } catch (BrakWolnegoPolozeniaException e) {
                    e.printStackTrace();
                }
            }
        } else {
            walczZ(atakowanyOrganizm);
        }*/

        if (this.getTyp().equals(atakowanyOrganizm.getTyp())) {
            rozmnoz(atakowanyOrganizm);
            return;
        } else if (TypOrganizmu.ANTYLOPA.equals(atakowanyOrganizm.getTyp())) {
            if (random.nextInt(100) < 50) {
                try {
                    Polozenie nowePolozenie = losowanieWolnegoPolozenia();
                    Polozenie polozeniePrzedUcieczka = atakowanyOrganizm.getPolozenie();
                    Komentator.ucieczkaOdAtaku(this, atakowanyOrganizm);
                    przeniesOrganizm(atakowanyOrganizm, nowePolozenie, swiat.getPlansza());
                    przeniesOrganizm(this, polozeniePrzedUcieczka, swiat.getPlansza());
                    return;
                } catch (BrakWolnegoPolozeniaException e) {
                    e.printStackTrace();
                }
            }
        }
        walczZ(atakowanyOrganizm);
    }



    /**
     * Metoda sluzaca do rozmnazania organizmu. Losuje wolne miejsce na planszy dla potomka. Jesli nie ma dostępnych wolnych miejsc
     * wypisuje komunikat o braku miejsca i konczy metode.
     * W przeciwnym razie tworzy potomka na nowym losowym polozeniu na planszy.
     * @param organizm Organizm.
     */
    protected void rozmnoz(Organizm organizm){
        Polozenie nowePolozenie;
        try {
            nowePolozenie = losowanieWolnegoPolozenia();
        } catch (BrakWolnegoPolozeniaException e) {
            System.out.println("Zwierze nie ma sie gdzie rozmnozyc");
            return;
        }
        Komentator.rozmnozenieZwierzecia(this, organizm, nowePolozenie);
        FabrykaOrganizmow.stworz(this.getTyp(), nowePolozenie, swiat, true);
    }


}
