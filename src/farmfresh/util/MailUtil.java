package farmfresh.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.*;

import java.util.Properties;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.*;

/**
 * Purpose: To Send an Email Message
 *
 * @author Benard Pacho developed the logic  Amy Radtke commented the code.
 */
public class MailUtil {

    /**
     * Builds the Email Message and Sends it
     * @param to Who the email is to
     * @param from Who the email is from
     * @param subject email Subject
     * @param body body of email
     * @param bodyIsHTML flag indicating if the body is in HTML format
     * @throws MessagingException re-thrown if issues occur during call to Transport.send(message)
     */
    public static void sendMail(String to, String from,
                                String subject, String body, boolean bodyIsHTML)
            throws MessagingException {

        // Create a Mail Session
        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("amy@roofreelancing.com", "k3rVE3VNV3h4");

                        //return new PasswordAuthentication("amy.freelance.dev@gmail.com", "free6312");
                    }
                });

//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", "localhost");
//        props.put("mail.smtp.host", "127.0.0.1"); --- this was the only line not commented out in old logic
//        props.put("mail.smtp.port", 25);
//        Session session = Session.getDefaultInstance(props);

        // Turn Debugging On
        session.setDebug(true);

        // Create the Message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // Address the Message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // Send the Message
        try {
            //Transport hangs - last msg:  trying to connect to host "127.0.0.1", port 25, isSSL false
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }

    }//End - sendMail()

}//End - MailUtil.java
