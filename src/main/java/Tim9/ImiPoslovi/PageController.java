package Tim9.ImiPoslovi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.sviOglasi();

    model.addAttribute("oglasi",oglasi);
    model.addAttribute("kategorije",baza.listKat());




        return "index";
    }
    @GetMapping("/trazi")
    public String home(@RequestParam(name = "pretraga") String pretraga,
                       @RequestParam(name = "kategorija") String kategorija,
                       @RequestParam(name = "podkategorija") String podkategorija,
                       Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija);

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());




        return "index";
    }



    @GetMapping("/listaPoslodavaca")
    public String listaPoslodavaca(Model model){
        Baza baza=new Baza();
        ArrayList<Poslodavac> poslodavci=baza.traziPoslodavca("");

        model.addAttribute("poslodavci",poslodavci);


        return "ListaPoslodavaca";
    }



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
    @GetMapping("/oglas")
    public String oglas(){
        return "Oglas";
    }





}
