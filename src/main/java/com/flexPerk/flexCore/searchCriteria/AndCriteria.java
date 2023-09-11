package com.flexPerk.flexCore.searchCriteria;

import com.flexPerk.flexCore.model.ServiceProvider;

import java.util.List;

public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<ServiceProvider> meetCriteria(List<ServiceProvider> providers) {
        List<ServiceProvider> firstCriteriaItems = criteria.meetCriteria(providers);
        return otherCriteria.meetCriteria(firstCriteriaItems);
    }
}

