package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

public class UsersUtils {

    private UsersUtils() {
        throw new AssertionError("Non Instantiable!!");
    }

    public static User createBasicUser()
    {
        User user = new User();
        user.setUsername("BASIC_USER");
        user.setFirstName("LOKESH");
        user.setLastName("RAMAMOORTHI");
        user.setEnabled(true);
        return user;
    }
}
