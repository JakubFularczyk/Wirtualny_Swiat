package pl.kubafularczyk.organizmy.zwierzeta.czlowiek.umiejetnosci;

import pl.kubafularczyk.organizmy.zwierzeta.czlowiek.Czlowiek;

import java.util.function.Consumer;

public class MagicznyEliksir extends Umiejetnosc{

    Consumer<Integer> zmienianieSilyConsumer;

    public MagicznyEliksir(Czlowiek czlowiek, Consumer<Integer> zmienianieSilyConsumer) {
        this.czlowiek = czlowiek;
        this.zmienianieSilyConsumer = zmienianieSilyConsumer;
    }

    @Override
    protected void uzyj() {
        zmienianieSilyConsumer.accept(10);
    }

    @Override
    protected String getNazwaUmiejetnosci() {
        return "Magiczny Eliksir";
    }
}
