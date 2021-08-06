package Tim9.ImiPoslovi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


@RestController
public class Registracija {
    @GetMapping ("/registrujRadnika")
    public boolean registrujRadnika(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                            @RequestParam(name = "mail") String mail,
                            @RequestParam(name = "ime") String ime,
                            @RequestParam(name = "prezime") String prezime,
                            @RequestParam(name = "date") String date,
                            @RequestParam(name = "slika") String slika
                            )
    {
        Date date1=Date.valueOf(date);
        Korisnik korisnik=new Radnik(username,password,mail,1,ime,prezime,date1,slika);
        Baza baza=new Baza();
        boolean uspeh = false;
        try {
            uspeh = baza.registruj(korisnik);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return uspeh;
    }
    @GetMapping("/registrujPoslodavca")
    public boolean registrujPoslodavca(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "mail") String mail,
                                   @RequestParam(name = "naziv") String naziv,
                                   @RequestParam(name = "adresa") String adresa,
                                   @RequestParam(name = "slika") String slika)
    {
        Korisnik korisnik=new Poslodavac(username,password,mail,2,naziv,adresa,slika);
        Baza baza=new Baza();
        boolean uspeh = false;
        try {
            uspeh = baza.registruj(korisnik);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return uspeh;
    }
    @GetMapping("/registrujAdmina")
    public boolean registrujAdmina(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "password") String password,
                                      @RequestParam(name = "mail") String mail,
                                      @RequestParam(name = "ime") String ime,
                                      @RequestParam(name = "prezime") String prezime,
                                      @RequestParam(name = "token") String token)
    {
        Korisnik korisnik=new Admin(username,password,mail,3,ime,prezime);

        Baza baza=new Baza();
        boolean uspeh = false;
        try {
            uspeh = baza.registruj(korisnik,token);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return uspeh;
    }
    @GetMapping("/registrujOglas")
    public boolean registrujOglas(@RequestParam(name = "naziv") String naziv,
                                   @RequestParam(name = "datum") String datum,
                                   @RequestParam(name = "kategorija") String kategorija,
                                  @RequestParam(name = "podkategorija") String podkategorija,
                                   @RequestParam(name = "plata") int plata,
                                   @RequestParam(name = "slika") String slika,
                                   @RequestParam(name = "tekst") String tekst,
                                   @RequestParam(name = "remote") boolean remote,
                                  @RequestParam(name = "token") String token
                                  )
    {
        Baza baza=new Baza();
        int idPoslodavca=baza.TokenToId(token);
        Date date1=Date.valueOf(datum);
        Oglas oglas=new Oglas(naziv,idPoslodavca,date1,kategorija,podkategorija,plata,slika,tekst,remote);


        boolean uspeh = false;
        uspeh = baza.postOglas(token,oglas);

        return uspeh;
    }
    @GetMapping("/vratiTip")
    public int vratiTip(@RequestParam(name = "token") String token
    ){
        Baza baza=new Baza();
        int id=baza.TokenToId(token);
        return baza.vratiType(id);
    }
    @GetMapping("/getPodKategorije")
    public ArrayList<String> getPodKategorije(@RequestParam(name = "kategorija") String kategorija
    ){
        Baza baza=new Baza();
        return baza.listPodKat(kategorija);
    }

    @GetMapping("/lajkujOglas")
    public boolean lajkujOglas(@RequestParam(name = "token") String token,
                                    @RequestParam(name = "idOgl") int idOgl
    ){
        boolean uspeh=false;

        Baza baza=new Baza();
        uspeh=baza.lajkDislajk(token,idOgl);
        return uspeh;

    }
    @GetMapping("/postKom")
    public boolean postKom(@RequestParam(name = "token") String token,
                                    @RequestParam(name = "idOgl") int idOgl,
                                    @RequestParam(name = "tekst") String tekst
    ){
        boolean uspeh=false;

        Baza baza=new Baza();
        uspeh=baza.postKom(token,idOgl,tekst);
        return uspeh;

    }

    @GetMapping("/prijavaOglas")
    public boolean prijavaOglas(@RequestParam(name = "token") String token,
                           @RequestParam(name = "idOgl") int idOgl,
                           @RequestParam(name = "tekst") String cv
    ){
        boolean uspeh=false;

        Baza baza=new Baza();
        uspeh=baza.prijaveMeNaOglas(token,cv,idOgl);
        return uspeh;

    }





}
