package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
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
        }
        return employer;
    }

    public Employer updateEmployer(Employer updatedEmployer) {
        Employer existingEmployer = employerRepository.findById(updatedEmployer.getEmployerID()).orElse(null);

        if (existingEmployer != null) {
            existingEmployer.setName(updatedEmployer.getName());
            existingEmployer.setContactPersonEmail(updatedEmployer.getContactPersonEmail());
            existingEmployer.setContactPersonPhone(updatedEmployer.getContactPersonPhone());
            existingEmployer.setContactPersonName(updatedEmployer.getContactPersonName());

            return employerRepository.save(existingEmployer);
        } else {
            throw new NotFoundException("Employer not found with ID: " + updatedEmployer.getEmployerID());
        }
    }

    public void registerEmployer(Employer employer) {
        Employer existingEmployer = employerRepository.findByName(employer.getName()).orElse(null);

        if (existingEmployer == null) {
            employerRepository.save(employer);
        } else {
            throw new EntityAlreadyExistsException("Employer: " + employer.getName() + " already exists");
        }
    }
}
