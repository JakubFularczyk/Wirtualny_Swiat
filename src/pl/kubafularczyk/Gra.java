package pl.kubafularczyk;

import java.util.Scanner;

public class Gra {

    public void start() {
        stworzSwiat();
    }

    // TODO do zastanowienia - czy warto przeniesc te metode do swiata
    private void stworzSwiat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wysokosc świata: ");
        int wysokoscSwiata = scanner.nextInt();
        System.out.println("Podaj szerokosc świata: ");
        int szerokoscSwiata = scanner.nextInt();

        Swiat swiat = new Swiat(wysokoscSwiata, szerokoscSwiata);
        swiat.uruchom();
    }

}
