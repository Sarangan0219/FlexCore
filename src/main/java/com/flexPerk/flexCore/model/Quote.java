package com.flexPerk.flexCore.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name  = "Quote")
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

    private String perk_type;
    private String perk_description;
    private int estimatedEmployeeCount;
    private double totalCost;

    public Quote(ServiceProvider serviceProvider, Employer employer, LocalDateTime validityStartDate,
                 LocalDateTime validityEndDate, String perk_type, String perk_description,
                 int estimatedEmployeeCount, double totalCost) {
        this.serviceProvider = serviceProvider;
        this.employer = employer;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.perk_type = perk_type;
        this.perk_description = perk_description;
        this.estimatedEmployeeCount = estimatedEmployeeCount;
        this.totalCost = totalCost;
        this.status = QuoteStatus.PENDING;
    }

    public Quote() {

    }

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

    public String getPerk_type() {
        return perk_type;
    }

    public void setPerk_type(String perk_type) {
        this.perk_type = perk_type;
    }

    public String getPerk_description() {
        return perk_description;
    }

    public void setPerk_description(String perk_description) {
        this.perk_description = perk_description;
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
