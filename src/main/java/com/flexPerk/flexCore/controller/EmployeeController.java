package com.flexPerk.flexCore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.service.EmployeeService;


@RestController
@RequestMapping("/api/v1/employer/{employerId}/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable long employerId, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employerId, employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            throw new NotFoundException("Employee with id: " + employeeId + " under Employer with id: " + employerId +
                    " not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable long employerId) {
        List<Employee> employeeList = employeeService.getEmployees(employerId);
        if (!employeeList.isEmpty()) {
            return ResponseEntity.ok(employeeList);
        } else {
            throw new NotFoundException("No Employees are registered for the Employer with ID " + employerId);
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(
            @PathVariable long employerId, @PathVariable long employeeId) {
        Employee employee = employeeService.deleteEmployee(employerId, employeeId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long employerId, @PathVariable long employeeId,
                                                   @RequestBody @Valid Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employerId, employeeId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PostMapping
    public ResponseEntity<String> registerEmployee(
            @PathVariable long employerId, @RequestBody @Valid Employee employee) {
        employeeService.registerEmployee(employerId, employee);
        return ResponseEntity.ok("Employee " + employee.getFirstName() + " registered");
    }
}
