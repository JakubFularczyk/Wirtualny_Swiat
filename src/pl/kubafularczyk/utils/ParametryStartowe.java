package pl.kubafularczyk.utils;

import pl.kubafularczyk.organizmy.TypOrganizmu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParametryStartowe {

    private int wysokosc;
    private int szerokosc;
    private List<TypOrganizmu> typyOrganizmow = new ArrayList<>();
    private int liczbaOrganizmow;
    private final Scanner scanner = new Scanner(System.in);
    public void pobierz() {

        boolean domyslny = sprawdzCzyDomyslnySwiat();
        if (domyslny) {
            pobierzDomyslneWartosci();
            return;
        }
        pobierzWysokosc();
        pobierzSzerokosc();
        pobierzLiczbeOrganizmow();
        pobierzTypyOrganizmow();
    }

    private boolean sprawdzCzyDomyslnySwiat() {
        System.out.println("Czy wygenerowac domyslny swiat? T/N");
        String domyslny = scanner.nextLine();
        return "T".equals(domyslny);
    }

    private void pobierzDomyslneWartosci() {
        this.wysokosc = 10;
        this.szerokosc = 10;
        this.liczbaOrganizmow = szerokosc * wysokosc / 30;
        this.typyOrganizmow = Arrays.asList(TypOrganizmu.values());
    }

    public void pobierzWysokosc() {
        System.out.println("Podaj wysokosc świata: ");
        this.wysokosc = scanner.nextInt();
    }

    public void pobierzSzerokosc() {
        System.out.println("Podaj szerokosc świata: ");
        this.szerokosc = scanner.nextInt();
    }


    public void pobierzLiczbeOrganizmow() {
        System.out.println("Podaj liczbe organizmów: ");
        this.liczbaOrganizmow = scanner.nextInt();
        scanner.nextLine(); // pobranie znaku '\n'
    }

    public void pobierzTypyOrganizmow() {
        System.out.println("Podaj typy organizów w wybranym świecie: ");
        System.out.println("" +
                "    WILK(\"W\"),//\uD83D\uDC3A\n" +
                "    LIS(\"L\"),//\uD83E\uDD8A\n" +
                "    ANTYLOPA(\"A\"),//\uD83D\uDC10\n" +
                "    CYBER_OWCA(\"C\"),//\uD83E\uDD8F\n" +
                "    ZOLW(\"Z\"),//\uD83D\uDC22\n" +
                "    OWCA(\"O\"),//\uD83D\uDC11\n" +
                "    TRAWA(\"T\"),//\uD83C\uDF31\n" +
                "    BARSZCZ_SOSNOWSKIEGO(\"B\"),//\uD83C\uDF8D\n" +
                "    GUARANA(\"G\"),//\uD83D\uDC9A\n" +
                "    MLECZ(\"M\"),//\uD83C\uDF3F\n" +
                "    WILCZE_JAGODY(\"J\"),// \uD83E\uDED0\n" +
                "    CZLOWIEK(\"P\"); //\uD83D\uDC68");
        String stringOrganizmow = scanner.nextLine();
        String[] tablicaStringowOrganizmow = stringOrganizmow.split(",");
        for(int i = 0; i < tablicaStringowOrganizmow.length; i++){
            String nazwaTypu = tablicaStringowOrganizmow[i];
            nazwaTypu = nazwaTypu.trim();
            TypOrganizmu typOrganizmu = TypOrganizmu.of(nazwaTypu);
            typyOrganizmow.add(typOrganizmu);
        }
    }

    public int getLiczbaOrganizmow() {
        return liczbaOrganizmow;
    }

    public List<TypOrganizmu> getTypyOrganizmow() {
        return typyOrganizmow;
    }

    public int getWysokosc() {
        return wysokosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }
}
