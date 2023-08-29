package com.flexPerk.flexCore.model;

import jakarta.persistence.*;


@Entity
@Table(name  = "Employer")
public class Employer {

    @Id
    @Column(name = "employerId", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employerID;

    private String name;
    private String contactPersonName;
    private String contactPersonEmail;
    private String contactPersonPhone;


    // Constructors
    public Employer(String name, String contactPersonName, String contactPersonEmail, String contactPersonPhone) {
        this.name = name;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.contactPersonPhone = contactPersonPhone;
    }

    public Employer() {

    }

    public long getEmployerID() {
        return employerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

