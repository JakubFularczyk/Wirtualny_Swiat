package pl.kubafularczyk;

public class Wilk extends Zwierze {

    public Wilk(Polozenie polozenie, Swiat swiat){
        super(polozenie,swiat); // wywolanie konstruktora z klasy Zwierze (zrob wszystko co przy tworzeniu zwierzecia)
        this.symbol = "W";

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
