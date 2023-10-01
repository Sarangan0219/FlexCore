package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.DTO.ServiceProviderDTO;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.service.ServiceProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/serviceProvider")
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;


    @Autowired
    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    /**
     * Register a new service provider.
     *
     * @param serviceProvider The service provider object to register.
     * @return A DTO containing a message and the registered service provider object, or an error message if registration fails.
     */

    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    @PostMapping
    public ServiceProviderDTO registerServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        ServiceProviderDTO serviceProviderDTO;
        ServiceProvider serviceProvider1 =  serviceProviderService.registerProvider(serviceProvider);
        if (serviceProvider1 != null) {
            String message  = "Service Provider " +  serviceProvider.getName() + " submitted for registration, " +
                    "Awaiting approval";
            serviceProviderDTO = new ServiceProviderDTO(message, serviceProvider1);
            return serviceProviderDTO;
        }
        return new ServiceProviderDTO("Error in registering Service provider", serviceProvider);
    }

    /**
     * Search for service providers based on the name and/or perk type.
     *
     * @param name The name of the service provider to search for.
     * @param perkType The type of perk to search for.
     * @return A list of service providers that match the search criteria.
     * @throws NotFoundException If no service providers are found matching the criteria.
     */

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/search")
    public ResponseEntity<List<ServiceProvider>> searchServiceProvider(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "perkType", required = false) String perkType) {

        List<ServiceProvider> providers = serviceProviderService.searchServiceProviders(name, perkType);

        if (!providers.isEmpty()) {
            return ResponseEntity.ok(providers);
        } else {
            throw new NotFoundException("No Service Providers found for given criteria");
        }
    }

    /**
     * Retrieve a service provider by their ID.
     *
     * @param id The ID of the service provider to retrieve.
     * @return The service provider with the specified ID.
     * @throws NotFoundException If no service provider is found with the given ID.
     */
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    @GetMapping(path = "{id}")
    public ServiceProvider getServiceProvider(@PathVariable("id") long id) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(id);
        if (serviceProvider != null) {
            return serviceProvider;
        } else {
            throw new NotFoundException("Service Provider with id : " + id + " not found");
        }
    }

    /**
     * Retrieve all registered service providers.
     *
     * @return A list of all registered service providers, or an empty list if none are found.
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<ServiceProvider>> getServiceProviders() {
        List<ServiceProvider> serviceProviderList = serviceProviderService.getServiceProviders();
        if (serviceProviderList.size() != 0) {
            return ResponseEntity.ok(serviceProviderList);
        } else {
            return ResponseEntity.ok(Arrays.asList());
        }
    }

    /**
     * Retrieve all service providers that are pending approval.
     *
     * @return A list of all pending service providers, or an empty list if none are pending.
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/pending")
    public ResponseEntity<List<ServiceProvider>> getPendingServiceProviders() {
        List<ServiceProvider> serviceProviderList = serviceProviderService.getPendingServiceProviders();
        if (serviceProviderList.size() != 0) {
            return ResponseEntity.ok(serviceProviderList);
        } else {
            return ResponseEntity.ok(Arrays.asList());
        }
    }

    /**
     * Delete a service provider by their ID.
     *
     * @param id The ID of the service provider to delete.
     * @return A confirmation message if the deletion was successful.
     * @throws NotFoundException If no service provider is found with the given ID.
     */

    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteServiceProvider(@PathVariable("id") long id) {
        ServiceProvider serviceProvider = serviceProviderService.deleteServiceProvider(id);
        if (serviceProvider != null) {
            return ResponseEntity.ok("Service Provider " + serviceProvider.getName() + " has been deleted");
        } else {
            throw new NotFoundException("Service Provider with id: " + id + " not found");
        }
    }

    /**
     * Update the details of a service provider.
     *
     * @param serviceProvider The service provider object with updated information.
     * @return The updated service provider object.
     */

    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    @PutMapping
    public ServiceProvider updateServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        return serviceProviderService.updateServiceProvider(serviceProvider);
    }


    /**
     * Upload a profile image for a specific service provider.
     *
     * @param id The ID of the service provider to upload the image for.
     * @param file The image file to upload.
     */

    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    @PostMapping(
            path = "{id}/profileImage",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadServiceProviderImage(
            @PathVariable("id") long id, @RequestParam("file") MultipartFile file ) {
            serviceProviderService.uploadImage(id, file);
    }

    /**
     * Retrieve the profile image of a specific service provider.
     *
     * @param id The ID of the service provider whose image to retrieve.
     * @return The image file as a byte array.
     */

    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    @GetMapping(path = "{id}/profileImage")
    public byte[] getServiceProviderImage(@PathVariable("id") long id) {
        return  serviceProviderService.getProfileImage(id);
    }

    /**
     * Approve a pending service provider by their ID.
     *
     * @param id The ID of the service provider to approve.
     * @return A confirmation message if the approval was successful.
     * @throws NotFoundException If no service provider is found with the given ID.
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "{id}/approve-service-provider")
    public ResponseEntity<String> approveServiceProvider(@PathVariable("id") long id) {
        ServiceProvider serviceProvider = serviceProviderService.approveServiceProvider(id);
        if (serviceProvider != null) {
            return ResponseEntity.ok("Service Provider " + serviceProvider.getName() + " has been approved");
        } else {
            throw new NotFoundException("Service Provider with id: " + id + " not found");
        }
    }
}
