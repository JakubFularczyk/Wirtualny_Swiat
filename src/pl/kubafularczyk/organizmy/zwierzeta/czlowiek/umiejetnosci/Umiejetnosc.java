package pl.kubafularczyk.organizmy.zwierzeta.czlowiek.umiejetnosci;

import pl.kubafularczyk.organizmy.zwierzeta.czlowiek.Czlowiek;

public abstract class Umiejetnosc {

    protected Czlowiek czlowiek;

    private int czasTrwaniaUmiejetnosci = 0; // calkowity czas trwania umiejetnosci
    // TODO moze zmiana nazw? w czasie aktywacji chodzi o to ile tur bedzie aktywna
    private final int CZAS_AKTYWACJI = 5; // czas aktywacji - czas kiedy umiejetnosc dziala
    private final int CZAS_DEZAKTYWACJI = 5; // czas dezaktywacji - czas kiedy umiejetnosc ma cooldown

    public void dzialanie() {
        czasTrwaniaUmiejetnosci++;
        if (czyAktywna()) {
            System.out.println("Umiejetnosc " + getNazwaUmiejetnosci() + " dziala" + " " + czasTrwaniaUmiejetnosci);
            uzyj();
        } else if(czyDezaktywowana()) {
            System.out.println("Umiejetnosc " + getNazwaUmiejetnosci() + " ma cooldown" + " " + czasTrwaniaCooldownu(czasTrwaniaUmiejetnosci));
        } else {
            System.out.println("Umiejetnosc " + getNazwaUmiejetnosci() + " wylaczona");
        }
    }

    protected boolean czyAktywna() {
        return czasTrwaniaUmiejetnosci <= CZAS_AKTYWACJI;
    }

    protected boolean czyDezaktywowana() {
        return !czyAktywna() && czasTrwaniaUmiejetnosci <= CZAS_AKTYWACJI + CZAS_DEZAKTYWACJI;
    }

    public boolean czyWylaczona() {
        return !czyAktywna() && !czyDezaktywowana();
    }

    protected abstract void uzyj();

    protected abstract String getNazwaUmiejetnosci();

    protected int czasTrwaniaCooldownu(int czasTrwaniaUmiejetnosci) {
        return czasTrwaniaUmiejetnosci - CZAS_AKTYWACJI;
    }

}
