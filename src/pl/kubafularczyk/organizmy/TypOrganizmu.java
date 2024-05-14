package pl.kubafularczyk.organizmy;

public enum TypOrganizmu {


    //TODO znalezc unicode pasujace do postaci
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
}
