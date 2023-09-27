package com.flexPerk.flexCore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.service.EmployeeService;

/**
 * Handles employee management requests, including retrieval, deletion, updating, and registration.
 */
@RestController
@RequestMapping("/api/v1/employer/{employerId}/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves a specific employee by their ID.
     *
     * @param employerId the employer's ID.
     * @param employeeId the employee's ID.
     * @return a response containing the employee's details.
     * @throws NotFoundException if the employee is not found.
     */
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

    /**
     * Retrieves all employees of a specific employer.
     *
     * @param employerId the employer's ID.
     * @return a response containing a list of employees.
     * @throws NotFoundException if no employees are found.
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable long employerId) {
        List<Employee> employeeList = employeeService.getEmployees(employerId);
        if (!employeeList.isEmpty()) {
            return ResponseEntity.ok(employeeList);
        } else {
            throw new NotFoundException("No Employees are registered for the Employer with ID " + employerId);
        }
    }

    /**
     * Deletes a specific employee by their ID.
     *
     * @param employerId the employer's ID.
     * @param employeeId the employee's ID.
     * @return a response containing the details of the deleted employee.
     */
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(
            @PathVariable long employerId, @PathVariable long employeeId) {
        Employee employee = employeeService.deleteEmployee(employerId, employeeId);
        return ResponseEntity.ok(employee);
    }

    /**
     * Updates the details of a specific employee.
     *
     * @param employerId the employer's ID.
     * @param employeeId the employee's ID.
     * @param employee the updated employee details.
     * @return a response containing the updated employee details.
     */
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long employerId, @PathVariable long employeeId,
                                                   @RequestBody @Valid Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employerId, employeeId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Registers a new employee for a specific employer.
     *
     * @param employerId the employer's ID.
     * @param employee the new employee details.
     * @return a response confirming the registration.
     */
    @PostMapping
    public ResponseEntity<String> registerEmployee(
            @PathVariable long employerId, @RequestBody @Valid Employee employee) {
        employeeService.registerEmployee(employerId, employee);
        return ResponseEntity.ok("Employee " + employee.getFirstName() + " registered");
    }
}
