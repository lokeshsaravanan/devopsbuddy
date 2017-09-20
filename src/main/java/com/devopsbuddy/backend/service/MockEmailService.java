package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService  {

    /**The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
    
    @Override
    public void sendGenericEmailMessage(SimpleMailMessage emailMessage) {
        LOG.debug("Sending Mock EMAIL Service");
        LOG.info(emailMessage.toString());
        LOG.debug("Email Sent");

    }
}
