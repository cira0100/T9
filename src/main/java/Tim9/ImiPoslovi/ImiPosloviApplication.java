package Tim9.ImiPoslovi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication
public class ImiPosloviApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImiPosloviApplication.class, args);
	Baza baza=new Baza();
	/*baza.brisiUsera(29,"uktW42mck429V22bdXgeco3IcTta6kH");
	baza.brisiUsera(31,"uktW42mck429V22bdXgeco3IcTta6kH");
	baza.brisiUsera(33,"uktW42mck429V22bdXgeco3IcTta6kH");
	baza.brisiUsera("zckhzBBbGqumpvgHvdIV9p9g6bbzbahq");*/
	if(String.valueOf(true)=="true"){
		System.out.println("tacno");
	}

}



}