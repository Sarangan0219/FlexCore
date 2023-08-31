package com.flexPerk.flexCore.utils;

import com.flexPerk.flexCore.exception.NotFoundException;

public class EmployeeUtils {

    public static boolean isEmployeeEmailValid(String employerEmail, String employeeEmail) {
        // Extract domains from emails
        String employerDomain = extractDomain(employerEmail);
        String employeeDomain = extractDomain(employeeEmail);

        // Compare domains
        return employerDomain.equals(employeeDomain);
    }

    private static String extractDomain(String email) {
        int atIndex = email.lastIndexOf('@');
        if (atIndex != -1) {
            return email.substring(atIndex + 1);
        } else {
            throw new NotFoundException("Email address is not valid");
        }
    }

}
