package com.flexPerk.flexCore.repository;

import com.flexPerk.flexCore.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.employeeID = :employeeID AND e.employer.employerID = :employerID")
    Employee findByEmployeeIdAndEmployerId(@Param("employeeID") long employeeID, @Param("employerID") long employerID);

    List<Employee> findByEmployer_EmployerID(long employerID);
}

