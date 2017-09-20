package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService  {

    /**The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;



    @Override
    public void sendGenericEmailMessage(SimpleMailMessage emailMessage) {
        LOG.debug("Sending Mock EMAIL Service");
        mailSender.send(emailMessage);
        LOG.debug("Email Sent");

    }
}
