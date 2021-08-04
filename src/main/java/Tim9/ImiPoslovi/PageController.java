package Tim9.ImiPoslovi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
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



    @GetMapping("/adminIndexTrazi")
    public String adminIndex(@RequestParam(name = "pretraga") String pretraga,
                             @RequestParam(name = "kategorija") String kategorija,
                             @RequestParam(name = "podkategorija") String podkategorija,
                             Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija);

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());

        return "AdminIndex";
    }
    @GetMapping("/adminIndex")
    public String adminIndex(Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.sviOglasi();

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());



        return "AdminIndex";
    }

    @GetMapping("/listaKorisnika")
    public String listaKorisnika(Model model,
                                @RequestParam(name = "token") String token){
        Baza baza=new Baza();
        ArrayList<Podaci> podaci=baza.listUsers(token);

        model.addAttribute("podaci",podaci);


        return "ListaKorisnika";

    }



    @GetMapping("/dodajAdmina")
    public String dodajAdmina(){
        return "DodajAdmina";
    }



    @GetMapping("/oglas")
    public String oglas(){
        return "Oglas";
    }


    @GetMapping("/poslodavacIndex")
    public String poslodavacIndex(Model model){

        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.sviOglasi();

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());
        return "PoslodavacIndex";

    }
    @GetMapping("/poslodavacIndexTrazi")
    public String poslodavacIndex(@RequestParam(name = "pretraga") String pretraga,
                                  @RequestParam(name = "kategorija") String kategorija,
                                  @RequestParam(name = "podkategorija") String podkategorija,
                                  Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija);

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());
        return "PoslodavacIndex";

    }

    @GetMapping("/mojiOglasi")
    public String mojiOglasi(Model model,@RequestParam(name = "token") String token){

        Baza baza=new Baza();
        ArrayList <Oglas> oglasi =baza.oglasiPoslodavca(token);
        model.addAttribute("oglasi",oglasi);

        return "MojiOglasi";

    }
    @GetMapping("/dodajOglas")
    public String dodajOglas(Model model){

        Baza baza=new Baza();
        model.addAttribute("kategorije",baza.listKat());

        return "DodajOglas";
    }

    @GetMapping("/radnikIndexTrazi")
    public String radnikIndex(@RequestParam(name = "pretraga") String pretraga,
                              @RequestParam(name = "kategorija") String kategorija,
                              @RequestParam(name = "podkategorija") String podkategorija,
                              Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija);

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());

        return "RadnikIndex";
    }
    @GetMapping("/radnikIndex")
    public String radnikIndex(Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.sviOglasi();

        model.addAttribute("oglasi",oglasi);
        model.addAttribute("kategorije",baza.listKat());



        return "RadnikIndex";
    }

    @GetMapping("/listaPoslodavacaRadnik")
    public String listaPoslodavacaRadnik(Model model){
        Baza baza=new Baza();
        ArrayList<Poslodavac> poslodavci=baza.traziPoslodavca("");

        model.addAttribute("poslodavci",poslodavci);


        return "ListaPoslodavacaRadnik";
    }

    @GetMapping("/mojePrijave")
    public String mojePrijave(@RequestParam(name="token") String token,Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi=baza.mojePrijaveOglasa(token);
        model.addAttribute("oglasi",oglasi);

        return "MojePrijave";
    }




}
