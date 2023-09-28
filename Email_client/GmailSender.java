package Email_client;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSender {
    private String userEmail;
    private String password;

    public GmailSender(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    // creates a session to send gmails from a given account
    // userEmail and password of the created object are used
    // for authentication
    public Session getGmailSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, password);
            }
        });
    }

    // sends a mail to a given receiver
    public void sendMail(Session session, String recipientEmail, String subject, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}