package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.DTO.ServiceProviderDTO;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.service.ServiceProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceProviderControllerTest {

    @InjectMocks
    ServiceProviderController serviceProviderController;

    @Mock
    ServiceProviderService serviceProviderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName("Test Provider");

        when(serviceProviderService.registerProvider(serviceProvider)).thenReturn(serviceProvider);

        ServiceProviderDTO result = serviceProviderController.registerServiceProvider(serviceProvider);

        assertNotNull(result);
        assertEquals("Service Provider Test Provider submitted for registration, Awaiting approval", result.getMessage());
    }

    @Test
    void searchServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        List<ServiceProvider> providers = new ArrayList<>();
        providers.add(serviceProvider);

        when(serviceProviderService.searchServiceProviders("Test", null)).thenReturn(providers);

        ResponseEntity<List<ServiceProvider>> response = serviceProviderController.searchServiceProvider("Test", null);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    // Similar tests can be written for other methods as well

    @Test
    void getServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName("Test Provider");

        when(serviceProviderService.getServiceProvider(1L)).thenReturn(serviceProvider);

        ServiceProvider result = serviceProviderController.getServiceProvider(1L);

        assertNotNull(result);
        assertEquals("Test Provider", result.getName());
    }

    @Test
    void getServiceProviders() {
        ServiceProvider serviceProvider = new ServiceProvider();
        List<ServiceProvider> providers = new ArrayList<>();
        providers.add(serviceProvider);

        when(serviceProviderService.getServiceProviders()).thenReturn(providers);

        ResponseEntity<List<ServiceProvider>> response = serviceProviderController.getServiceProviders();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void deleteServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName("Test Provider");

        when(serviceProviderService.deleteServiceProvider(1L)).thenReturn(serviceProvider);

        ResponseEntity<String> response = serviceProviderController.deleteServiceProvider(1L);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("has been deleted"));
    }

    @Test
    void uploadServiceProviderImage() {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "testimage".getBytes());
        assertDoesNotThrow(() -> serviceProviderController.uploadServiceProviderImage(1L, file));
    }

    @Test
    void getServiceProviderImage() {
        byte[] image = "testimage".getBytes();
        when(serviceProviderService.getProfileImage(1L)).thenReturn(image);

        byte[] result = serviceProviderController.getServiceProviderImage(1L);

        assertNotNull(result);
        assertEquals("testimage", new String(result));
    }

    @Test
    void approveServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName("Test Provider");

        when(serviceProviderService.approveServiceProvider(1L)).thenReturn(serviceProvider);

        ResponseEntity<String> response = serviceProviderController.approveServiceProvider(1L);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("has been approved"));
    }
}
