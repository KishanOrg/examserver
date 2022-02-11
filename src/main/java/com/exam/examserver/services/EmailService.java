package com.exam.examserver.services;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Service
public class EmailService {
    public int sendOTP(String to){

        String host = "smtp.gmail.com";
        boolean isSent = false;
        String from = "multiplechoiceteam2021@gmail.com";

        Random random = new Random();
        int otp = random.nextInt(999999);

        String subject = "Email Verification code : " + otp;

        String message = ""
                +"<div style = 'border: 2px solid blue'>"
                +"<h3>"
                + "Multiple Choice received a request for OTP to register " + to + " account. Use "
                +"</h3>"
                +"<br>"
                +"<h1>"
                +"<center>"
                + otp
                +"<center>"
                +"</h1>"
                +"<br>"
                +"<h3>"
                +" verify your account."
                +"</h3>"
                +"<div>";


        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        //Step 1 : Create Session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("multiplechoiceteam2021@gmail.com","#mulchoice");
            }
        });

        session.setDebug(true);

        // Step 2 : Compose message
        MimeMessage mimeMessage = new MimeMessage(session);

        try{
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(message, "text/html");

            // Step 3 : send message using Transport class
            Transport.send(mimeMessage);

            System.out.println("Message Sent successfully ................");
            isSent = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        if(isSent){
            return otp;
        }else {
            return -1;
        }
    }
}

