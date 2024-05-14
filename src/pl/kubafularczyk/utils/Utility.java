package pl.kubafularczyk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    private static String logFileName;

    /**
     * Uzupelnia liczbę spacjami z prawej strony az do podanej dlugosci.
     * @param liczba int
     * @param dlugosc int
     * @return liczba uzupelniona spacjami.
     */
    public static String uzupelnijSpacjami(int liczba, int dlugosc) {
        int przerwa = dlugosc - wyznaczLiczbeCyfr(liczba);
        String liczbaZeSpacjami = liczba + "";
        return uzupelnijSpacjamiUniwersalnie(liczbaZeSpacjami, przerwa);
    }

    /**
     * Uzupelnia tekst spacjami z prawej strony az do podanej dlugosci.
     * @param tekst String
     * @param dlugosc int
     * @return tekst uzupelniony spacjami.
     */
    public static String uzupelnijSpacjami(String tekst, int dlugosc) {
        int przerwa = dlugosc - tekst.length();
        String tekstZeSpacjami = tekst + "";
        return uzupelnijSpacjamiUniwersalnie(tekstZeSpacjami, przerwa);
    }

    private static String uzupelnijSpacjamiUniwersalnie(String tekst, int przerwa) {
        while(przerwa != 0){
            tekst += " ";
            przerwa--;
        }
        return tekst;
    }

    /**
     * Wyznacza liczbe cyfr w podanej jako argument liczbie.
     * alternatywnie i prosto: Integer.toString(liczba).length()
     *
     *       1234
     *      1*10^3 + 2*10^2 + 3*10^1 + 4*10^0
     *      >> 10 == /10, << 10 == *10
     *
     *      11011
     *      1*2^4 + 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0
     *      >> 2 == /2, << 2 == *2
     * @param liczba
     * @return liczba cyfr.
     */
    public static int wyznaczLiczbeCyfr(int liczba) {
        int liczbaCyfr = 0;
        do {
            liczbaCyfr++;
            liczba = liczba / 10;
        } while (liczba != 0);

        return liczbaCyfr;
    }

    public static String getLogFileName() {
        if (logFileName != null && !logFileName.isBlank()) {
            return logFileName;
        }

        String prefix = "src/notepad/informacje-";
        String suffix = ".txt";

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String formattedDate = simpleDateFormat.format(date);

        logFileName = prefix + formattedDate + suffix;
        return logFileName;
    }


}