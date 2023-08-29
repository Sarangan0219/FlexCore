package com.flexPerk.flexCore.model;


import jakarta.persistence.*;

@Entity
@Table(name  = "Branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "branchId", updatable = false)
    private long branchId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "serviceProviderId",
            referencedColumnName = "serviceProviderId"

    )
    private ServiceProvider serviceProvider;

    private String locationName;
    private double latitude;
    private double longitude;

    public Branch(long branchId, ServiceProvider serviceProvider, String locationName, double latitude, double longitude) {
        this.branchId = branchId;
        this.serviceProvider = serviceProvider;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Branch() {

    }

    public long getBranchId() {
        return branchId;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
