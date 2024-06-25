package pl.kubafularczyk.utils;

import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.rosliny.Roslina;
import pl.kubafularczyk.organizmy.zwierzeta.Zwierze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Komentator {



    public static void zapisDoPliku(String komentarz) {
        // standardowy sposob, z recznym zamykaniem
        /*try {
            FileWriter fileWriter = new FileWriter("src/notepad/informacje.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(komentarz);
            bufferedWriter.write("\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.print("Nie udalo sie otworzyć pliku");
        }*/

        // automatycznie zamyka writery/readery
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Utility.getLogFileName(), true))) {
            bufferedWriter.write(komentarz);
        } catch (IOException e) {
            System.out.print("Nie udalo sie otworzyć pliku");
        }
    }

    public static void brakKierunkowZasiania(Roslina roslina){
        String komentarz = roslina + " na polozeniu " + roslina.getPolozenie() + " nie ma gdzie zasiac!\n";
        zapisDoPliku(komentarz);
    }

    // ogolne informacje moga byc dalej wypisywane na ekran (np. wystapienie kolizji)
    public static void usmierceniePostaci(Organizm przezywajacy, Organizm umierajacy){
        String komentarz = "Przy kolizji umarl/a " + umierajacy + " jednoczesnie wygral/a potyczke " + przezywajacy + " !\n";
        System.out.print(komentarz);
        zapisDoPliku(komentarz);
    }
    public static void kolizjaPostaci(Organizm atakujacy, Organizm atakowany){
        String komentarz = "Kolizja na pozycji: atakujacy: " + atakujacy + " atakowany: " + atakowany + "\n";
        System.out.print(komentarz);
        zapisDoPliku(komentarz);
    }

    public static void rozmnozenieZwierzecia(Zwierze zwierze, Organizm atakowanyOrganizm, Polozenie nowePolozenie) {
        String komentarz = "Rozmnozenie zwierzat na pozycji " + zwierze.getPolozenie() + " i " + atakowanyOrganizm.getPolozenie() +
                ". Nowy organizm w polozeniu: " + nowePolozenie + "\n";
        zapisDoPliku(komentarz);
    }

    public static void brakMiejscaRuchu(Zwierze zwierze) {
        String komentarz = zwierze + "nie ma sie gdzie poruszyc!\n";
        zapisDoPliku(komentarz);
    }

    public static void ucieczkaOdAtaku(Organizm atakujacy, Organizm atakowany) {
        String komentarz = "Ucieczka od ataku: atakujacy: " + atakujacy + " atakowany: " + atakowany + "\n";
        System.out.print(komentarz);
        zapisDoPliku(komentarz);
    }


    // TODO** wyswietlanie komentarzy po prawej stronie planszy, numerowane od 1, na zasadzie dodatkowych kolumn
    // musialaby byc jakas maksymalna szerokosc kolumny
}

