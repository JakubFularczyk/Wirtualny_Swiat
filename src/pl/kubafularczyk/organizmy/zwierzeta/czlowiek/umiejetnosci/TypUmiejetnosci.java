package pl.kubafularczyk.organizmy.zwierzeta.czlowiek.umiejetnosci;
import pl.kubafularczyk.exceptions.NieistniejacaUmiejetnoscException;

public enum TypUmiejetnosci {
    NIESMIERTELNOSC("1"),
    MAGICZNY_ELIKSIR("2"),
    SZYBKOSC_ANTYLOPY("3"),
    TARCZA_ALZURY("4"),
    CALOPALENIE("5");

    String numerUmiejetnosci;

    public String getNumerUmiejetnosci() {
        return numerUmiejetnosci;
    }

    TypUmiejetnosci(String numerUmiejetnosci) {
        this.numerUmiejetnosci = numerUmiejetnosci;
    }

    public static TypUmiejetnosci of(String numerUmiejetnosci) throws NieistniejacaUmiejetnoscException {
        for(TypUmiejetnosci typUmiejetnosci : TypUmiejetnosci.values()) {
            if(typUmiejetnosci.numerUmiejetnosci.equals(numerUmiejetnosci)){
                return typUmiejetnosci;
            }
        }
        throw new NieistniejacaUmiejetnoscException();
    }

}
