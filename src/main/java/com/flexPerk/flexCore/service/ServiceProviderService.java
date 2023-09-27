package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final FileHandlerService fileHandlerService;

    @Autowired
    public ServiceProviderService(ServiceProviderRepository serviceProviderRepository, FileHandlerService fileHandlerService) {
        this.serviceProviderRepository = serviceProviderRepository;
        this.fileHandlerService = fileHandlerService;
    }

    public ServiceProvider getServiceProvider(long id) {
        return serviceProviderRepository.findById(id).orElse(null);
    }

    public ServiceProvider deleteServiceProvider(long id) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(id).orElse(null);
        if (serviceProvider != null) {
            serviceProviderRepository.delete(serviceProvider);
        }
        return serviceProvider;
    }

    public ServiceProvider updateServiceProvider(ServiceProvider updatedServiceProvider) {
        ServiceProvider existingServiceProvider = serviceProviderRepository
                .findById(updatedServiceProvider.getServiceProviderID()).orElse(null);

        if (existingServiceProvider != null) {
            existingServiceProvider.setName(updatedServiceProvider.getName());
            existingServiceProvider.setEmail(updatedServiceProvider.getEmail());
            existingServiceProvider.setPhoneNumber(updatedServiceProvider.getPhoneNumber());
            existingServiceProvider.setPerk_description(updatedServiceProvider.getPerk_description());

            return serviceProviderRepository.save(existingServiceProvider);
        } else {
            throw new NotFoundException("Service Provider not found with ID: " + updatedServiceProvider.getServiceProviderID());
        }
    }

    public List<ServiceProvider> getServiceProviders() {

        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        List<ServiceProvider> filteredServiceProviderList  = serviceProviderList.stream()
                .filter(ServiceProvider::isEligible)
                .toList();
        return filteredServiceProviderList;
    }

    public void registerProvider(ServiceProvider serviceProvider) {
        ServiceProvider existingServiceProvider = serviceProviderRepository
                .findByName(serviceProvider.getName()).orElse(null);

        if (existingServiceProvider == null) {
            serviceProvider.setEligible(false);
            serviceProviderRepository.save(serviceProvider);
        } else {
            throw new EntityAlreadyExistsException("Service Provider: " + serviceProvider.getName() + " already exists");
        }
    }

    public void approveQuotes() {

    }

    public List<ServiceProvider> searchServiceProviders(String name, String perkType) {
        if (name != null) {
            ServiceProvider serviceProvider = serviceProviderRepository.findByName(name).orElse(null);
            return Arrays.asList(serviceProvider);
        } else if (perkType != null) {
            //return serviceProviderRepository.findByperktype(perkType).orElse(null);
            return new ArrayList<>();
        } else {
            return serviceProviderRepository.findAll();
        }
    }

    public void uploadImage(long id, MultipartFile file) {
        fileHandlerService.uploadImage(id, file);
    }

    public byte[] getProfileImage(long id) {
        return fileHandlerService.getProfileImage(id);
    }

    public ServiceProvider approveServiceProvider(long id) {
        ServiceProvider serviceProvider = getServiceProvider(id);
        if (serviceProvider != null) {
            serviceProvider.setEligible(true);
            serviceProviderRepository.save(serviceProvider);
            return serviceProvider;
        } else {
            return null;
        }
    }
}

