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
    private LocalDateTime validityStartDate;;
    private LocalDateTime validityEndDate;

    private int estimatedEmployeeCount;
    private double totalCost;

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

    public LocalDateTime getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(LocalDateTime validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    public LocalDateTime getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(LocalDateTime validityEndDate) {
        this.validityEndDate = validityEndDate;
    }


    public int getEstimatedEmployeeCount() {
        return estimatedEmployeeCount;
    }

    public void setEstimatedEmployeeCount(int estimatedEmployeeCount) {
        this.estimatedEmployeeCount = estimatedEmployeeCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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
