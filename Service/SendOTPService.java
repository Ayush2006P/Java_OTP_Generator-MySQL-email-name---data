package Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendOTPService {
    public static void sendOTP(String email, String genOTP) {
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID
        String from = "ayush.movingdigits@gmail.com";  // Use your own email

        // Assuming you are sending email through Gmail's SMTP
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server properties
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object and authenticate with username and password
        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "tzzgazxvvtvioumo"); //email password
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the "From" header field
            message.setFrom(new InternetAddress(from));

            // Set the "To" header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set the subject
            message.setSubject("Your OTP");

            // Set the actual message
            message.setText("Your One Time Password (OTP) is: " + genOTP);

            System.out.println("Sending...");

            // Send the message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
