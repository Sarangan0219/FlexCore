package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Employee deleteEmployee(
            @PathVariable long employerId, @PathVariable long employeeId) {
        return employeeService.deleteEmployee(employerId, employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable long employerId, @PathVariable long employeeId,
                                   @RequestBody @Valid Employee employee) {
        return employeeService.updateEmployee(employerId, employeeId, employee);
    }

    @PostMapping
    public ResponseEntity<String> registerEmployee(
            @PathVariable long employerId, @RequestBody @Valid Employee employee) {
        employeeService.registerEmployee(employerId, employee);
        return ResponseEntity.ok("Employee " + employee.getFirstName() + " registered");
    }
}
