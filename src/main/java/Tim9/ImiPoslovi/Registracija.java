package Tim9.ImiPoslovi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;


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
}
