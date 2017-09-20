package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    /**
     *
     * @param feedbackPojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    public void sendGenericEmailMessage(SimpleMailMessage emailMessage);

}
