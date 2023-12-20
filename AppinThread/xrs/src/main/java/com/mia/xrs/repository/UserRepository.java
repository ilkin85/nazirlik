package com.mia.xrs.repository;

import com.mia.xrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndIsEnabled(String username, Boolean isEnabled);

    Boolean existsByUsername(String username);

}
