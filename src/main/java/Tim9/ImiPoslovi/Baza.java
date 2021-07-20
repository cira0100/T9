package Tim9.ImiPoslovi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.*;

public class Baza {

    private Connection conn = null;
    public Statement stmt = null;
    private String sql;

    public Baza(){
        System.out.println("Connecting...");
        try{
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/poslovi", "root", "");
            System.out.println("Connected.");
            stmt = conn.createStatement();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private String GenerateCookie() {
        String cookie = new String();
        char c;
        int t;
        for(int i = 0; i < 32; i++) {
            t = (int)(Math.random() * 100) % 62; /* 26 lowercase + 26 uppercase + 10 digits; values 0 - 61. */

            if(t < 10) {
                c = (char)('0' + t);
            } else if(t >= 10 && t < 36) {
                c = (char)('a' + (t - 10));
            } else {
                c = (char)('A' + (t - 36));
            }

            cookie += c;
        }

        return cookie;
    }

    private String postojiKorisnik(String username, String email){
        sql= "SELECT * FROM `glavna` WHERE `Username` = ?;";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,String.valueOf(username));

            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return null;
            else{
                sql= "SELECT * FROM `glavna` WHERE `Mail` = ?;";
                ps=conn.prepareStatement(sql);

                ps.setString(1,String.valueOf(email));

                rs=ps.executeQuery();
                if(rs.next())
                    return null;
                else{
                    String response=GenerateCookie();
                    do {
                        response = GenerateCookie();
                        sql = "SELECT * FROM `glavna` WHERE `Token`=?;";
                        ps=conn.prepareStatement(sql);
                        ps.setString(1,String.valueOf(response));
                        rs = ps.executeQuery();
                    } while(rs.next());
                    return response;
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
    public boolean  registruj(Korisnik korisnik) throws SQLException {
        String token;
        PreparedStatement ps;
        ResultSet rs;
        token=postojiKorisnik(korisnik.getUsername(),korisnik.getMail());
        if(token==null) {
            return false;
        }
        if(korisnik instanceof Admin)
            return false;
        korisnik.setPassword(PasswordCrypt.hashPasword(korisnik.getPassword()));
        sql = "INSERT INTO `glavna`(Username,Password,Mail,Type,Token) VALUES(?,?,?,?,?)";
        ps=conn.prepareStatement(sql);
        korisnik.setToken(token);
        ps.setString(1,String.valueOf(korisnik.getUsername()));
        ps.setString(2,String.valueOf(korisnik.getPassword()));
        ps.setString(3,String.valueOf(korisnik.getMail()));
        ps.setString(4,String.valueOf(korisnik.getType()));
        ps.setString(5,String.valueOf(korisnik.getToken()));
        rs = ps.executeQuery();

        sql = "SELECT * FROM `glavna` WHERE `Token`=?;";
        ps=conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(token));
        rs = ps.executeQuery();
        rs.next();
        int id=rs.getInt("Id");
        System.out.println(id);
        korisnik.setId(id);

        if(korisnik instanceof Radnik){
            Radnik r=(Radnik) korisnik;
            sql = "INSERT INTO `radnik`(Id,Ime,Prezime,Date,Slika) VALUES(?,?,?,?,?);";
            ps=conn.prepareStatement(sql);
            ps.setString(1,String.valueOf(r.getId()));
            ps.setString(2,String.valueOf(r.getIme()));
            ps.setString(3,String.valueOf(r.getPrezime()));
            ps.setString(4,String.valueOf(r.getDate()));
            ps.setString(5,String.valueOf(r.getSlika()));
            rs = ps.executeQuery();
            return true;
        }else if(korisnik instanceof Poslodavac)
        {
            Poslodavac r=(Poslodavac) korisnik;
            sql = "INSERT INTO `poslodavac`(Id,Naziv,Adresa,Slika) VALUES(?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,String.valueOf(r.getId()));
            ps.setString(2,String.valueOf(r.getNaziv()));
            ps.setString(3,String.valueOf(r.getAdresa()));
            ps.setString(4,String.valueOf(r.getSlika()));
            rs = ps.executeQuery();
            return true;
        }
        return true;
    }
    public boolean  registruj(Korisnik korisnik,String pomToken) throws SQLException {
        String token;
        PreparedStatement ps;
        ResultSet rs;
        sql = "SELECT * FROM `glavna` WHERE `Token`=?;";
        ps=conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(pomToken));
        rs = ps.executeQuery();
        if(rs.next()) {
            int tip = rs.getInt("Type");
            if (tip != 3)
                return false;
        }else
            return false;
        token=postojiKorisnik(korisnik.getUsername(),korisnik.getMail());
        if(token==null) {
            return false;
        }
        korisnik.setPassword(PasswordCrypt.hashPasword(korisnik.getPassword()));
        sql = "INSERT INTO `glavna`(Username,Password,Mail,Type,Token) VALUES(?,?,?,?,?)";
        ps=conn.prepareStatement(sql);
        korisnik.setToken(token);
        ps.setString(1,String.valueOf(korisnik.getUsername()));
        ps.setString(2,String.valueOf(korisnik.getPassword()));
        ps.setString(3,String.valueOf(korisnik.getMail()));
        ps.setString(4,String.valueOf(korisnik.getType()));
        ps.setString(5,String.valueOf(korisnik.getToken()));
        rs = ps.executeQuery();

        sql = "SELECT * FROM `glavna` WHERE `Token`=?;";
        ps=conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(token));
        rs = ps.executeQuery();
        rs.next();
        int id=rs.getInt("Id");
        System.out.println(id);
        korisnik.setId(id);

        if(korisnik instanceof Admin){
            Admin r=(Admin) korisnik;
            sql = "INSERT INTO `admin`(Id,Ime,Prezime) VALUES(?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,String.valueOf(r.getId()));
            ps.setString(2,String.valueOf(r.getIme()));
            ps.setString(3,String.valueOf(r.getPrezime()));
            rs = ps.executeQuery();
            return true;
        }
        return true;
    }
    public String Login(String username,String password){
        sql="SELECT * FROM `glavna` WHERE `Username` = ? ;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, String.valueOf(username));

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String passwordH =rs.getString("Password");
                if(PasswordCrypt.checkPassword(password,passwordH))
                {
                    return rs.getString("Token");
                }
                else
                    return null;
            }
            else
                return null;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public int TokenToId(String token){
        sql="SELECT * FROM `glavna` WHERE Token = ? ;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(token));
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                int id=rs.getInt("Id");
                return id;
            }
            else
                return -1;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Oglas> oglasiPoslodavca(int IdPoslodavca){ //Lista oglase ulogovanog poslodavca
        sql="SELECT * FROM `oglas` WHERE IdPoslodavca = ? ;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,IdPoslodavca);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                rs.previous();
            } else {
                return null;
            }
            ArrayList<Oglas> oglasipos = new ArrayList<Oglas>(20);
            while(rs.next()){
                int IdOglasa= rs.getInt("Id");
                String Naziv= rs.getString("Naziv");
                int idPoslodavca= rs.getInt("IdPoslodavca");
                Date DatumPostavljanja= rs.getDate("Datum-Postavljanja");
                Date DatumIsteka= rs.getDate("Datum-Isteka");
                int Lajkovi= rs.getInt("Lajkovi");
                int Posete= rs.getInt("Posete");
                String Kategorija= rs.getString("Kategorija");
                String PodLategorije= rs.getString("PodKategorije");
                int plata = rs.getInt("plata");
                String Slika = rs.getString("Slika");
                String Tekst = rs.getString("Tekst");
                boolean Remote = rs.getBoolean("Remote");

                Oglas temp= new Oglas(IdOglasa,Naziv,idPoslodavca,DatumPostavljanja,DatumIsteka,Lajkovi,Posete,Kategorija,PodLategorije,plata,Slika,Tekst,Remote);
                oglasipos.add(temp);
            }
            return oglasipos;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Oglas> oglasiPoslodavca(String token){ //Lista oglase ulogovanog poslodavca

        int IdPoslodavca=(int)TokenToId(token);
        if(IdPoslodavca==-1)
            return null;
        return oglasiPoslodavca(IdPoslodavca);
    }

    public ArrayList<Poslodavac> traziPoslodavca(String pretraga){
        Pattern pattern=Pattern.compile(pretraga,Pattern.CASE_INSENSITIVE);
        sql="SELECT * FROM `poslodavac` p join `glavna` g on p.Id=g.Id;";
        try {
            ResultSet rs= stmt.executeQuery(sql);
            if(!rs.first())
                return null;
            else
                rs.previous();
            ArrayList<Poslodavac> listapos= new ArrayList<Poslodavac>(20);
            while(rs.next()){
                Matcher matcher=pattern.matcher(rs.getString("p.Naziv"));
                if(matcher.find()){
                    int id=rs.getInt("p.Id");
                    String naziv=rs.getString("p.Naziv");
                    String adresa=rs.getString("p.Adresa");
                    String slika=rs.getString("p.Slika");
                    double ocena=rs.getDouble("p.Ocena");
                    Poslodavac tmp=new Poslodavac();
                    tmp.setId(id);
                    tmp.setNaziv(naziv);
                    tmp.setAdresa(adresa);
                    tmp.setSlika(slika);
                    tmp.setOcena(ocena);
                    listapos.add(tmp);
                }
            }
            if(listapos.isEmpty())
                return null;
            return listapos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean daLiJeMojOglas(String token, int idOgl){
        sql="SELECT gl.Id FROM `glavna`gl WHERE gl.Token= ? ;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(token));
            ResultSet rs = ps.executeQuery();
            if(rs.first()){
                int idPos=rs.getInt("gl.Id");
                sql = "SELECT * FROM `oglas` WHERE `IdPoslodavca`= ? AND `Id`= ?;";
                ps=conn.prepareStatement(sql);
                ps.setString(1,String.valueOf(idPos));
                ps.setString(2,String.valueOf(idOgl));
                rs=ps.executeQuery();
                if(rs.first())
                    return true;
                else
                    return false;
            }
            else return false;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Radnik> prijavljeniNaOglas(String token,int idOgl){

        if(daLiJeMojOglas(token,idOgl)) {
            sql = "SELECT r.Id,r.Ime,r.Prezime,r.Date,r.Slika FROM `prijave` p  join radnik r on p.IdRadnika=r.Id WHERE p.IdOglasa = ?;";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, String.valueOf(idOgl));
                ResultSet rs = ps.executeQuery();
                if(!rs.first())
                    return null;
                else
                    rs.previous();
                ArrayList<Radnik> lrad= new ArrayList<Radnik>(20);
                while(rs.next()){
                    int id=rs.getInt("r.Id");
                    String ime=rs.getString("r.Ime");
                    String prez=rs.getString("r.Prezime");
                    Date date=rs.getDate("r.Date");
                    String slika=rs.getString("r.Slika");
                    Radnik tmp=new Radnik();
                    tmp.setId(id);
                    tmp.setIme(ime);
                    tmp.setPrezime(prez);
                    tmp.setDate(date);
                    tmp.setSlika(slika);
                    lrad.add(tmp);
                }
                return lrad;
            }
            catch (SQLException throwables){
                throwables.printStackTrace();
            }
            return null;
        }
        else
            return null;
    }

    public ArrayList<Oglas> mojePrijaveOglasa(String token){
        sql = "SELECT * FROM `glavna` gl join `prijave` pr on gl.Id=pr.IdRadnika join `oglas` o on pr.IdOglasa=o.Id WHERE gl.Token= ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(token));
            ResultSet rs = ps.executeQuery();
            if(!rs.first())
                return null;
            else
                rs.previous();
            ArrayList<Oglas> oglasipos = new ArrayList<Oglas>(20);
            while(rs.next()){
                int IdOglasa= rs.getInt("Id");
                String Naziv= rs.getString("Naziv");
                int idPoslodavca= rs.getInt("IdPoslodavca");
                Date DatumPostavljanja= rs.getDate("Datum-Postavljanja");
                Date DatumIsteka= rs.getDate("Datum-Isteka");
                int Lajkovi= rs.getInt("Lajkovi");
                int Posete= rs.getInt("Posete");
                String Kategorija= rs.getString("Kategorija");
                String PodLategorije= rs.getString("PodKategorije");
                int plata = rs.getInt("plata");
                String Slika = rs.getString("Slika");
                String Tekst = rs.getString("Tekst");
                boolean Remote = rs.getBoolean("Remote");

                Oglas temp= new Oglas(IdOglasa,Naziv,idPoslodavca,DatumPostavljanja,DatumIsteka,Lajkovi,Posete,Kategorija,PodLategorije,plata,Slika,Tekst,Remote);
                oglasipos.add(temp);
            }
            return oglasipos;
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }





    public boolean prijaveMeNaOglas(String token,String cv,int idOglasa){
        int idRadnika=TokenToId(token);
        if(idRadnika==-1)
            return false;
        if(vratiType(idRadnika)!=1)
            return false;
        try {
            sql = "SELECT * from `oglas` WHERE Id=?;";
            PreparedStatement ps = null;

            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(idOglasa));
            ResultSet rs = ps.executeQuery();
            if(!rs.first())
                return false;



            int idPoslodavca=rs.getInt("IdPoslodavca");
            String Naziv=rs.getString("Naziv");

            sql = "SELECT * from `prijave` WHERE IdOglasa=? AND IdRadnika=?;";
             ps = null;

            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(idOglasa));
            ps.setString(2, String.valueOf(idRadnika));
            rs = ps.executeQuery();
            if(rs.first())
                return false;





            sql = "SELECT * from `glavna` WHERE Id=?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(idPoslodavca));
             rs = ps.executeQuery();
            if(!rs.first())
                return false;
            String email=rs.getString("Mail");


            EMailSender.SendEmail(email,Naziv,cv);

            sql = "INSERT INTO `prijave`(IdOglasa,IdRadnika) VALUES(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(idOglasa));
            ps.setString(2, String.valueOf(idRadnika));
            rs=ps.executeQuery();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }






    int vratiType(int id){


        try {
            sql = "SELECT * from `glavna` WHERE Id=?;";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if(!rs.first())
                return -1;
            return rs.getInt("Type");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }


    public boolean postOglas(String token,Oglas oglas){
        int idpos=TokenToId(token);
        if(idpos==-1)
            return false;
        int tip=vratiType(idpos);

        if(tip==2) {
            sql = "INSERT INTO `oglas`(Naziv,IdPoslodavca,`Datum-Isteka`,Kategorija,Podkategorije,Plata,Slika,Tekst,Remote,`Datum-Postavljanja`,Lajkovi,Posete) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, String.valueOf(oglas.getNaziv()));
                ps.setInt(2, idpos);
                ps.setDate(3, oglas.getDatumIsteka());
                ps.setString(4, String.valueOf(oglas.getKategorija()));
                ps.setString(5, String.valueOf(oglas.getPodKategorije()));
                ps.setInt(6, oglas.getPlata());
                ps.setString(7, String.valueOf(oglas.getSlika()));
                ps.setString(8, String.valueOf(oglas.getTekst()));
                ps.setBoolean(9, oglas.isRemote());
                ps.setDate(10, oglas.getDatumPostavljanja());
                ps.setInt(11, 0);
                ps.setInt(12, 0);
                ResultSet rs = ps.executeQuery();

                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public boolean postKom(String token,int idOgl,String tekst){
        int iduser=TokenToId(token);
        sql="INSERT INTO `komentari` (IdOglasa,IdUsera,Tekst) VALUES (?,?,?);";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idOgl);
            ps.setInt(2, iduser);
            ps.setString(3,String.valueOf(tekst));
            ResultSet rs=ps.executeQuery();
            return true;
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> basicInfo(int iduser){
        try{
            String sql1="SELECT * FROM `glavna` WHERE Id=?;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,iduser);
            ResultSet rs1=ps1.executeQuery();
            if(!rs1.first())
                return null;
            int tip=rs1.getInt("Type");
            ArrayList<String> ret=new ArrayList<>(2);
            String sql2;
            ResultSet rs2;
            PreparedStatement ps2;
            String s1,s2;
            if(tip==1){
                sql2="SELECT * FROM `radnik` WHERE Id=?;";
                ps2=conn.prepareStatement(sql2);
                ps2.setInt(1,iduser);
                rs2=ps2.executeQuery();
                rs2.first();
                s1=rs2.getString("Ime");
                s2=rs2.getString("Prezime");
                ret.add(s1);
                ret.add(s2);
            }
            else if(tip==2){
                sql2="SELECT * FROM `poslodavac` WHERE Id=?;";
                ps2=conn.prepareStatement(sql2);
                ps2.setInt(1,iduser);
                rs2=ps2.executeQuery();
                rs2.first();
                s1=rs2.getString("Naziv");
                s2=rs2.getString("Adresa");
                ret.add(s1);
                ret.add(s2);
            }
            else if(tip==3){
                sql2="SELECT * FROM `admin` WHERE Id=?;";
                ps2=conn.prepareStatement(sql2);
                ps2.setInt(1,iduser);
                rs2=ps2.executeQuery();
                rs2.first();
                s1=rs2.getString("Ime");
                s2=rs2.getString("Prezime");
                ret.add(s1);
                ret.add(s2);
            }
            return ret;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Komentar> listKom(int idogl){
        try{
            sql="SELECT * FROM `komentari` WHERE IdOglasa=?;";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,idogl);
            ResultSet rs=ps.executeQuery();
            if(rs.first())
                rs.previous();
            else
                return null;

            ArrayList<Komentar> ret=new ArrayList<>(20);
            while(rs.next()){
                int iduser=rs.getInt("IdUsera");
                String text=rs.getString("Tekst");
                ArrayList<String> als= new ArrayList<>(2);
                als=basicInfo(iduser);
                String s1=als.get(0);
                String s2=als.get(1);
                Komentar temp=new Komentar(s1,s2,text);
                ret.add(temp);
            }
            return ret;
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

















}



