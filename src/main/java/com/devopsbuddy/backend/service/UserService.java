package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

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

}
