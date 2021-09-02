/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.io;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that's tool to send an email.
 * 
 * @Author: METIVIER Nicolas <nicolas.a.metivier@gmail.com>
 * @Date:   2020-08-25 23:42:29
 * @Last Modified by: nicolas.a.metivier@gmail.com
 * @Last Modified time: 2016-08-09 13:29:41
 * @since 1.0.0
 * @serial 20200902152723
 */
public final class EmailBroker {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailBroker.class);
    
    /**
     * Send an email.
     * @param subject Subject of the mail.
     * @param text Content of the mail.
     * @param destinations List of emails destinations.
     */
    public static final void sendEmail(final String subject, final String text, final String... destinations) {
        LOGGER.info("EmailBroker is call to send a email...");
        final String username = "nicolas.a.metivier@gmail.com";
        final String password = "zboclhfqcqvgvjex";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        for (String to : destinations) {
            LOGGER.info(String.format("An email will be send to: %s", to));
        }

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            String destination = "";
            for (String address : destinations) {
                destination = destination.concat(address).concat(", ");
            }
            destination = destination.substring(0, destination.length()-2);
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destination)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        LOGGER.info("EmailBroker has finished it's task.");
    }
}
