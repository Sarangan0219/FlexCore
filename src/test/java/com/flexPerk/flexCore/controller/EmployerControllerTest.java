package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.service.EmployerService;
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

class EmployerControllerTest {

    @InjectMocks
    EmployerController employerController;

    @Mock
    EmployerService employerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployer() {
        Employer employer = new Employer();
        employer.setEmployerID(1L);

        when(employerService.getEmployer(1L)).thenReturn(employer);

        ResponseEntity<Employer> response = employerController.getEmployer(1L);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getEmployerID());
    }

    @Test
    void getEmployer_notFound() {
        when(employerService.getEmployer(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> employerController.getEmployer(1L));
    }

    @Test
    void getEmployers() {
        List<Employer> employers = new ArrayList<>();
        employers.add(new Employer());

        when(employerService.getEmployers()).thenReturn(employers);

        ResponseEntity<List<Employer>> response = employerController.getEmployers();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getEmployers_notFound() {
        when(employerService.getEmployers()).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> employerController.getEmployers());
    }

    @Test
    void deleteEmployer() {
        Employer employer = new Employer();
        employer.setEmployerID(1L);

        when(employerService.deleteEmployer(1L)).thenReturn(employer);

        Employer response = employerController.deleteEmployer(1L);

        assertNotNull(response);
        assertEquals(1L, response.getEmployerID());
    }

    @Test
    void updateEmployer() {
        Employer employer = new Employer();
        employer.setEmployerID(1L);

        when(employerService.updateEmployer(employer)).thenReturn(employer);

        Employer response = employerController.updateEmployer(employer);

        assertNotNull(response);
        assertEquals(1L, response.getEmployerID());
    }

    @Test
    void registerEmployer() {
        Employer employer = new Employer();
        employer.setName("Test Employer");

        doNothing().when(employerService).registerEmployer(employer);

        ResponseEntity<String> response = employerController.registerEmployer(employer);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("Test Employer"));
    }

    @Test
    void approveEmployer() {
        doNothing().when(employerService).performSystemSystemValidationForEmployer(1L);

        ResponseEntity<?> response = employerController.approveEmployer(1L);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("approved"));
    }
}
