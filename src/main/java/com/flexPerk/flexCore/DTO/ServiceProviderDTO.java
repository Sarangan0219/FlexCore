package com.flexPerk.flexCore.DTO;

import com.flexPerk.flexCore.model.ServiceProvider;

public class ServiceProviderDTO {

    private String message;
    private ServiceProvider serviceProvider;

    public String getMessage() {
        return message;
    }

    public ServiceProviderDTO(String message, ServiceProvider serviceProvider) {
        this.message = message;
        this.serviceProvider = serviceProvider;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
