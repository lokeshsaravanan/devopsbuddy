package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.I18NService;
import com.devopsbuddy.backend.service.PasswordResetTokenService;
import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgotMyPasswordController {

    /**The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(ForgotMyPasswordController.class);

    public static final String EMAIL_ADDRESS_VIEW_NAME = "forgotmypassword/emailForm";

    public static final String FORGOT_PASSWORD_URL_MAPPING = "/forgotmypassword";
    public static final String MAIL_SENT_KEY = "mailSent";
    public static final String CHANGE_PASSWORD_PATH = "/changeuserpassword" ;

    public static final String FORGOT_PASSWORD_EMAIL_TEXT = "forgotmypassword.email.text" ;


    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private I18NService i18NService;

    @Autowired
    private EmailService emailService;

    @Value("${webmaster.email}")
    private String webmasterEmail;

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.GET)
    private String forgotPasswordGet()
    {
        return EMAIL_ADDRESS_VIEW_NAME;
    }


    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.POST)
    private String forgotPasswordPost(HttpServletRequest request, @RequestParam("email") String email, ModelMap model)
    {
        PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(email);

        if(passwordResetToken == null)
        {
            LOG.debug("Unable to find user with email "+email);
        }else
        {
            User user = passwordResetToken.getUser();
            String token= passwordResetToken.getToken().toString();

            String resetPasswordUrl = UserUtils.createPasswordResetUrl(request,user.getId(),token);

            String emailText = i18NService.getMessage(FORGOT_PASSWORD_EMAIL_TEXT,request.getLocale());

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setSubject("Forgot Password Reminder");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setFrom(webmasterEmail);

            simpleMailMessage.setText(emailText+"\r\n"+resetPasswordUrl);

            emailService.sendGenericEmailMessage(simpleMailMessage);

            LOG.debug(resetPasswordUrl);
        }

//            LOG.info("Password Token : {}",passwordResetToken.getToken());
//            LOG.info("Password User: {}",passwordResetToken.getUser().getEmail());


        model.addAttribute(MAIL_SENT_KEY,"true");
        return EMAIL_ADDRESS_VIEW_NAME;
    }

}
