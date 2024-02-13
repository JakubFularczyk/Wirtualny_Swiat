package pl.kubafularczyk;

public class Komentator {



    public void brakKierunkowZasiania(Polozenie polozenie,Organizm organizm){
        System.out.println(organizm + " na polozeniu " + polozenie + " nie ma gdzie zasiac!" );
    }
    public void usmierceniePostaci(Organizm przezywajacy, Organizm umierajacy){
        System.out.println("Przy kolizji umarl/a " + umierajacy + " jednoczesnie wygral/a potyczke " + przezywajacy + " !");
    }
    public void kolizjaPostaci(Polozenie polozenie, Organizm organizm){
        System.out.println("Kolizja na pozycji: atakujacy: " + polozenie + " atakowany: " + organizm.polozenie);
    }

}
