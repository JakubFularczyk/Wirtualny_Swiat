package pl.kubafularczyk.organizmy;

import pl.kubafularczyk.exceptions.NieistniejacyOrganizmException;

public enum TypOrganizmu {

    WILK("W"),//🐺
    LIS("L"),//🦊
    ANTYLOPA("A"),//🐐
    CYBER_OWCA("C"),//🦏
    ZOLW("Z"),//🐢
    OWCA("O"),//🐑
    TRAWA("T"),//🌱
    BARSZCZ_SOSNOWSKIEGO("B"),//🎍
    GUARANA("G"),//💚
    MLECZ("M"),//🌿
    WILCZE_JAGODY("J"),// 🫐
    CZLOWIEK("P"); //👨

    private final String symbol;

    TypOrganizmu(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TypOrganizmu of(String symbol) {
        for (TypOrganizmu t : TypOrganizmu.values()) {
            if (t.getSymbol().equals(symbol)) {
                return t;
            }
        }
        throw new NieistniejacyOrganizmException();
    }
}
