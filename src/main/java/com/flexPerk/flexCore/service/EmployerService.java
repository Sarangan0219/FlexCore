package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.OptInRequest;
import com.flexPerk.flexCore.repository.EmployeeRepository;
import com.flexPerk.flexCore.repository.EmployerRepository;
import com.flexPerk.flexCore.repository.OptInRequestRepository;
import com.flexPerk.flexCore.utils.JavaxLinkGenerator;
import com.flexPerk.flexCore.utils.RegistrationLinkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    private final EmployerRepository employerRepository;
    private final RegistrationLinkGenerator registrationLinkGenerator;
    private final EmployeeRepository employeeRepository;
    private final OptInRequestRepository optInRequestRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository, JavaxLinkGenerator javaxLinkGenerator,
                           EmployeeRepository employeeRepository, OptInRequestRepository optInRequestRepository) {
        this.employerRepository = employerRepository;
        this.registrationLinkGenerator = javaxLinkGenerator;
        this.employeeRepository = employeeRepository;
        this.optInRequestRepository = optInRequestRepository;
    }

    public Employer getEmployer(long id) {
        return employerRepository.findById(id).orElse(null);
    }

    public List<Employer> getEmployers() {
        return employerRepository.findAll();
    }

    public Employer deleteEmployer(long id) {
        Employer employer = employerRepository.findById(id).orElse(null);
        if (employer != null) {
            employerRepository.delete(employer);
        } else {
            throw new NotFoundException("Employer not found with ID: " + id);
        }
        return employer;
    }

    public void approveOptInRequest(Long requestId) {
        OptInRequest request = optInRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request with ID: " + requestId + " not found"));

        request.setApproved(true);
        optInRequestRepository.save(request);
    }

    public void rejectOptInRequest(Long requestId) {
        OptInRequest request = optInRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request with ID: " + requestId + " not found"));

        optInRequestRepository.delete(request);
    }

    public Employer updateEmployer(Employer updatedEmployer) {
        Employer existingEmployer = employerRepository.findByName(updatedEmployer.getName()).orElse(null);

        if (existingEmployer != null) {
            existingEmployer.setContactPersonEmail(updatedEmployer.getContactPersonEmail());
            existingEmployer.setContactPersonPhone(updatedEmployer.getContactPersonPhone());
            existingEmployer.setContactPersonName(updatedEmployer.getContactPersonName());
            existingEmployer.setDescription(updatedEmployer.getDescription());
            return employerRepository.save(existingEmployer);
        } else {
            throw new NotFoundException("Employer not found with ID: " + updatedEmployer.getEmployerID());
        }
    }

    public void registerEmployer(Employer employer) {
        Employer existingEmployer = employerRepository.findByName(employer.getName()).orElse(null);

        if (existingEmployer == null) {
            employer.setApproved(false);
            employerRepository.save(employer);
        } else {
            throw new EntityAlreadyExistsException("Employer: " + employer.getName() + " already exists");
        }
    }

    public void sendSelfRegistrationLink(long employerId, long employeeId) {
        Employer employer = getEmployer(employerId);
        if (employer != null) {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            if (employee != null) {
                String registrationLink = registrationLinkGenerator.generateRegistrationLink();
                registrationLinkGenerator.sendRegistrationEmail(employee.getEmail(), registrationLink);
            } else {
                throw new NotFoundException("Employee with ID: " + employeeId + " not found");
            }
        } else {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }
    }

    public void performSystemSystemValidationForEmployer(Long employerId) {
    }
}
