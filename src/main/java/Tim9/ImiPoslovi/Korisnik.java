package Tim9.ImiPoslovi;

abstract public class Korisnik {
    private int id;
    private String username;
    private String password;
    private String mail;
    private int type;
    private String token;

    public Korisnik( String username, String password, String mail, int type) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.type = type;
    }
    public Korisnik(){

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
