package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.repository.ServiceProviderRepository;
import com.flexPerk.flexCore.searchCriteria.AndCriteria;
import com.flexPerk.flexCore.searchCriteria.Criteria;
import com.flexPerk.flexCore.searchCriteria.ServiceProviderNameCriteria;
import com.flexPerk.flexCore.utils.FlexCoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    public ServiceProviderService(ServiceProviderRepository serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
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
            existingServiceProvider.setEligible(updatedServiceProvider.isEligible());

            return serviceProviderRepository.save(existingServiceProvider);
        } else {
            throw new NotFoundException("Service Provider not found with ID: " + updatedServiceProvider.getServiceProviderID());
        }
    }

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    public void registerProvider(ServiceProvider serviceProvider) {
        ServiceProvider existingServiceProvider = serviceProviderRepository
                .findByName(serviceProvider.getName()).orElse(null);

        if (existingServiceProvider == null) {
            serviceProviderRepository.save(serviceProvider);
        } else {
            throw new EntityAlreadyExistsException("Service Provider: " + serviceProvider.getName() + " already exists");
        }
    }

    public void approveQuotes() {

    }

    public List<ServiceProvider> searchServiceProviders(String name, String perkType) {
        if (name != null) {
            return serviceProviderRepository.findByName(name).orElse(null);
        } else if (perkType != null) {
            return serviceProviderRepository.findByPerkType(perkType).orElse(null);
        } else {
            return serviceProviderRepository.findAll();
        }
    }
}

