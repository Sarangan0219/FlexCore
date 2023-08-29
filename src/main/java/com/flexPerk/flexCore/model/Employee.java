package com.flexPerk.flexCore.model;

import jakarta.persistence.*;

@Entity
@Table(name  = "Employee")
public class Employee {
    @Id
    @Column(name = "employeeId", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeID;
    private String name;
    private String email;
    private String position;
    private String team;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "employerId",
            referencedColumnName = "employerId"

    )
    private Employer employer;

    // Constructors
    public Employee() {
        // Default constructor
    }

    public Employee(String name, String email, String position, String team) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employer getEmployer() {
        return employer;
    }

    // Getters and Setters
    public long getEmployeeID() {
        return employeeID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @Override
//    public String toString() {
//        return "Employee{" +
//                "employeeID=" + employeeID +
//                ", employerID=" + employerID +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}

