package com.flexPerk.flexCore.searchCriteria;

import com.flexPerk.flexCore.model.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderPerkTypeCriteria implements Criteria {

    private String perkType;

    public ServiceProviderPerkTypeCriteria(String perkType) {
        this.perkType = perkType;
    }

    @Override
    public List<ServiceProvider> meetCriteria(List<ServiceProvider> providers) {
        return new ArrayList<>();
    }
}
