package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.SubscriptionRequest;
import com.flexPerk.flexCore.repository.EmployeeRepository;
import com.flexPerk.flexCore.repository.SubscriptionRequestRepository;
import com.flexPerk.flexCore.utils.EmployeeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final EmployerService employerService;
    private final SubscriptionRequestRepository subscriptionRequestRepository;
    private final NotificationService notificationService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployerService employerService, SubscriptionRequestRepository subscriptionRequestRepository, NotificationService notificationService) {
        this.employeeRepository = employeeRepository;
        this.employerService = employerService;
        this.subscriptionRequestRepository = subscriptionRequestRepository;
        this.notificationService = notificationService;
    }

    public Employee getEmployee(long employerId, long employeeId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            Employee employee = employeeRepository.findByEmployeeIdAndEmployerId(employeeId, employerId);
            if (employee != null) {
                return employee;
            } else {
                throw new NotFoundException("Employee with ID: " + employeeId + " not found for Employer with ID: " + employerId);
            }
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    public List<Employee> getEmployees(long employerId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            return employeeRepository.findByEmployer_EmployerID(employerId);
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    @Transactional
    public Employee deleteEmployee(long employerId, long employeeId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            Employee employeeToDelete = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee with ID: " + employeeId + " not found"));
            employeeRepository.delete(employeeToDelete);
            return employeeToDelete;
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    public void subscribeServiceProvider(long employerId, long employeeId, long serviceProviderId) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer == null) {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }

        Employee employee = getEmployee(employerId, employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee with ID: " + employeeId + " not found for Employer with ID: " + employerId);
        }

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setEmployee(employee);
        subscriptionRequest.setServiceProviderId(serviceProviderId);
        subscriptionRequest.setApproved(false);
        subscriptionRequestRepository.save(subscriptionRequest);

        notificationService.sendNotification(employerId, subscriptionRequest);

        logger.info("Subscription request from Employee ID: {} for Service Provider ID: {} sent to Employer ID: {} for approval.", employeeId, serviceProviderId, employerId);
    }


    @Transactional
    public Employee updateEmployee(long employerId, long employeeId, Employee employee) {
        if (employee != null) {
            Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee with ID: " + employeeId + " not found"));
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setEmail(employee.getEmail());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new IllegalArgumentException("Employee cannot be null");
        }
    }

    @Transactional
    public void registerEmployee(long employerId, Employee employee) {
        Employer employer = employerService.getEmployer(employerId);
        if (employer != null) {
            if (employee != null) {
                employee.setEmployer(employer);
                EmployeeUtils.isEmployeeEmailValid(employer.getContactPersonEmail(), employee.getEmail());
                employeeRepository.save(employee);
            } else {
                throw new NotFoundException("Employee with ID: " + employerId + " not found in Employer with ID " + employerId);
            }
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }
}
