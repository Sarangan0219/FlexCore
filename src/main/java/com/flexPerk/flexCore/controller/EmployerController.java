package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.service.EmployerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employer")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping(path = "{employerId}")
    public ResponseEntity<Employer> getEmployer(@PathVariable("employerId") long id) {
        Employer employer = employerService.getEmployer(id);
        if (employer != null) {
            return ResponseEntity.ok(employer);
        } else {
            throw new NotFoundException("Employer with id : " + id + " not found");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Employer>> getEmployers() {
        List<Employer> employerList = employerService.getEmployers();
        if (employerList.size() != 0) {
            return ResponseEntity.ok(employerList);
        } else {
            throw new NotFoundException("No Employers are registered with this system");
        }
    }

    @DeleteMapping(path = "{id}")
    public Employer deleteEmployer(@PathVariable("id") long id) {
        return employerService.deleteEmployer(id);
    }

    @PutMapping
    public Employer updateEmployer(@RequestBody Employer employer) {
        return employerService.updateEmployer(employer);
    }

    @PostMapping
    public ResponseEntity<String> registerEmployer(@RequestBody  @Valid Employer employer) {
        employerService.registerEmployer(employer);
        return ResponseEntity.ok("Employer " + employer.getName() + " registered, Awaiting approval.");
    }

//    @GetMapping(path = "{employerId}/employee/{employeeId}")
//    public void getRegistrationLinkForEmployees( @PathVariable("employerId") long employerId,
//                                     @PathVariable("employeeId") long employeeId) {
//        employerService.sendSelfRegistrationLink(employerId, employeeId);
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "{id}/approve-service-provider")
    public ResponseEntity<?> approveEmployer(@PathVariable("id") long id) {
        employerService.performSystemSystemValidationForEmployer(id);
        return ResponseEntity.ok("Employer "  + id+ " approved");
    }

}
