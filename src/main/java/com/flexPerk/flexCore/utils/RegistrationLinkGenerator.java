package com.flexPerk.flexCore.utils;

public interface RegistrationLinkGenerator {
    void sendRegistrationEmail(String recipientEmail, String registrationLink);
    String generateRegistrationLink();
}
