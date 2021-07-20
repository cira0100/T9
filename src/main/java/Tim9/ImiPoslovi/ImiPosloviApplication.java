package Tim9.ImiPoslovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;

@SpringBootApplication
public class ImiPosloviApplication {

	@Autowired
	private EMailSender service;

	public static void main(String[] args) {
		SpringApplication.run(ImiPosloviApplication.class, args);
	Baza baza=new Baza();

	System.out.println(baza.prijaveMeNaOglas("BlqtgP1rMn3ufgFv1i3b5aVWxgVYgz9A","CV",2));

}



}