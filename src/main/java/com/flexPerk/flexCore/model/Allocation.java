package com.flexPerk.flexCore.model;


public class Allocation {
    private int allocationID;
    private int quoteID;
    private int employeeID;
    private String allocatedTime;

    public Allocation(int allocationID, int quoteID, int employeeID, String allocatedTime) {
        this.allocationID = allocationID;
        this.quoteID = quoteID;
        this.employeeID = employeeID;
        this.allocatedTime = allocatedTime;
    }

    public int getAllocationID() {
        return allocationID;
    }

    public int getQuoteID() {
        return quoteID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getAllocatedTime() {
        return allocatedTime;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "allocationID=" + allocationID +
                ", quoteID=" + quoteID +
                ", employeeID=" + employeeID +
                ", allocatedTime='" + allocatedTime + '\'' +
                '}';
    }
}

