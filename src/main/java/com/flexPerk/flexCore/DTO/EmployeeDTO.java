package com.flexPerk.flexCore.DTO;

import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.ServiceProvider;

public class EmployeeDTO {

    private String message;
    private Employee employee;

    public String getMessage() {
        return message;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDTO(String message, Employee employee) {
        this.message = message;
        this.employee = employee;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
