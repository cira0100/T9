package Tim9.ImiPoslovi;

public class Komentar {
    private String s1;
    private String s2;
    private String tekst;

    public Komentar(String s1, String s2, String tekst) {
        this.s1 = s1;
        this.s2 = s2;
        this.tekst = tekst;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
}
