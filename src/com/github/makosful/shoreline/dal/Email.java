package com.github.makosful.shoreline.dal;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Axl
 */
public class Email
{

    private final String recipient;
    private final String title;
    private final String message;

    public Email(String recepient, String title, String message)
    {
        this.recipient = recepient;
        this.title = title;
        this.message = message;
    }

    public boolean sendMail() throws MessagingException
    {
        final String from = "NullShoreline@gmail.com";
        final String pass = "VwnfU4Tg";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");

        Session session = Session.getDefaultInstance(
                props, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, pass);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        msg.setSubject(title);
        msg.setContent(message, "text/html");

        Transport.send(msg);

        return true;
    }
}
