package Tim9.ImiPoslovi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    @GetMapping("/")
    public String home(){ return "index"; }
    @GetMapping("/login1")
    public String login(){
        return "login";
    }
    @GetMapping("/register1")
    public String register(){
        return "register";
    }
    @GetMapping("/error")
    public String error(){
        return "404";
    }
    @GetMapping("/radnikIndex")
    public String radnikIndex(){
        return "RadnikIndex";
    }
    @GetMapping("/poslodavacIndex")
    public String poslodavacIndex(){
        return "PoslodavacIndex";
    }
    @GetMapping("/adminIndex")
    public String adminIndex(){
        return "AdminIndex";
    }
    @GetMapping("/listaKorisnika")
    public String listaKorisnika(){
        return "ListaKorisnika";
    }
    @GetMapping("/dodajAdmina")
    public String dodajAdmina(){
        return "DodajAdmina";
    }
    @GetMapping("/listaPoslodavaca")
    public String listaPoslodavaca(){
        return "ListaPoslodavaca";
    }
    @GetMapping("/listaPoslodavacaRadnik")
    public String listaPoslodavacaRadnik(){
        return "ListaPoslodavacaRadnik";
    }
    @GetMapping("/mojePrijave")
    public String mojePrijave(){
        return "MojePrijave";
    }
    @GetMapping("/mojiOglasi")
    public String mojiOglasi(){
        return "MojiOglasi";
    }
    @GetMapping("/dodajOglas")
    public String dodajOglas(){
        return "DodajOglas";
    }


}
