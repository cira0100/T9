package Tim9.ImiPoslovi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
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
            t = (int)(Math.random() * 100) % 62;

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
                    slika=encoder("files/"+slika);
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


            EMailSender.SendEmailAttachement(email,Naziv,cv);

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
                int idkom=rs.getInt("Id");
                ArrayList<String> als= new ArrayList<>(2);
                als=basicInfo(iduser);
                String s1=als.get(0);
                String s2=als.get(1);
                Komentar temp=new Komentar(s1,s2,text,idkom);
                ret.add(temp);
            }
            return ret;
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;


    }

    public boolean daLiJeMojKom(int idkom,String token){
        int iduser=TokenToId(token);
        if(iduser==-1)
            return false;
        try{
            String sql1="SELECT * FROM `komentari` WHERE Id= ?;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,idkom);
            ResultSet rs1= ps1.executeQuery();
            if(!rs1.first())
                return false;
            if(iduser==rs1.getInt("IdUsera"))
                return true;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean brisiKom(int idkom,String token){//obavezno reloadaj listu komentara nakon brisanja
        int iduser=TokenToId(token);
        int tip=vratiType(iduser);
        if(iduser==-1)
            return false;
        try{
            if(daLiJeMojKom(idkom,token) || tip==3){
                String sql1="DELETE FROM `komentari` WHERE Id=?;";
                PreparedStatement ps1=conn.prepareStatement(sql1);
                ps1.setInt(1,idkom);
                ps1.executeQuery();
                return true;
            }
            else return false;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean brisiOgl(String token,int idogl){
        int userid=TokenToId(token);
        int tip=vratiType(userid);
        try{
            String sql1="SELECT * FROM `oglas` WHERE Id=?";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,idogl);
            ResultSet rs1=ps1.executeQuery();
            if(!rs1.first())
                return false;
        }
        catch (SQLException th){
            th.printStackTrace();
            return false;
        }
        if(daLiJeMojOglas(token,idogl) || tip==3){
            try{
                String sql2="DELETE  FROM `prijave` WHERE IdOglasa = ?";
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setInt(1,idogl);
                ps2.executeQuery();
                System.out.println("Obrisao Prijave....");

                sql2="DELETE  FROM `lajkovioglasa` WHERE IdOglasa = ?";
                ps2=conn.prepareStatement(sql2);
                ps2.setInt(1,idogl);
                ps2.executeQuery();
                System.out.println("Obrisao Lajkove....");

                String sql3="DELETE  FROM `komentari` WHERE IdOglasa=?";
                PreparedStatement ps3=conn.prepareStatement(sql3);
                ps3.setInt(1,idogl);
                ps3.executeQuery();
                System.out.println("Obrisao Komentare....");

                String sql4="DELETE  FROM `oglas` WHERE Id=?";
                PreparedStatement ps4=conn.prepareStatement(sql4);
                ps4.setInt(1,idogl);
                ps4.executeQuery();
                System.out.println("Obrisao Oglas.... ZAVRSIO");

                return true;
            }
            catch (SQLException throwables){
                throwables.printStackTrace();
                return false;
            }
        }
        else return false;


    }

    public boolean lajkDislajk(String token,int idogl){
        int iduser=TokenToId(token);
        if(iduser==-1)
            return false;
        int tip=vratiType(iduser);
        if(tip==1){
            try{
                String sql1="SELECT * FROM `lajkovioglasa` WHERE IdOglasa=? AND IdRadnika=?;";
                PreparedStatement ps1=conn.prepareStatement(sql1);
                ps1.setInt(1,idogl);
                ps1.setInt(2,iduser);
                ResultSet rs1=ps1.executeQuery();
                if(!rs1.first()){
                    String sql2="INSERT INTO `lajkovioglasa` (`IdOglasa`,`IdRadnika`) VALUES (?,?);";
                    PreparedStatement ps2=conn.prepareStatement(sql2);
                    ps2.setInt(1,idogl);
                    ps2.setInt(2,iduser);
                    ps2.executeQuery();
                    if(!updateLajk(idogl))
                        return false;
                    return true;
                }
                else{
                    String sql2="DELETE FROM `lajkovioglasa` WHERE IdOglasa=? AND IdRadnika=?;";
                    PreparedStatement ps2=conn.prepareStatement(sql2);
                    ps2.setInt(1,idogl);
                    ps2.setInt(2,iduser);
                    ps2.executeQuery();
                    if(!updateLajk(idogl))
                        return false;
                    return true;
                }

            }
            catch(SQLException th){
                th.printStackTrace();
            }
        }

        return false;
    }

    public boolean updateLajk(int idogl){
        try{
            String sql1="SELECT count(*) as broj FROM `lajkovioglasa` WHERE IdOglasa=?;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,idogl);
            ResultSet rs1=ps1.executeQuery();
            rs1.first();
            int ret=rs1.getInt("broj");
            String sql2="UPDATE `oglas` SET Lajkovi=? WHERE Id=?;";
            PreparedStatement ps2=conn.prepareStatement(sql2);
            ps2.setInt(1,ret);
            ps2.setInt(2,idogl);
            ps2.executeQuery();
            return true;
        }
        catch(SQLException th){
            th.printStackTrace();
        }
        return false;
    }

    public boolean brisiPrijaveRadnika(int iduser){
        try{
            String sql1="DELETE FROM `prijave` WHERE IdRadnika=?;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,iduser);
            ps1.executeQuery();
            return true;
        }catch (SQLException th){
            th.printStackTrace();
        }
        return false;
    }

    public void updateLajkPostDel(){
        try{
            String sql1="SELECT * FROM `oglas`;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next()){
                int idogl=rs1.getInt("Id");
                updateLajk(idogl);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public boolean brisiLajkoveRadnika(int iduser){
        try{
            String sql1="DELETE FROM `lajkovioglasa` WHERE IdRadnika=?;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,iduser);
            ps1.executeQuery();
            updateLajkPostDel();
            return true;
        }catch (SQLException th){
            th.printStackTrace();
        }
        return false;
    }

    public boolean brisiUsera(int iduser,String token){
        int tipobrisanog=vratiType(iduser);
        int adminId=TokenToId(token);
        int tip=vratiType(adminId);
        if(tip!=3)
            return false;
        if(tipobrisanog==2){

           ArrayList<Oglas> oglasi= oglasiPoslodavca(iduser);
           if(oglasi!=null)
           for(Oglas oglas : oglasi)
               brisiOgl(token,oglas.getIdOglasa());

            ArrayList<Komentar> komentari=listSveKom(iduser);
            if(komentari!=null)
            for(Komentar komentar:komentari)
                brisiKom(komentar.getId(),token);

                try {
                    sql="DELETE FROM `poslodavac` WHERE Id=?;";
                    PreparedStatement ps=conn.prepareStatement(sql);
                    ps.setInt(1,iduser);
                    ps.executeQuery();
                    sql="DELETE FROM `glavna` WHERE Id=?;";
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1,iduser);
                    ps.executeQuery();
                    return true;



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return false;




        }else if(tipobrisanog==1){
            brisiPrijaveRadnika(iduser);
            brisiLajkoveRadnika(iduser);
            ArrayList<Komentar> kometari=listSveKom(iduser);
            if(kometari!=null)
            for(Komentar komentar:kometari)
                brisiKom(komentar.getId(),token);
            try {
                sql="DELETE FROM `radnik` WHERE Id=?;";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1,iduser);
                ps.executeQuery();
                sql="DELETE FROM `glavna` WHERE Id=?;";
                ps=conn.prepareStatement(sql);
                ps.setInt(1,iduser);
                ps.executeQuery();
                return true;



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;

        }else
            return false;
    }

    public ArrayList<Komentar> listSveKom(int idKorisnika){
        try{
            sql="SELECT * FROM `komentari` WHERE IdUsera=?;";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,idKorisnika);
            ResultSet rs=ps.executeQuery();
            if(rs.first())
                rs.previous();
            else
                return null;

            ArrayList<Komentar> ret=new ArrayList<>(20);
            while(rs.next()){
                int iduser=rs.getInt("IdUsera");
                String text=rs.getString("Tekst");
                int idkom=rs.getInt("Id");
                ArrayList<String> als= new ArrayList<>(2);
                als=basicInfo(iduser);
                String s1=als.get(0);
                String s2=als.get(1);
                Komentar temp=new Komentar(s1,s2,text,idkom);
                ret.add(temp);
            }
            return ret;
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;


    }
    public boolean brisiUsera(String token){
        int iduser=TokenToId(token);
        int tipobrisanog=vratiType(iduser);
        if(tipobrisanog==2){

            ArrayList<Oglas> oglasi= oglasiPoslodavca(iduser);
            if(oglasi!=null)
            for(Oglas oglas : oglasi)
                brisiOgl(token,oglas.getIdOglasa());

            ArrayList<Komentar> komentari=listSveKom(iduser);
            if(komentari!=null)
            for(Komentar komentar:komentari)
                brisiKom(komentar.getId(),token);

                try {
                    sql="DELETE FROM `poslodavac` WHERE Id=?;";
                    PreparedStatement ps=conn.prepareStatement(sql);
                    ps.setInt(1,iduser);
                    ps.executeQuery();
                    sql="DELETE FROM `glavna` WHERE Id=?;";
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1,iduser);
                    ps.executeQuery();
                    return true;



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return false;




        }else if(tipobrisanog==1){
            brisiPrijaveRadnika(iduser);
            brisiLajkoveRadnika(iduser);
            ArrayList<Komentar> kometari=listSveKom(iduser);
            if(kometari!=null)
            for(Komentar komentar:kometari)
                brisiKom(komentar.getId(),token);
            try {
                sql="DELETE FROM `radnik` WHERE Id=?;";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1,iduser);
                ps.executeQuery();
                sql="DELETE FROM `glavna` WHERE Id=?;";
                ps=conn.prepareStatement(sql);
                ps.setInt(1,iduser);
                ps.executeQuery();
                return true;



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;

        }else
            return false;

    }





    public ArrayList<Podaci> listUsers(String token){
        int idadmina=TokenToId(token);
        if(idadmina==-1)
            return null;
        int tipchk=vratiType(idadmina);
        if(tipchk!=3)
            return null;
        try{
            ArrayList<Podaci> pod=new ArrayList<>(20);
            String sql1="SELECT * FROM `glavna` WHERE Type !=3;";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next()) {
                int id = rs1.getInt("Id");
                String usr = rs1.getString("Username");
                String mail = rs1.getString("Mail");
                Podaci temp = new Podaci(id, usr, mail);
                pod.add(temp);
            }
            if(pod.isEmpty())
                return null;
            return pod;
        }
        catch(SQLException th){
            th.printStackTrace();
        }

        return null;
    }
    public ArrayList<Oglas> sviOglasi(){
        sql="SELECT * FROM `oglas` ;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
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

    public ArrayList<String> listKat(){
        String sql1="SELECT * FROM `kategorije`;";
        try{
            PreparedStatement ps=conn.prepareStatement(sql1);
            ResultSet rs=ps.executeQuery();
            ArrayList<String> ret=new ArrayList<>(50);
            if(!rs.first())
                return null;
            else
                rs.previous();
            while(rs.next()){
                String tmp=rs.getString("Naziv");
                ret.add(tmp);
            }
            return ret;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> listPodKat(String kat){
        String sql1="SELECT * FROM `kategorije` WHERE `Naziv`=?;";
        try{
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setString(1,String.valueOf(kat));
            ResultSet rs1=ps1.executeQuery();
            int idkat;
            if(!rs1.first())
                return null;
            else
                idkat=rs1.getInt("Id");
            String sql2="SELECT * FROM `podkategorije` WHERE `IdKategorije`=?;";
            PreparedStatement ps2=conn.prepareStatement(sql2);
            ps2.setInt(1,idkat);
            ResultSet rs2=ps2.executeQuery();
            if(!rs2.first())
                return null;
            else
                rs2.previous();
            ArrayList<String> ret=new ArrayList<>(50);
            while(rs2.next()){
                String tmp=rs2.getString("Naziv");
                ret.add(tmp);
            }
            return ret;

        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<Oglas> pretOglase(String naziv,String kat,String podkat){
        ArrayList<Oglas> svi=sviOglasi();
        ArrayList<Oglas> filtered=new ArrayList<>(50);
        if(svi==null)
            return null;
        Pattern patNaziv=Pattern.compile(naziv,Pattern.CASE_INSENSITIVE);
        Pattern patKat=Pattern.compile(kat,Pattern.CASE_INSENSITIVE);
        Pattern patPodKat=Pattern.compile(podkat,Pattern.CASE_INSENSITIVE);
        for (Oglas ogl:svi) {
            Matcher matchNaziv=patNaziv.matcher(ogl.getNaziv());
            Matcher matchKat=patKat.matcher(ogl.getKategorija());
            Matcher matchPodKat=patPodKat.matcher(ogl.getPodKategorije());
            if(naziv=="" || matchNaziv.find()){
                if(kat=="Svi" || matchKat.find()){
                    if(podkat=="Svi" || matchPodKat.find()){
                        filtered.add(ogl);
                    }
                }
            }
        }
        if(filtered.isEmpty())
            return null;

        return filtered;
    }

    public String encoder(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }


    public Oglas vratiOglas(int id){
        sql="SELECT * FROM `oglas` WHERE Id = ? ;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                rs.previous();
            } else {
                return null;
            }
            Oglas temp=null;
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

                temp= new Oglas(IdOglasa,Naziv,idPoslodavca,DatumPostavljanja,DatumIsteka,Lajkovi,Posete,Kategorija,PodLategorije,plata,Slika,Tekst,Remote);

            }
            return temp;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

}



