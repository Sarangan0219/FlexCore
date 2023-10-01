package com.flexPerk.flexCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name  = "Quote")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    @Id
    @Column(name = "quoteId", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quoteID;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "serviceProviderId",
            referencedColumnName = "serviceProviderId"

    )
    private ServiceProvider serviceProvider;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "employerId",
            referencedColumnName = "employerId"

    )
    private Employer employer;
    private QuoteStatus status;


    private long contractDuration;
    private int estimatedEmployeeCount;
    private int paymentFrequency;
    private double estimatedTotalCost;
    private String negotiableAttributes;

    public long getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(long contractDuration) {
        this.contractDuration = contractDuration;
    }

    public int getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(int paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public String getNegotiableAttributes() {
        return negotiableAttributes;
    }

    public void setNegotiableAttributes(String negotiableAttributes) {
        this.negotiableAttributes = negotiableAttributes;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    private String additionalInformation;

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public int getEstimatedEmployeeCount() {
        return estimatedEmployeeCount;
    }

    public void setEstimatedEmployeeCount(int estimatedEmployeeCount) {
        this.estimatedEmployeeCount = estimatedEmployeeCount;
    }

    public void setQuoteID(long quoteID) {
        this.quoteID = quoteID;
    }


    public void setStatus(QuoteStatus status) {
        this.status = status;
    }

    public long getQuoteID() {
        return quoteID;
    }

    public QuoteStatus getStatus() {
        return status;
    }


    public enum QuoteStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        EXPIRED
    }
}
