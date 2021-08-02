package Tim9.ImiPoslovi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Login {
    @GetMapping("/login")
    public String Login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password){
        Baza baza=new Baza();
        String ret=null;
        ret=baza.Login(username,password);
        if(ret !=null)
            return ret;
        else
            return ret;
    }

    @GetMapping("/tokenTotype")
    public int TokenTotype(@RequestParam(name = "token") String token){
        int ret=-1;
        if(token=="")
            return ret;
        Baza baza=new Baza();
        int id =baza.TokenToId(token);
        if(id==-1)
            return ret;
        ret= baza.vratiType(id);

        return ret;
    }

}
