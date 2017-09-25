package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {

    /**The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    /**
     *
     * @param user
     * @param plansEnum
     * @param userRoleSet
     * @return User
     */
    @Transactional
    public User createUser(User user, PlansEnum plansEnum,Set<UserRole> userRoleSet)
    {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);


        Plan plan = new Plan(plansEnum);

        //It makes sure that the plans exist in the database
        if(!planRepository.exists(plansEnum.getId()))
        {
            planRepository.save(plan);
        }

        user.setPlan(plan);

        for(UserRole ur : userRoleSet)
        {
            roleRepository.save(ur.getRole());
        }

        user.getUserRoles().addAll(userRoleSet);

        user = userRepository.save(user);

        return user;
    }

    @Transactional
    public void updatePassword(long userId, String password) {
        password = passwordEncoder.encode(password);
        userRepository.updatePassword(userId,password);
        LOG.debug("Password updated for user ID"+userId);
    }

}
