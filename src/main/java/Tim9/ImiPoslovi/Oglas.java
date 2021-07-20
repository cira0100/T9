package Tim9.ImiPoslovi;

import java.sql.Date;

public class Oglas {
    private int IdOglasa;
    private String Naziv;
    private int IdPoslodavca;
    private Date DatumPostavljanja;
    private Date DatumIsteka;
    private int Lajkovi;
    private int Posete;
    private String Kategorija;
    private String PodKategorije;
    private int plata;
    private String Slika;
    private String Tekst;
    private boolean Remote;

    public Oglas(String naziv, int idPoslodavca, Date datumIsteka, String kategorija, String podKategorije, int plata, String slika, String tekst, boolean remote) {
        Naziv = naziv;
        IdPoslodavca = idPoslodavca;
        DatumIsteka = datumIsteka;
        Kategorija = kategorija;
        PodKategorije = podKategorije;
        this.plata = plata;
        Slika = slika;
        Tekst = tekst;
        Remote = remote;
        Lajkovi=0;
        Posete=0;
        DatumPostavljanja=new Date(System.currentTimeMillis());
    }

    public Oglas(int idOglasa, String naziv, int idPoslodavca, Date datumPostavljanja, Date datumIsteka, int lajkovi, int posete, String kategorija, String podKategorije, int plata, String slika, String tekst, boolean remote) {
        IdOglasa = idOglasa;
        Naziv = naziv;
        IdPoslodavca = idPoslodavca;
        DatumPostavljanja = datumPostavljanja;
        DatumIsteka = datumIsteka;
        Lajkovi = lajkovi;
        Posete = posete;
        Kategorija = kategorija;
        PodKategorije = podKategorije;
        this.plata = plata;
        Slika = slika;
        Tekst = tekst;
        Remote = remote;
    }

    public int getIdOglasa() {
        return IdOglasa;
    }

    public void setIdOglasa(int idOglasa) {
        IdOglasa = idOglasa;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        Naziv = naziv;
    }

    public int getIdPoslodavca() {
        return IdPoslodavca;
    }

    public void setIdPoslodavca(int idPoslodavca) {
        IdPoslodavca = idPoslodavca;
    }

    public Date getDatumPostavljanja() {
        return DatumPostavljanja;
    }

    public void setDatumPostavljanja(Date datumPostavljanja) {
        DatumPostavljanja = datumPostavljanja;
    }

    public Date getDatumIsteka() {
        return DatumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        DatumIsteka = datumIsteka;
    }

    public int getLajkovi() {
        return Lajkovi;
    }

    public void setLajkovi(int lajkovi) {
        Lajkovi = lajkovi;
    }

    public int getPosete() {
        return Posete;
    }

    public void setPosete(int posete) {
        Posete = posete;
    }

    public String getKategorija() {
        return Kategorija;
    }

    public void setKategorija(String kategorija) {
        Kategorija = kategorija;
    }

    public String getPodKategorije() {
        return PodKategorije;
    }

    public void setPodKategorije(String podKategorije) {
        PodKategorije = podKategorije;
    }

    public int getPlata() {
        return plata;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }

    public String getSlika() {
        return Slika;
    }

    public void setSlika(String slika) {
        Slika = slika;
    }

    public String getTekst() {
        return Tekst;
    }

    public void setTekst(String tekst) {
        Tekst = tekst;
    }

    public boolean isRemote() {
        return Remote;
    }

    public void setRemote(boolean remote) {
        Remote = remote;
    }
}
