package com.flexPerk.flexCore.utils;

import com.flexPerk.flexCore.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeUtilsTest {

    @Test
    void isEmployeeEmailValid_sameDomain() {
        String employerEmail = "employer@flexperk.com";
        String employeeEmail = "employee@flexperk.com";

        assertTrue(EmployeeUtils.isEmployeeEmailValid(employerEmail, employeeEmail));
    }

    @Test
    void isEmployeeEmailValid_differentDomain() {
        String employerEmail = "employer@flexperk.com";
        String employeeEmail = "employee@different.com";

        assertFalse(EmployeeUtils.isEmployeeEmailValid(employerEmail, employeeEmail));
    }

    @Test
    void isEmployeeEmailValid_invalidEmployerEmail() {
        String employerEmail = "invalid-email";
        String employeeEmail = "employee@flexperk.com";

        Exception exception = assertThrows(NotFoundException.class, () ->
                EmployeeUtils.isEmployeeEmailValid(employerEmail, employeeEmail));
        assertTrue(exception.getMessage().contains("Email address is not valid"));
    }

    @Test
    void isEmployeeEmailValid_invalidEmployeeEmail() {
        String employerEmail = "employer@flexperk.com";
        String employeeEmail = "invalid-email";

        Exception exception = assertThrows(NotFoundException.class, () ->
                EmployeeUtils.isEmployeeEmailValid(employerEmail, employeeEmail));
        assertTrue(exception.getMessage().contains("Email address is not valid"));
    }
}
