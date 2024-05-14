package pl.kubafularczyk.organizmy;

public class WalczacaPara {

    private Organizm slabszyOrganizm;
    private Organizm silniejszyOrganizm;

    public WalczacaPara(Organizm slabszyOrganizm, Organizm silniejszyOrganizm) {
        this.slabszyOrganizm = slabszyOrganizm;
        this.silniejszyOrganizm = silniejszyOrganizm;
    }

    public Organizm getSlabszyOrganizm() {
        return slabszyOrganizm;
    }

    public Organizm getSilniejszyOrganizm() {
        return silniejszyOrganizm;
    }
}
