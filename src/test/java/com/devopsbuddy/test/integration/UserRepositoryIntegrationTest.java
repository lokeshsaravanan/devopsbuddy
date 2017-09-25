package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Rule
    public TestName testName = new TestName();

    @Before
    public void init() {
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);

    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievePlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievePlan);

    }


    @Test
    public void testCreateNewRole() throws Exception {
        Role basicRole = createBasicRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);
        Role retrieveRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrieveRole);

    }

    @Test
    public void testCreateNewUser() throws Exception {


        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = createUser(testName);
        basicUser.setPlan(basicPlan);

        Role basicRole = createBasicRole(RolesEnum.BASIC);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser,basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for(UserRole urole: userRoles)
        {
            roleRepository.save(urole.getRole());
        }

        basicUser = userRepository.save(basicUser);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());

        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId()!=0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newUserRoles = newlyCreatedUser.getUserRoles();
        for(UserRole ur : newUserRoles)
        {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());

        }
    }

    @Test
    public void findUserByEmail()
    {
        User newUser = createUser(testName);
        userRepository.save(newUser);

        User newlySearchedUser = userRepository.findByEmail(newUser.getEmail());

        Assert.assertNotNull(newlySearchedUser);
        Assert.assertNotNull(newlySearchedUser.getId());

    }

    @Test
    public void testDeleteUser() throws Exception {
        User basicUser = createUser(testName);
        userRepository.delete(basicUser.getId());
    }

//    @Test
//    public void testUpdateNewPassword() throws Exception {
//        User user = createUser(testName);
//        Assert.assertNotNull(user);
//        Assert.assertNotNull(user.getId());
//
//        String newPassword = UUID.randomUUID().toString();
//
//        userRepository.updatePassword(user.getId(),newPassword);
//
//        user = userRepository.findOne(user.getId());
//
//        Assert.assertNotNull(user);
//        Assert.assertEquals(newPassword,user.getPassword());
//
//    }
}
