package co.edu.unbosque.livingcorp.service;

import java.util.List;
import java.util.Properties;

import co.edu.unbosque.livingcorp.model.dto.*;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

public class NotificationService {
    private static final Logger logger = Logger.getLogger(LogService.class);

    public NotificationService() {
    }

    public void send(String to, String subject, String content) {

        String from = "jriveraca@unbosque.edu.co";
        final String username = "jriveraca@unbosque.edu.co";
        final String password = "qxph iwpm ccnb tqfr";
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        logger.info("Autenticacion");
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
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    public void notificationBooking(ResourceBookingDto resourceBookingDto, UserDto userDto, double tiempoReserva){
        String to = userDto.getEmailUser();
        String subject = "Reserva hecha";
        String content = "Buen día,\n" +
                "Su fecha de la reserva es: " + resourceBookingDto.getBookingStartDate()+".\n"+
                "El tipo de servicio adquirido: "+ resourceBookingDto.getPropertyResourceId().getResId().getType()+".\n"+
                "El residente responsable: " + resourceBookingDto.getUserName()+ ".\n"+
                "El tiempo de reserva: "+tiempoReserva+ ".\n\n\n"+
                "Atentamente la administracion.";
        logger.info(content);
        send(to, subject, content);
    }
    public void notificationResource(List<String> correos, PropertyResourceDto resourceDto){
        for(String correo : correos){
            String to = correo;
            String subject = "Creacion de Recurso";
            String content = "Buen día,\n" +
                    "se ha creado el siguiente recurso en la propiedad: \n"+
                    "Recurso: "+resourceDto.getResId().getType()+ ".\n\n\n"+
                    "Atentamente la administracion.";
            logger.info(content);

            send(to, subject, content);

        }
    }
    public void notificationRequest(ServiceRequestDto serviceRequestDto){
        String to = serviceRequestDto.getSvcProviderId().getProviderEmail();
        String subject = "Solicitud de servicio";
        String content = "Buen día,\n" +
                "Servicio a la propiedad: " + serviceRequestDto.getPropertyId().getNameProperty()+".\n"+
                "Al residente: "+ serviceRequestDto.getUserName().getNameUser()+".\n"+
                "El servicio: " + serviceRequestDto.getSvcProviderId().getServiceType()+ ".\n"+
                "Fecha del servicio: "+serviceRequestDto.getSvcDateTime()+ ".\n\n\n"+
                "Atentamente la administracion.";
        logger.info(content);
        send(to, subject, content);
    }
    public void notificationService(ServiceRFQDto serviceRFQDto){
        String to = serviceRFQDto.getUserName().getEmailUser();
        String subject = "Solicitud de servicio de transporte";
        String content = "Buen día,\n" +
                "su servicio de trasporte ha sido recibido exitosamente.\n"+
                "Al residente: "+ serviceRFQDto.getUserName().getNameUser()+".\n"+
                "El servicio: " + serviceRFQDto.getSvcProviderId().getServiceType()+ ".\n"+
                "Fecha del servicio: "+serviceRFQDto.getRfqDateTime()+ ".\n\n\n"+
                "Atentamente la administracion.";
        logger.info(content);
        send(to, subject, content);
    }
}
