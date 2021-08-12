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
                       @RequestParam(name = "remote") String remote,
                       Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija,remote);

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
        return "error";
    }



    @GetMapping("/adminIndexTrazi")
    public String adminIndex(@RequestParam(name = "pretraga") String pretraga,
                             @RequestParam(name = "kategorija") String kategorija,
                             @RequestParam(name = "podkategorija") String podkategorija,
                             @RequestParam(name = "remote") String remote,
                             Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija,remote);

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
    public String oglas(Model model,
                        @RequestParam(name = "idOglasa") int id){
        Baza baza=new Baza();
        Oglas oglas=baza.vratiOglas(id);
        if(oglas==null)
            return "404";

        ArrayList<Komentar> komentari=baza.listKom(id);
        model.addAttribute("naslov",oglas.getNaziv());
        model.addAttribute("lajkovi",oglas.getLajkovi());
        model.addAttribute("opis",oglas.getTekst());
        model.addAttribute("remote",oglas.isRemote());
        if(oglas.getPlata()==-1)
            model.addAttribute("plata","Po Dogovoru");
        else
            model.addAttribute("plata",oglas.getPlata());
        model.addAttribute("kategorija",oglas.getKategorija());
        model.addAttribute("podkategorija",oglas.getPodKategorije());
        model.addAttribute("komentari",komentari);
        model.addAttribute("datumPostavke",oglas.getDatumPostavljanja());
        model.addAttribute("datumIsteka",oglas.getDatumIsteka());
        model.addAttribute("slika",baza.encoder("files/"+oglas.getSlika()));

        return "Oglas";
    }
    @GetMapping("/oglasRadnik")
    public String oglas(Model model,
                        @RequestParam(name = "idOglasa") int id,
                        @RequestParam(name = "token") String token){
        Baza baza=new Baza();
        Oglas oglas=baza.vratiOglas(id);
        if(oglas==null)
            return "404";

        if(baza.vratiType(baza.TokenToId(token))!=1){
            return "404";
        }


        ArrayList<Komentar> komentari=baza.listKom(id);
        model.addAttribute("IdOglasa",oglas.getIdOglasa());
        model.addAttribute("naslov",oglas.getNaziv());
        model.addAttribute("lajkovi",oglas.getLajkovi());
        model.addAttribute("opis",oglas.getTekst());
        model.addAttribute("remote",oglas.isRemote());
        if(oglas.getPlata()==-1)
            model.addAttribute("plata","Po Dogovoru");
        else
            model.addAttribute("plata",oglas.getPlata());
        model.addAttribute("kategorija",oglas.getKategorija());
        model.addAttribute("podkategorija",oglas.getPodKategorije());
        model.addAttribute("komentari",komentari);
        model.addAttribute("datumPostavke",oglas.getDatumPostavljanja());
        model.addAttribute("datumIsteka",oglas.getDatumIsteka());
        model.addAttribute("slika",baza.encoder("files/"+oglas.getSlika()));

        return "OglasRadnik";
    }
    @GetMapping("/oglasPoslodavac")
    public String oglasPoslodavac(Model model,
                        @RequestParam(name = "idOglasa") int id,
                        @RequestParam(name = "token") String token){
        Baza baza=new Baza();
        Oglas oglas=baza.vratiOglas(id);
        if(oglas==null)
            return "404";

        if(baza.vratiType(baza.TokenToId(token))!=2){
            return "404";
        }


        ArrayList<Komentar> komentari=baza.listKom(id);
        model.addAttribute("IdOglasa",oglas.getIdOglasa());
        model.addAttribute("naslov",oglas.getNaziv());
        model.addAttribute("lajkovi",oglas.getLajkovi());
        model.addAttribute("opis",oglas.getTekst());
        model.addAttribute("remote",oglas.isRemote());
        if(oglas.getPlata()==-1)
            model.addAttribute("plata","Po Dogovoru");
        else
            model.addAttribute("plata",oglas.getPlata());
        model.addAttribute("kategorija",oglas.getKategorija());
        model.addAttribute("podkategorija",oglas.getPodKategorije());
        model.addAttribute("komentari",komentari);
        model.addAttribute("datumPostavke",oglas.getDatumPostavljanja());
        model.addAttribute("datumIsteka",oglas.getDatumIsteka());
        model.addAttribute("slika",baza.encoder("files/"+oglas.getSlika()));
        if(baza.daLiJeMojOglas(token,id))
            model.addAttribute("MojOglas","Prijave na moj oglas:");
        else
            model.addAttribute("MojOglas","Nije moj oglas");
        model.addAttribute("radnici",baza.prijavljeniNaOglas(token,id));





        return "OglasPoslodavac";
    }
    @GetMapping("/oglasAdmin")
    public String oglasAdmin(Model model,
                                  @RequestParam(name = "idOglasa") int id,
                                  @RequestParam(name = "token") String token){
        Baza baza=new Baza();
        Oglas oglas=baza.vratiOglas(id);
        if(oglas==null)
            return "404";

        if(baza.vratiType(baza.TokenToId(token))!=3){
            return "404";
        }


        ArrayList<Komentar> komentari=baza.listKom(id);
        model.addAttribute("IdOglasa",oglas.getIdOglasa());
        model.addAttribute("naslov",oglas.getNaziv());
        model.addAttribute("lajkovi",oglas.getLajkovi());
        model.addAttribute("opis",oglas.getTekst());
        model.addAttribute("remote",oglas.isRemote());
        if(oglas.getPlata()==-1)
            model.addAttribute("plata","Po Dogovoru");
        else
            model.addAttribute("plata",oglas.getPlata());
        model.addAttribute("kategorija",oglas.getKategorija());
        model.addAttribute("podkategorija",oglas.getPodKategorije());
        model.addAttribute("komentari",komentari);
        model.addAttribute("datumPostavke",oglas.getDatumPostavljanja());
        model.addAttribute("datumIsteka",oglas.getDatumIsteka());
        model.addAttribute("slika",baza.encoder("files/"+oglas.getSlika()));

        return "OglasAdmin";
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
                                  @RequestParam(name = "remote") String remote,
                                  Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija,remote);

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
                              @RequestParam(name = "remote") String remote,
                              Model model){
        Baza baza=new Baza();
        ArrayList<Oglas> oglasi =baza.pretOglase(pretraga,kategorija,podkategorija,remote);

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

    @GetMapping("/listaPoslodavacaTrazi")//editing
    public String listaPoslodavaca(@RequestParam(name="pretraga") String pretraga, Model model){
        Baza baza=new Baza();
        ArrayList<Poslodavac> poslodavci=baza.traziPoslodavca(pretraga);

        model.addAttribute("poslodavci",poslodavci);


        return "ListaPoslodavaca";
    }

    @GetMapping("/listaPoslodavacaRadnikTrazi")
    public String listaPoslodavacaRadnik(@RequestParam(name="pretraga") String pretraga,Model model){
        Baza baza=new Baza();
        ArrayList<Poslodavac> poslodavci=baza.traziPoslodavca(pretraga);

        model.addAttribute("poslodavci",poslodavci);


        return "ListaPoslodavacaRadnik";
    }





}
