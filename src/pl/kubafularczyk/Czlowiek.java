package pl.kubafularczyk;

public class Czlowiek extends Zwierze {

    public Czlowiek(Polozenie polozenie, Swiat swiat){
        super(polozenie, swiat);
    }

    @Override
    public Organizm stworz(Polozenie polozenie, Swiat swiat) {
        return new Czlowiek(polozenie, swiat);
    }

}
