package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Non Instantiable!!");
    }

    public static User createBasicUser()
    {
        User user = new User();
        user.setUsername("tester");
        user.setFirstName("LOKESH");
        user.setLastName("RAMAMOORTHI");
        user.setPassword("lokeshr");
        user.setCountry("IN");
        user.setDescription("Lokeshr Learning and testing");
        user.setEnabled(true);
        return user;
    }
}
