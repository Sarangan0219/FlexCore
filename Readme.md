
### FlexCore Spring Boot Project

FlexCore is a robust Spring Boot application designed for managing flexible perks and services for employees and employers. This document will guide you through the setup and configuration process.

#### Prerequisites

- Java JDK 8 or later
- MySQL Server
- Maven or Gradle (optional)

#### Setting Up the Database

1. Ensure MySQL server is running.
2. Create a new database named `flexCore`.
3. Update the `username` and `password` in the `application.properties` file to match your MySQL credentials.

#### Configuring Email Service

The application is configured to use Gmail for sending emails. Update the `spring.mail.username` and `spring.mail.password` properties with your Gmail credentials. Ensure that you have enabled "less secure app access" on your Google account to allow the application to send emails.

#### Configuring JWT

JWT is used for securing your application. The secret key and expiration time for JWT tokens are configurable through the `application.security.jwt.secret-key`, `application.security.jwt.expiration` and `application.security.jwt.refresh-token.expiration` properties.

#### AWS SNS Configuration

Update the `aws.sns.topicArn` property with your AWS SNS topic ARN to enable the application to publish messages to the specified SNS topic.

#### Running the Application

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run the command `./mvnw spring-boot:run` (for Maven) or `./gradlew bootRun` (for Gradle) to start the application.
4. The application will be running on `http://localhost:8080`.

#### Application Properties

Here is a breakdown of the properties in the `application.properties` file:

- **Database Configuration:** Configures the MySQL database connection details.

- **JPA/Hibernate Configuration:** Configures JPA/Hibernate settings including the dialect and SQL format.

- **Email Configuration:** Configures the email service for sending emails.

- **JWT Configuration:** Configures the JWT for securing the application.

- **Health Endpoint Configuration:** Exposes all the actuator endpoints for monitoring and managing the application.

- **AWS SNS Configuration:** Configures the AWS SNS topic for publishing messages.

The properties file is well-commented, and each property is self-explanatory. Adjust the properties as per your requirements.

#### Endpoints

All endpoints are secured, and require a valid JWT token in the request header for access. Obtain a JWT token by authenticating with the appropriate endpoint.

#### Contributing

Feel free to fork the repository, and submit pull requests for any enhancements, features, or bug fixes.
