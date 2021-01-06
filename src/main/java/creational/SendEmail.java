package creational;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static void main(String[] args) {
        // Put recipient’s address
        String to = "ranjan@yopmail.com";

        // from gmail
//        String from = "from@example.com";
//        final String username = "notificationslpo@gmail.com";
//        final String password = "lpo@12345";
//        String host = "smtp.gmail.com";


        // from outlook
        String from = "Accounts@LifePlanOrganizer.com";
        final String username = "Accounts@LifePlanOrganizer.com";
        final String password = "Monitor3744!";
        String host = "smtp.office365.com";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");//it’s optional in Mailtrap
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("My first message bro");

            // Put the content of your message
            message.setText("Hi there, this is my bro");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

