package Tim9.ImiPoslovi;

public class Admin extends Korisnik{
    private String ime;
    private String prezime;

    public Admin(String username, String password, String mail, int type, String ime, String prezime) {
        super(username, password, mail, type);
        this.ime = ime;
        this.prezime = prezime;
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
}
