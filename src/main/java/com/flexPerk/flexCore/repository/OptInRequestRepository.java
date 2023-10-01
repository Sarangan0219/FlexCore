package com.flexPerk.flexCore.repository;


import com.flexPerk.flexCore.model.OptInRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptInRequestRepository extends JpaRepository<OptInRequest, Long> {

}

