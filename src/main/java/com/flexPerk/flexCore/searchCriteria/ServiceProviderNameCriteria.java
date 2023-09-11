package com.flexPerk.flexCore.searchCriteria;

import com.flexPerk.flexCore.model.ServiceProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class ServiceProviderNameCriteria implements Criteria {

    private String name;

    public ServiceProviderNameCriteria(String name) {
        this.name = name;
    }

    @Override
    public List<ServiceProvider> meetCriteria(List<ServiceProvider> providers) {
        return new ArrayList<>();
    }
}
