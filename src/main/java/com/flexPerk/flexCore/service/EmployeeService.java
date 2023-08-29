package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployerService employerService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployerService employerService) {
        this.employeeRepository = employeeRepository;
        this.employerService = employerService;
    }

    public Employee getEmployee(long employerId, long employeeId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            return employeeRepository.findById(employeeId).orElse(null);
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    public List<Employee> getEmployees(long employerId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            return employeeRepository.findAll();
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    public Employee deleteEmployee(long employerId, long employeeId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            Employee employeeToDelete = employeeRepository.findById(employeeId).orElse(null);
            if (employeeToDelete != null) {
                employeeRepository.delete(employeeToDelete);
                return employeeToDelete;
            } else {
                throw new NotFoundException("Employee with ID: " + employeeId + " not found");
            }
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    public Employee updateEmployee(Employee employee) {
        if (employee != null) {
            long employeeId = employee.getEmployeeID();
            Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);
            if (existingEmployee != null) {
                existingEmployee.setName(employee.getName());
                existingEmployee.setEmail(employee.getEmail());
                return employeeRepository.save(existingEmployee);
            } else {
                throw new NotFoundException("Employee with ID: " + employeeId + " not found");
            }
        } else {
            throw new IllegalArgumentException("Employee cannot be null");
        }
    }

    public void registerEmployee(long employerId, Employee employee) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            if (employee != null) {
                employeeRepository.save(employee);
            } else {
                throw new NotFoundException("Employee with ID: " + employerId + " not found in Employer with ID " +
                        employerId);
            }
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }
}
