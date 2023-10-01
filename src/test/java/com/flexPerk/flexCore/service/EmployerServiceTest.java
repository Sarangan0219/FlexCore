package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.repository.EmployeeRepository;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.repository.EmployerRepository;
import com.flexPerk.flexCore.utils.JavaxLinkGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployerServiceTest {

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private JavaxLinkGenerator javaxLinkGenerator;

    @InjectMocks
    private EmployerService employerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testUpdateEmployerWhenEmployerDoesNotExist() {
        Employer employer = new Employer();
        employer.setName("Test Employer");
        when(employerRepository.findByName(anyString())).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            employerService.updateEmployer(employer);
        });

        assertEquals("Employer not found with ID: " + employer.getEmployerID(), exception.getMessage());
    }

    @Test
    void testSendSelfRegistrationLinkWhenEmployerDoesNotExist() {
        when(employerRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            employerService.sendSelfRegistrationLink(1L, 1L);
        });

        assertEquals("Employer with ID: 1 not found", exception.getMessage());
    }

    @Test
    void testSendSelfRegistrationLinkWhenEmployeeDoesNotExist() {
        Employer employer = new Employer();
        when(employerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(employer));
        when(employeeRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            employerService.sendSelfRegistrationLink(1L, 1L);
        });

        assertEquals("Employer with ID: 1 not found", exception.getMessage());
    }

    @Test
    void testDeleteEmployerWhenEmployerDoesNotExist() {
        when(employerRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            employerService.deleteEmployer(1L);
        });

        assertEquals("Employer not found with ID: 1", exception.getMessage());
    }
}
