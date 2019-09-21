/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
    //creates framework for email functionality
    //externally sourced code
    public static void send(String from, String password, String to, String subject, String message) {

        Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
            
        try {
            MimeMessage mess = new MimeMessage(session);
            mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mess.setSubject(subject);
            mess.setText(message);
            Transport.send(mess);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        Email.send("sjcrollcall@gmail.com","SJCrollcall1?","23723@stjohnscollege.co.za","Test1","How r u?");    
    }
} 

