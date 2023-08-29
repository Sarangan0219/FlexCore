package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.service.ServiceProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/serviceProvider")
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    @Autowired
    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ServiceProvider> getServiceProvider(@PathVariable("id") long id) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(id);
        if (serviceProvider != null) {
            return ResponseEntity.ok(serviceProvider);
        } else {
            throw new NotFoundException("Service Provider with id : " + id + " not found");
        }
    }

    @GetMapping()
    public ResponseEntity<List<ServiceProvider>> getServiceProviders() {
        List<ServiceProvider> serviceProviderList = serviceProviderService.getServiceProviders();
        if (serviceProviderList.size() != 0) {
            return ResponseEntity.ok(serviceProviderList);
        } else {
            throw new NotFoundException("No Service Providers are registered with this system");
        }
    }

    @DeleteMapping(path = "{id}")
    public ServiceProvider deleteServiceProvider(@PathVariable("id") long id) {
        return serviceProviderService.deleteServiceProvider(id);
    }

    @PutMapping
    public ServiceProvider updateServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        return serviceProviderService.updateServiceProvider(serviceProvider);
    }

    @PostMapping
    public ResponseEntity<String> registerServiceProvider(@RequestBody  @Valid ServiceProvider serviceProvider) {
        serviceProviderService.registerProvider(serviceProvider);
        return ResponseEntity.ok("Service Provider " + serviceProvider.getName() + " registered");
    }
}
