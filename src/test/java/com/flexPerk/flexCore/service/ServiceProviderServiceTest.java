package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.repository.ServiceProviderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceProviderServiceTest {

    @Mock
    private ServiceProviderRepository serviceProviderRepository;

    @Mock
    private FileHandlerService fileHandlerService;

    @InjectMocks
    private ServiceProviderService serviceProviderService;


    @Test
    void deleteServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        when(serviceProviderRepository.findById(anyLong())).thenReturn(Optional.of(serviceProvider));

        ServiceProvider deletedServiceProvider = serviceProviderService.deleteServiceProvider(1L);
        verify(serviceProviderRepository).delete(serviceProvider);
        assertNotNull(deletedServiceProvider);
    }

    @Test
    void updateServiceProvider() {
        // You can fill this based on your requirement
    }

    @Test
    void getServiceProviders() {
        // You can fill this based on your requirement
    }

    @Test
    void getPendingServiceProviders() {
        // You can fill this based on your requirement
    }

    @Test
    void approveQuotes() {
        // This method is empty in your service class, so there's nothing to test unless you implement it
    }

    @Test
    void searchServiceProviders() {
        // You can fill this based on your requirement
    }

    @Test
    void uploadImage() {
        MultipartFile file = mock(MultipartFile.class);
        doNothing().when(fileHandlerService).uploadImage(anyLong(), any());

        assertDoesNotThrow(() -> serviceProviderService.uploadImage(1L, file));
    }

    @Test
    void getProfileImage() {
        byte[] image = new byte[10];
        when(fileHandlerService.getProfileImage(anyLong())).thenReturn(image);

        byte[] returnedImage = serviceProviderService.getProfileImage(1L);
        assertNotNull(returnedImage);
    }

    @Test
    void approveServiceProvider() {
        ServiceProvider serviceProvider = new ServiceProvider();
        when(serviceProviderRepository.findById(anyLong())).thenReturn(Optional.of(serviceProvider));

        ServiceProvider approvedServiceProvider = serviceProviderService.approveServiceProvider(1L);
        assertTrue(approvedServiceProvider.isEligible());
    }
}
