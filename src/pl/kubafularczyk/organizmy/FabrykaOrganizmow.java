package pl.kubafularczyk.organizmy;

import pl.kubafularczyk.Swiat;
import pl.kubafularczyk.exceptions.NieistniejacyOrganizmException;
import pl.kubafularczyk.nawigacja.Polozenie;
import pl.kubafularczyk.organizmy.rosliny.*;
import pl.kubafularczyk.organizmy.zwierzeta.*;
import pl.kubafularczyk.organizmy.zwierzeta.czlowiek.Czlowiek;

public class FabrykaOrganizmow {

    public static Organizm stworz(TypOrganizmu typ, Polozenie polozenie, Swiat swiat) {
        Organizm organizm = switch (typ) {
            case TRAWA -> new Trawa(polozenie, swiat);
            case WILK -> new Wilk(polozenie, swiat);
            case LIS -> new Lis(polozenie, swiat);
            case ANTYLOPA -> new Antylopa(polozenie, swiat);
            case CYBER_OWCA -> new CyberOwca(polozenie, swiat);
            case ZOLW -> new Zolw(polozenie, swiat);
            case BARSZCZ_SOSNOWSKIEGO -> new BarszczSosnowskiego(polozenie, swiat);
            case GUARANA -> new Guarana(polozenie, swiat);
            case WILCZE_JAGODY -> new WilczeJagody(polozenie, swiat);
            case MLECZ -> new Mlecz(polozenie, swiat);
            case OWCA -> new Owca(polozenie, swiat);
            case CZLOWIEK -> new Czlowiek(polozenie, swiat);
            default -> throw new NieistniejacyOrganizmException();
        };
        return organizm;
    }

    public static Organizm stworz(TypOrganizmu typ, Polozenie polozenie, Swiat swiat, boolean wylaczRuch) {
        Organizm organizm = stworz(typ, polozenie, swiat);
        if(wylaczRuch) {
            organizm.wylaczRuch();
        }
        return organizm;
    }

}
