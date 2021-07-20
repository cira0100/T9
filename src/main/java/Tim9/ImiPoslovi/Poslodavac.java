package Tim9.ImiPoslovi;

public class Poslodavac extends Korisnik {
    private String naziv;
    private String adresa;
    private String slika;
    private Double ocena;

    public Poslodavac( String username, String password, String mail, int type, String naziv, String adresa, String slika) {
        super(username, password, mail, type);
        this.naziv = naziv;
        this.adresa = adresa;
        this.slika = slika;
    }
    public Poslodavac(){
        super();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }
}
