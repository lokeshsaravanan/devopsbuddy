package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.web.controllers.ForgotMyPasswordController;
import com.devopsbuddy.web.domain.frontend.BasicAccountPayload;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Non Instantiable!!");
    }

    /**
     *
     * @param userName
     * @param email
     * @return A user entity
     */
    public static User createBasicUser(String userName, String email)
    {
        User user = new User();
        user.setUsername(userName);
        user.setFirstName("LOKESH");
        user.setLastName("RAMAMOORTHI");
        user.setPassword("lokeshr");
        user.setCountry("IN");
        user.setDescription("Lokeshr Learning and testing");
        user.setEnabled(true);
        user.setEmail(email);
        return user;
    }

    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        String passwordResetURL = request.getScheme()+ "://"+ request.getServerName()+":"+
                                    request.getServerPort()+request.getContextPath()+ ForgotMyPasswordController.CHANGE_PASSWORD_PATH+
                                    "?id="+userId+
                                    "&token="+token;
        return passwordResetURL;
    }

    public static <T extends BasicAccountPayload> User fromWebUserToDomainUser(T frontendPayload) {
        User user = new User();
        user.setUsername(frontendPayload.getUsername());
        user.setPassword(frontendPayload.getPassword());
        user.setFirstName(frontendPayload.getFirstName());
        user.setLastName(frontendPayload.getLastName());
        user.setEmail(frontendPayload.getEmail());
        user.setPhoneNumber(frontendPayload.getPhoneNumber());
        user.setCountry(frontendPayload.getCountry());
        user.setEnabled(true);
        user.setDescription(frontendPayload.getDescription());

        return user;
    }
}
