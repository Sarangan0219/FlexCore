package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.DTO.EmployeeDTO;
import com.flexPerk.flexCore.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeID(1L);

        when(employeeService.getEmployee(1L, 1L)).thenReturn(employee);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployee(1L, 1L);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getEmployee().getEmployeeID());
    }

    @Test
    void getEmployee_notFound() {
        when(employeeService.getEmployee(1L, 1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> employeeController.getEmployee(1L, 1L));
    }

    @Test
    void getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        when(employeeService.getEmployees(1L)).thenReturn(employees);

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getEmployees(1L);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getEmployees_notFound() {
        when(employeeService.getEmployees(1L)).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> employeeController.getEmployees(1L));
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeID(1L);

        when(employeeService.deleteEmployee(1L, 1L)).thenReturn(employee);

        ResponseEntity<EmployeeDTO> response = employeeController.deleteEmployee(1L, 1L);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getEmployee().getEmployeeID());
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeID(1L);

        when(employeeService.updateEmployee(1L, 1L, employee)).thenReturn(employee);

        ResponseEntity<EmployeeDTO> response = employeeController.updateEmployee(1L, 1L, employee);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getEmployee().getEmployeeID());
    }

    @Test
    void registerEmployee() {
        Employee employee = new Employee();

        doNothing().when(employeeService).registerEmployee(1L, employee);

        ResponseEntity<EmployeeDTO> response = employeeController.registerEmployee(1L, employee);

        assertNotNull(response.getBody());
        assertEquals("Employee registered successfully", response.getBody().getMessage());
    }
}
