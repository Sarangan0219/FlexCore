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
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "employer/{employerId}/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeByEmployer(
            @PathVariable("employerId") long employerId,
            @PathVariable("employeeId") long employeeId) {
        Employee employee = employeeService.getEmployee(employerId, employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            throw new NotFoundException("Employee with id: " + employeeId + " under Employer with id: " + employerId +
                    " not found");
        }
    }

    @GetMapping(path = "employer/{employerId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByEmployer(
            @PathVariable("employerId") long employerId) {
        List<Employee> employeeList = employeeService.getEmployees(employerId);
        if (!employeeList.isEmpty()) {
            return ResponseEntity.ok(employeeList);
        } else {
            throw new NotFoundException("No Employees are registered for the Employer with ID " + employerId);
        }
    }

    @DeleteMapping(path = "employer/{employerId}/employee/{employeeId}")
    public Employee deleteEmployee(
            @PathVariable("employerId") long employerId, @PathVariable("employeeId") long employeeId) {
        return employeeService.deleteEmployee(employerId, employeeId);
    }

    @PutMapping(path = "employee")
    public Employee updateEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @PostMapping(path = "employer/{employerId}/employee")
    public ResponseEntity<String> registerEmployee(
            @PathVariable("employerId") long employerId, @RequestBody @Valid Employee employee) {
        employeeService.registerEmployee(employerId, employee);
        return ResponseEntity.ok("Employee " + employee.getName() + " registered");
    }
}
