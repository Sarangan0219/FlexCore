package com.flexPerk.flexCore.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name  = "OptInRequest")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptInRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private ServiceProvider serviceProvider;

    private boolean approved;

    public boolean isApproved() {

        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
