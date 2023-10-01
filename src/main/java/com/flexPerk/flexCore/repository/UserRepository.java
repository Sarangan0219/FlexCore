package com.flexPerk.flexCore.repository;

import com.flexPerk.flexCore.model.Role;
import com.flexPerk.flexCore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameAndRole(String userName, Role role);

    Optional<User> findByUsername(String userName);

}
