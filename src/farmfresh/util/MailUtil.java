package farmfresh.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.*;

import java.util.Properties;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.*;

/**
 * Created by Mom and Dad on 11/11/2016.
 */
public class MailUtil {
    public static void sendMail(String to, String from,
                                String subject, String body, boolean bodyIsHTML)
            throws MessagingException {

        // 1 - get a mail session
        Properties props = new Properties();
        //props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "127.0.0.1");
        //props.put("mail.smtp.port", 25);
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // 3 - address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 4 - send the message
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }

    }


}
