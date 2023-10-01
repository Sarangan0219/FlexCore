package com.flexPerk.flexCore.service;


import com.flexPerk.flexCore.model.SubscriptionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class SNSNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(SNSNotificationService.class);

    @Value("${aws.sns.topicArn}") // Inject the SNS Topic ARN from the application properties
    private String snsTopicArn;

    @Override
    public void sendNotification(Long employerId, SubscriptionRequest subscriptionRequest) {
        try {
            SnsClient snsClient = SnsClient.builder().region(Region.US_EAST_1).build();

            String message = String.format("New subscription request ID %d for Employer ID %d.", subscriptionRequest.getId(), employerId);

            PublishRequest publishRequest = PublishRequest.builder().message(message).topicArn(snsTopicArn).build();

            snsClient.publish(publishRequest);
            logger.info("Notification sent: {}", message);

        } catch (Exception e) {
            logger.error("Failed to send notification", e);
        }
    }

}
