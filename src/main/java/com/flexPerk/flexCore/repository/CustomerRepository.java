package com.flexPerk.flexCore.repository;

import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String userName);

}
