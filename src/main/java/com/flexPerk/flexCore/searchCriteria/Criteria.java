package com.flexPerk.flexCore.searchCriteria;

import com.flexPerk.flexCore.model.ServiceProvider;

import java.util.List;

public interface Criteria {
    List<ServiceProvider> meetCriteria(List<ServiceProvider> providers);
}

