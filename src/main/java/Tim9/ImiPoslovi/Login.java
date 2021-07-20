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
}
