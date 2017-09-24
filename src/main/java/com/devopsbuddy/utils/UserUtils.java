package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

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
}
