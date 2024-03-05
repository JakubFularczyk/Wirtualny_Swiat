package pl.kubafularczyk;

public class Wilk extends Zwierze {

    public Wilk(Polozenie polozenie, Swiat swiat){
        super(polozenie,swiat);
        this.symbol = "W";
        this.inicjatywa = 5;
        this.sila = 9;
    }

    @Override
    protected void akcja(){
        super.akcja();
    }

    @Override
    public Organizm stworz(Polozenie polozenie, Swiat swiat) {
        return new Wilk(polozenie, swiat);
    }

}
