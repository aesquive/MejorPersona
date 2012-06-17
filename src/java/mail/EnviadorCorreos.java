package mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviadorCorreos {

    private final String puerto ;
    private final String host;
    private final String remitente;
    private final String password;
    private Properties propiedades;
    private Session sesion;

    public static void main(String[] args) {
    }

    private String obtenerHTML(String[] cuerpo){
        String cabecera="<table style=\"border-color:#006\" width=\"600\" border=\"0\">"
                + "<tr style=\"background-color:\">"
                + "<th scope=\"col\"><font face=\"Chiller\"><img src=\"http://www.mejorpersona.com.mx/LogoMejorPersona.png\" width=\"150\" height=\"150\" /></font></th>"
                + "</tr>"
                + "<tr>"
                + "<td>";
        String fin="</td>"
                + "</tr>"
                + "<tr>"
                + "<td><a href=\"http://www.mejorpersona.com.mx\">www.mejorpersona.com.mx</a></td>"
                + "</tr>"
                + "</table>";

        String centro="";
        for(String s:cuerpo){
            centro+="<p style=\"color:#003\"><font>"+s+"</font></p>";
        }
                                
        return cabecera+centro+fin;
    }
    
    public EnviadorCorreos(String host , final String remitente , final String password ,final String puerto) {
        this.puerto=puerto;
        this.host = host;
        this.remitente = remitente;
        this.password = password;
        propiedades = new Properties();
        ponerPropiedades();
        sesion = Session.getInstance(propiedades, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });
    }

    public boolean enviar(String[] destinatarios, String subject, String[] mensaje) {
        try {
            MimeMessage message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(remitente));
            agregarDestinatarios(message, destinatarios);

            message.setSubject(subject);
            message.setContent(obtenerHTML(mensaje), "text/html");
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(EnviadorCorreos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private void agregarDestinatarios(MimeMessage message, String[] destinatarios) {
        for (String s : destinatarios) {
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
            } catch (MessagingException ex) {
                Logger.getLogger(EnviadorCorreos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void ponerPropiedades() {

        propiedades.put("mail.smtp.host", host);
        propiedades.put("mail.smtp.user", remitente);
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.password", password);
        propiedades.setProperty("mail.smtp.port", puerto);
    }
}
