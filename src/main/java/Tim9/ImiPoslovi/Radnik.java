package Tim9.ImiPoslovi;


import java.sql.Date;

public class Radnik extends Korisnik{
    private String ime;
    private String prezime;
    private Date date;
    private String slika;

    public Radnik(String username, String password, String mail, int type, String ime, String prezime, Date date, String slika) {
        super(username, password, mail, type);
        this.ime = ime;
        this.prezime = prezime;
        this.date = date;
        this.slika = slika;
    }
    public Radnik(){

    }
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
