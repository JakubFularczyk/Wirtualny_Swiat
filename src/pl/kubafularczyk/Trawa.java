package pl.kubafularczyk;

public class Trawa extends Roslina {

    public Trawa(Polozenie polozenie,Swiat swiat) {
        super(polozenie, swiat);
        this.symbol = "T";
    }

    @Override
    protected void kolizja() {

    }

    @Override
    public Organizm stworz(Polozenie polozenie, Swiat swiat) {
        return new Trawa(polozenie, swiat);
    }


}
