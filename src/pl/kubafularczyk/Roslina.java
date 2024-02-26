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
            Komentator.brakKierunkowZasiania(polozenie,swiat.getOrganizm(polozenie));
            return;
        }
        Organizm organizm = stworz(polozenieNowejRosliny, swiat);
        swiat.dodajOrganizm(organizm);

    }

    protected boolean czyZasieje(){
        Random random = new Random();
        int szansaNaZasianie = random.nextInt(20);
        return szansaNaZasianie == 0;
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        System.out.println("Kolizja"); // TODO komentator? jakiś dłuższy opis tego co sie stało
    }
}
