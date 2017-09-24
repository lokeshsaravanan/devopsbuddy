package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,Long> {

    @Query("Select ptr from PasswordResetToken ptr inner join ptr.user u where ptr.user.id=?1")
    Set<PasswordResetToken> findAllByUserId(Long userId);

    PasswordResetToken findByToken(String token);

}
