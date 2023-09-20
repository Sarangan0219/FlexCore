package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.repository.EmployeeRepository;
import com.flexPerk.flexCore.service.EmployerService;
import com.flexPerk.flexCore.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployerService employerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployee() {
        // Arrange
        Employer employer = new Employer();
        Employee employee = new Employee();

        when(employerService.getEmployer(anyLong())).thenReturn(employer);
        when(employeeRepository.findByEmployeeIdAndEmployerId(anyLong(), anyLong())).thenReturn(employee);

        // Act
        Employee result = employeeService.getEmployee(1L, 1L);

        // Assert
        assertNotNull(result);
        verify(employerService).getEmployer(1L);
        verify(employeeRepository).findByEmployeeIdAndEmployerId(1L, 1L);
    }

    @Test
    void getEmployees() {
        // Arrange
        Employer employer = new Employer();
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        when(employerService.getEmployer(anyLong())).thenReturn(employer);
        when(employeeRepository.findByEmployer_EmployerID(anyLong())).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.getEmployees(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employerService).getEmployer(1L);
        verify(employeeRepository).findByEmployer_EmployerID(1L);
    }

    @Test
    void deleteEmployee() {
        // Arrange
        Employer employer = new Employer();
        Employee employee = new Employee();

        when(employerService.getEmployer(anyLong())).thenReturn(employer);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeService.deleteEmployee(1L, 1L);

        // Assert
        assertNotNull(result);
        verify(employerService).getEmployer(1L);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).delete(employee);
    }


    @Test
    void registerEmployee() {
        // Arrange
        Employer employer = new Employer();
        employer.setContactPersonEmail("contact@example.com");
        Employee newEmployee = new Employee();
        newEmployee.setEmail("employee@example.com");

        when(employerService.getEmployer(anyLong())).thenReturn(employer);

        // Act
        employeeService.registerEmployee(1L, newEmployee);

        // Assert
        verify(employerService).getEmployer(1L);
        verify(employeeRepository).save(any(Employee.class));
    }
}
