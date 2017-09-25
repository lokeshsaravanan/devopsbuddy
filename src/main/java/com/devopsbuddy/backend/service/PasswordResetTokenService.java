package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import org.hibernate.annotations.ValueGenerationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokenExpirationInMinutes;

    /**The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetTokenService.class);

    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    /**
     *
     * @param email
     * @return
     */
    public PasswordResetToken createPasswordResetTokenForEmail(String email) {
       PasswordResetToken passwordResetToken = null;

       User user = userRepository.findByEmail(email);

       if(user != null)
       {
            String token = UUID.randomUUID().toString();
           LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
           passwordResetToken = new PasswordResetToken(token,user,now,tokenExpirationInMinutes);
           passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
           LOG.debug("Successfully created token for the user : "+user.getEmail());
       }
       else {
            LOG.debug("Unable to find user for email" +email);
       }

       return passwordResetToken;
    }
}
