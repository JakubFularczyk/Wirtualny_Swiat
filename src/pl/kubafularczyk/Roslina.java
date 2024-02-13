package pl.kubafularczyk;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Roslina extends Organizm {

    public Roslina(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        inicjatywa = 0;
    }


    @Override
    protected void akcja() {
        if(!czyZasieje()) {
            return;
        }
        Polozenie polozenieNowejRosliny;
        Set<Kierunek> kierunki = new HashSet<>();
        boolean brakDostepnychKierunkow;
        do {
            Kierunek kierunek = Kierunek.losuj();
            polozenieNowejRosliny = polozenie.stworzPrzesunietaKopie(kierunek);
            kierunki.add(kierunek);
            brakDostepnychKierunkow = kierunki.size() == Kierunek.values().length;
        } while(!swiat.czyPozycjaWolna(polozenieNowejRosliny) && !brakDostepnychKierunkow);
        if (brakDostepnychKierunkow) {
            // nie ma dostepnych kierunkow wiec nie wykonujemy rozsiewania
            return; // jesli mielibysmy obiekt komentatora, to moglby to skomentowac
        }
        Organizm organizm = stworz(polozenieNowejRosliny, swiat);
        swiat.dodajOrganizm(organizm);

    }

    protected boolean czyZasieje(){
        Random random = new Random();
        int szansaNaZasianie = random.nextInt(10);
        return szansaNaZasianie == 0;
    }

}
