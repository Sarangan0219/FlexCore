package com.flexPerk.flexCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name  = "Employer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    @Id
    @Column(name = "employerId", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employerID;

    private String name;
    private String contactPersonName;
    private String contactPersonEmail;
    private String contactPersonPhone;
    private String address;
    private int companySize;
    private String url;
    private String description;
    private boolean isApproved;

    @ManyToMany
    @JoinTable(
            name = "employer_perks",
            joinColumns = @JoinColumn(name = "employer_id"),
            inverseJoinColumns = @JoinColumn(name = "service_provider_id"))
    private List<ServiceProvider> perks = new ArrayList<>();


    public List<ServiceProvider> getPerks() {
        return perks;
    }

    public void setPerks(List<ServiceProvider> perks) {
        this.perks = perks;
    }

    public void setEmployerID(long employerID) {
        this.employerID = employerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEmployerID() {
        return employerID;
    }

    public String getName() {
        return name;
    }


    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "employerID=" + employerID +
                ", name='" + name + '\'' +
                ", contactPersonName='" + contactPersonName + '\'' +
                ", contactPersonEmail='" + contactPersonEmail + '\'' +
                ", contactPersonPhone='" + contactPersonPhone + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public int getCompanySize() {
        return companySize;
    }


    public String getDescription() {
        return description;
    }

}

