package com.flexPerk.flexCore.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class JavaxLinkGenerator implements RegistrationLinkGenerator {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Override
    public  String generateRegistrationLink() {
        // Generate a unique token using UUID
        String verificationToken = UUID.randomUUID().toString();

        // Example registration link format: https://yourwebsite.com/register?token=<verificationToken>
        return "https://yourwebsite.com/register?token=" + verificationToken;
    }

    @Override
    public void sendRegistrationEmail(String recipientEmail, String registrationLink) {

        // Set up email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Registration Link");
            message.setText("Hello Employer,\n\nHere is your registration link:\n\n" + registrationLink);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
