package pl.kubafularczyk;

public class Komentator {

    // TODO zmiana sposobu wyswietlania tak by zamiast na ekran zapisywalo do pliku
    // chodzi o to zeby szczegolowe informacje np. ruch skad dokad byly w pliku
    // do zastanowienia czy do pliku zapisywac tez plansze czy tylko same operacje
    // ogolne informacje moga byc dalej wypisywane na ekran (np. wystapienie kolizji)

    public static void brakKierunkowZasiania(Polozenie polozenie,Organizm organizm){
        System.out.println(organizm + " na polozeniu " + polozenie + " nie ma gdzie zasiac!" );
    }
    public static void usmierceniePostaci(Organizm przezywajacy, Organizm umierajacy){
        System.out.println("Przy kolizji umarl/a " + umierajacy + " jednoczesnie wygral/a potyczke " + przezywajacy + " !");
    }
    public static void kolizjaPostaci(Polozenie polozenie, Organizm organizm){
        System.out.println("Kolizja na pozycji: atakujacy: " + polozenie + " atakowany: " + organizm.polozenie);
    }

    public static void rozmnozenieZwierzecia(Zwierze zwierze, Organizm atakowanyOrganizm, Polozenie nowePolozenie) {
        System.out.println("Rozmnozenie zwierzat na pozycji " + zwierze.getPolozenie() + " i " + atakowanyOrganizm.getPolozenie() + ". Nowy organizm w polozeniu: " + nowePolozenie);
    }

    // TODO** wyswietlanie komentarzy po prawej stronie planszy, numerowane od 1, na zasadzie dodatkowych kolumn
    // musialaby byc jakas maksymalna szerokosc kolumny
}

