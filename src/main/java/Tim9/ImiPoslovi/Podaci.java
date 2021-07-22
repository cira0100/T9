package Tim9.ImiPoslovi;

public class Podaci {
    private int id;
    private String username;
    private String mail;

    public Podaci(int id, String username, String mail) {
        this.id = id;
        this.username = username;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
