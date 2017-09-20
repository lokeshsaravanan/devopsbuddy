package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.to.address}")
    private String defaultToAddress;
    /**
     *
     * @param feedbackPojo
     * @return
     */
    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedbackPojo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(defaultToAddress);
        simpleMailMessage.setFrom(feedbackPojo.getEmail());
        simpleMailMessage.setSubject("Feedback from "+feedbackPojo.getFirstName()+" "+feedbackPojo.getLastName());
        simpleMailMessage.setText(feedbackPojo.getFeedback());

        return simpleMailMessage;
    }

    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
    }
}
