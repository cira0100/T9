package Tim9.ImiPoslovi;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.URL;
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
     static public void SendEmailAttachement(String email,String Naziv,String fileToAttach){
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

        MimeMessage message = javaMailSender.createMimeMessage();

         MimeMessageHelper helper = null;
         try {
             helper = new MimeMessageHelper(message, true);
             helper.setFrom("imiposlovikg@gmail.com");
             helper.setTo(email);
             helper.setSubject(Naziv);

             FileSystemResource file = new FileSystemResource(new File(fileToAttach));
             helper.addAttachment("cv.pdf", file);
             helper.setText("", true);

             javaMailSender.send(message);

         } catch (MessagingException e) {
             e.printStackTrace();
         }



    }

}
