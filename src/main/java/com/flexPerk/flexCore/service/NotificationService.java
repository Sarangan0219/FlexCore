package com.flexPerk.flexCore.service;


import com.flexPerk.flexCore.model.SubscriptionRequest;

public interface NotificationService {
    void sendNotification(Long employerId, SubscriptionRequest subscriptionRequest);
}
