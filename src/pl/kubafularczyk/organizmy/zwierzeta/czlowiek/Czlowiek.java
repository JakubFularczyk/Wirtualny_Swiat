package pl.kubafularczyk.organizmy.zwierzeta.czlowiek;


import pl.kubafularczyk.nawigacja.Kierunek;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.organizmy.Organizm;
import pl.kubafularczyk.organizmy.TypOrganizmu;
import pl.kubafularczyk.organizmy.zwierzeta.Zwierze;
import pl.kubafularczyk.organizmy.zwierzeta.czlowiek.umiejetnosci.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Czlowiek extends Zwierze {

    private Kierunek nastepnyKierunek;
    private Set<Umiejetnosc> umiejetnosci;

    private final Scanner scanner = new Scanner(System.in);

    public Czlowiek(Polozenie polozenie, Swiat swiat) {
        super(polozenie, swiat);
        this.sila = 5;
        this.inicjatywa = 4;
        this.typ = TypOrganizmu.CZLOWIEK;
        this.umiejetnosci = new HashSet<>();

    }

    @Override
    public void akcja() {
        if(!umiejetnosci.isEmpty()){
            umiejetnosci.forEach(Umiejetnosc::dzialanie);
            umiejetnosci = umiejetnosci.stream() //
                .filter(u -> !u.czyWylaczona()) //
                .collect(Collectors.toSet());
        }
        super.akcja();
    }

    @Override
    protected Kierunek wybierzKierunek() {
        return nastepnyKierunek;
    }

    @Override
    protected void kolizja(Organizm atakowanyOrganizm) {
        super.kolizja(atakowanyOrganizm);
    }

    /**
     * Pobranie ruchu przed wszystkimi organizmami.
     * Pobrany ruch ma wplyw na pozniejsze wybranie kierunku w Czlowiek#wybierzKierunek().
     */
    public void pobierzKolejnyRuch() {
        pobierzKolejnyRuch(true);
    }

    private void pobierzKolejnyRuch(boolean zUmiejetnoscia) {
        String ruch = scanner.nextLine();
        if(jestKierunkiem(ruch)) {
            Kierunek kierunek = Kierunek.of(ruch);
            if(kierunekPrawidlowy(kierunek)) {
                nastepnyKierunek = kierunek;
            } else {
                pobierzKolejnyRuch(false);
            }
        } else if(jestUmiejetnoscia(ruch) && zUmiejetnoscia) {
            TypUmiejetnosci typUmiejetnosci = TypUmiejetnosci.of(ruch);
            aktywuj(typUmiejetnosci);
            pobierzKolejnyRuch(false);
        } else {
            pobierzKolejnyRuch(zUmiejetnoscia);
        }
    }
    // TODO* ^sprobowac przerobic powyzsze warunki/sterowanie na bardziej funkcyjne (z Optionalami)

    // TODO aktualne metody jestKierunkiem (Kierunek) i jestUmiejetnoscia (TypUmiejetnosci) duplikuja troche kodu z enumow - mozna sprobowac to uproscic
    private boolean jestKierunkiem(String ruch) {
        for(Kierunek kierunek : Kierunek.values()) {
            if (kierunek.getSymbol().equalsIgnoreCase(ruch)) {
                return true;
            }
        }
        return false;
    }

    private boolean jestUmiejetnoscia(String ruch) {

        for(TypUmiejetnosci typUmiejetnosci : TypUmiejetnosci.values()) {
            if(typUmiejetnosci.getNumerUmiejetnosci().equals(ruch)) {
                return true;
            }
        }
        return false;
    }

    private boolean kierunekPrawidlowy(Kierunek kierunek) {
        Polozenie przyszlePolozenie = polozenie.stworzPrzesunietaKopie(kierunek);
        return przyszlePolozenie.czyPoprawne(swiat.getSzerokosc(),swiat.getWysokosc());
    }

    private void aktywuj(TypUmiejetnosci typUmiejetnosci) {
        System.out.println("Umiejetnosc aktywowana");
        if (TypUmiejetnosci.NIESMIERTELNOSC.equals(typUmiejetnosci)) {
            umiejetnosci.add(new Niesmiertelnosc(this));
        } else if(TypUmiejetnosci.TARCZA_ALZURY.equals(typUmiejetnosci)) {
            umiejetnosci.add(new TarczaAlzury(this));
        } else if(TypUmiejetnosci.MAGICZNY_ELIKSIR.equals(typUmiejetnosci)) {
            umiejetnosci.add(new MagicznyEliksir(this, this::setSila));
        } else if(TypUmiejetnosci.SZYBKOSC_ANTYLOPY.equals(typUmiejetnosci)) {
            umiejetnosci.add(new SzybkoscAntylopy(this));
        } else if(TypUmiejetnosci.CALOPALENIE.equals(typUmiejetnosci)) {
            umiejetnosci.add(new Calopalenie(this));
        }
    }


}

