package co.edu.unbosque.livingcorp.model.entity;

import java.util.Properties;

import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceBookingDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Notification {

    public Notification() {
    }

    public void send(String to, String subject, String content) {

        String from = "jriveraca@unbosque.edu.co";
        final String username = "jriveraca@unbosque.edu.co";
        final String password = "osuk jxct sjpi qols";
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void MsnResident(ResourceBookingDto resourceBookingDto, UserDto userDto){
        String to = userDto.getEmailUser();
        String subject = "Confirmación de Reserva";
        String content =

    }
}
