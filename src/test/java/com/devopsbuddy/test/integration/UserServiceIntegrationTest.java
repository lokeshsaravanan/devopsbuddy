package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateNewUser() throws Exception{
        Set<UserRole> userRoles = new HashSet<>();

        User basicUser = UsersUtils.createBasicUser();
        userRoles.add(new UserRole(basicUser,new Role(RolesEnum.BASIC)));

        //System Printing on the console.
        //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        User user = userService.createUser(basicUser, PlansEnum.BASIC,userRoles);

        //System Printing on the console.
        //System.out.println("#######################################################################################");

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
     }
}
