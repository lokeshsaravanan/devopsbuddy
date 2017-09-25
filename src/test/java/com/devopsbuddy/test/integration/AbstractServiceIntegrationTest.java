package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractServiceIntegrationTest {

    @Autowired
    protected UserService userService;


    protected User createUser(TestName testName) {

        User basicUser = UserUtils.createBasicUser(testName.getMethodName(),testName.getMethodName()+"@lokeshr.com");

        //System Printing on the console.
        System.out.println(testName.getMethodName()+" ###################################");

        Set<UserRole> userRoles = new HashSet<>();

        userRoles.add(new UserRole(basicUser,new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser,PlansEnum.BASIC,userRoles);

    }


}
