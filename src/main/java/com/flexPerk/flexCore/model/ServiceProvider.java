package com.flexPerk.flexCore.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name  = "ServiceProvider")
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProvider  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serviceProviderId", updatable = false)
    private long serviceProviderID;

    @NotBlank(message = "Service Provider Name should not be empty")
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isEligible;
    private String perk_type;
    private String password;


    public void setPerk_description(String perk_description) {
        this.perk_description = perk_description;
    }

    private String perk_description;

    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL)
    private List<Branch> branches;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public long getServiceProviderID() {
        return serviceProviderID;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "serviceProviderID=" + serviceProviderID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isEligible=" + isEligible +
                '}';
    }

    public String getPerk_description() {
        return perk_description;
    }

    public String getPerk_type() {
        return perk_type;
    }
}

