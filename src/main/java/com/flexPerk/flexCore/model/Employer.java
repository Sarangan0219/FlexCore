package com.flexPerk.flexCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


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
    private String employerStatus;

    public void setEmployerStatus(String employerStatus) {
        this.employerStatus = employerStatus;
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

    public String getURL() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getEmployerStatus() {
        return employerStatus;
    }
}

