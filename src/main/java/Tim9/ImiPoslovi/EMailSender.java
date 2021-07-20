package Tim9.ImiPoslovi;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EMailSender  {



    static public void SendEmail(String email,String Naziv,String cv)
    {
        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);

        javaMailSender.setUsername("imiposlovikg@gmail.com");
        javaMailSender.setPassword("ogbqhnhxbrmvooen");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        System.out.println(email+" "+Naziv+" "+cv);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("imiposlovikg@gmail.com");
        msg.setSubject(Naziv+" Prijava za posao");
        msg.setText(cv);

        javaMailSender.send(msg);
    }

}
