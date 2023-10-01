package com.flexPerk.flexCore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.DTO.EmployeeDTO;
import com.flexPerk.flexCore.service.EmployeeService;

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
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(
            @PathVariable long employerId, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employerId, employeeId);
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO("Employee retrieved successfully", employee);
            return ResponseEntity.ok(employeeDTO);
        } else {
            throw new NotFoundException("Employee with id: " + employeeId + " not found");
        }
    }

    /**
     * Retrieves all employees of a specific employer.
     *
     * @param employerId the employer's ID.
     * @return a response containing a list of employees.
     * @throws NotFoundException if no employees are found.
     */
    @PreAuthorize("hasAuthority('EMPLOYER')")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@PathVariable long employerId) {
        List<Employee> employeeList = employeeService.getEmployees(employerId);
        if (!employeeList.isEmpty()) {
            List<EmployeeDTO> employeeDTOList = employeeList.stream()
                    .map(employee -> new EmployeeDTO("Employee retrieved successfully", employee))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(employeeDTOList);
        } else {
            throw new NotFoundException("No Employees found");
        }
    }

    /**
     * Deletes a specific employee by their ID.
     *
     * @param employerId the employer's ID.
     * @param employeeId the employee's ID.
     * @return a response containing the details of the deleted employee.
     */
    @PreAuthorize("hasAuthority('EMPLOYER')")
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(
            @PathVariable long employerId, @PathVariable long employeeId) {
        Employee employee = employeeService.deleteEmployee(employerId, employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee deleted successfully", employee);
        return ResponseEntity.ok(employeeDTO);
    }

    /**
     * Updates the details of a specific employee.
     *
     * @param employerId the employer's ID.
     * @param employeeId the employee's ID.
     * @param employee the updated employee details.
     * @return a response containing the updated employee details.
     */
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long employerId, @PathVariable long employeeId,
                                                      @RequestBody @Valid Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employerId, employeeId, employee);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee updated successfully", updatedEmployee);
        return ResponseEntity.ok(employeeDTO);
    }

    /**
     * Registers a new employee for a specific employer.
     *
     * @param employerId the employer's ID.
     * @param employee the new employee details.
     * @return a response confirming the registration.
     */
    @PreAuthorize("hasAuthority('EMPLOYER')")
    @PostMapping
    public ResponseEntity<EmployeeDTO> registerEmployee(
            @PathVariable long employerId, @RequestBody @Valid Employee employee) {
        employeeService.registerEmployee(employerId, employee);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee registered successfully", employee);
        return ResponseEntity.ok(employeeDTO);
    }
}
