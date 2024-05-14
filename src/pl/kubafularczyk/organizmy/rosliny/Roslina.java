package pl.kubafularczyk.organizmy.rosliny;


import pl.kubafularczyk.organizmy.FabrykaOrganizmow;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.utils.Komentator;
import pl.kubafularczyk.nawigacja.Kierunek;
import pl.kubafularczyk.nawigacja.Polozenie;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Roslina extends Organizm {


    public Roslina(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        inicjatywa = 0;
    }


    @Override
    public void akcja() {

        super.akcja();
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
            Komentator.brakKierunkowZasiania(this);
            return;
        }
        Organizm organizm = FabrykaOrganizmow.stworz(this.getTyp(), polozenieNowejRosliny, swiat);
        swiat.dodajOrganizm(organizm);

    }

    protected boolean czyZasieje(){
        Random random = new Random();
        int szansaNaZasianie = random.nextInt(20);
        return szansaNaZasianie == 0;
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        System.out.println("Kolizja");
    }
}
