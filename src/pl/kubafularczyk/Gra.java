package pl.kubafularczyk;

import pl.kubafularczyk.utils.ParametryStartowe;

import java.util.Scanner;

public class Gra {

    public void start() {
        stworzSwiat();
    }

    // TODO do zastanowienia - czy warto przeniesc te metode do swiata
    private void stworzSwiat(){

        ParametryStartowe parametry = pobierzParametryStartowe();
        Swiat swiat = new Swiat(parametry);
        swiat.uruchom();
    }

    ParametryStartowe pobierzParametryStartowe() {
        ParametryStartowe parametry = new ParametryStartowe();
        parametry.pobierz();
        return parametry;
    }

}
