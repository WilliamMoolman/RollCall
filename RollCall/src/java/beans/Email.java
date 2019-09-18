/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author moolm
 */
public class Email {

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
            MimeMessage mes = new MimeMessage(session);
            mes.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mes.setSubject(subject);
            mes.setText(message);
            //send message  
            Transport.send(mes);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        Email.send("sjcrollcall@gmail.com","SJCrollcall1?","23723@stjohnscollege.co.za","Test1","How r u?");    
    }
} 
// 
