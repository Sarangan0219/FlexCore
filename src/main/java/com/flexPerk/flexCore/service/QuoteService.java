package com.flexPerk.flexCore.service;


import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employee;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.Quote;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final EmployerService employerService;
    private final ServiceProviderService serviceProviderService;
    private final  EmployeeService employeeService;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, EmployerService employerService, ServiceProviderService serviceProviderService, EmployeeService employeeService) {
        this.quoteRepository = quoteRepository;
        this.employerService = employerService;
        this.serviceProviderService = serviceProviderService;
        this.employeeService = employeeService;
    }

    public Quote getQuote(long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    public Quote deleteQuote(long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote != null) {
            quoteRepository.delete(quote);
        }
        return quote;
    }

    public Quote updateQuote(Quote updatedQuote) {
        Quote existingQuote = quoteRepository.findById(updatedQuote.getQuoteID()).orElse(null);

        if (existingQuote != null) {
            return quoteRepository.save(existingQuote);
        } else {
            throw new NotFoundException("Quote not found with ID: " + updatedQuote.getQuoteID());
        }
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    public void createQuote(Quote quote) {
        Quote existingQuote = quoteRepository.findById(quote.getQuoteID()).orElse(null);
        if (existingQuote == null) {
            ServiceProvider serviceProvider =
                    serviceProviderService.getServiceProvider(quote.getServiceProvider().getServiceProviderID());
            quote.setServiceProvider(serviceProvider);

            Employer employer = employerService.getEmployer(quote.getEmployer().getEmployerID());
            quote.setEmployer(employer);

            quote.setStatus(Quote.QuoteStatus.PENDING);
            quoteRepository.save(quote);
        } else {
            throw new EntityAlreadyExistsException("Quote with ID " + quote.getQuoteID() + " already exists");
        }
    }

    public Quote createQuoteForServiceProvider(long employerId, long serviceProviderId, Quote quote) {
        Employer employer = employerService.getEmployer(employerId);
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(serviceProviderId);

        if (employer == null) {
            throw new NotFoundException("Employer with ID: " + employerId + " not found");
        }

        if (serviceProvider == null) {
            throw new NotFoundException("Service Provider with ID: " + serviceProviderId + " not found");
        }

        quote.setEmployer(employer);
        quote.setServiceProvider(serviceProvider);
        quote.setStatus(Quote.QuoteStatus.PENDING);

        return quoteRepository.save(quote);
    }

    public Quote reviewQuote(long quoteId, Quote.QuoteStatus status) {
        Quote existingQuote = getQuote(quoteId);

        if (existingQuote == null) {
            throw new NotFoundException("Quote with ID: " + quoteId + " not found");
        }

        if (status == Quote.QuoteStatus.ACCEPTED) {
            allocateServiceToEmployer(existingQuote.getEmployer(), existingQuote.getServiceProvider());
            List<Employee> employees = employeeService.getEmployees(existingQuote.getEmployer().getEmployerID());
            allocateServiceToEmployee(employees, existingQuote.getServiceProvider());
        }

        existingQuote.setStatus(status);

        return quoteRepository.save(existingQuote);
    }

    public Quote approveQuote(long id) {
        Quote quote = getQuote(id);
        quote.setStatus(Quote.QuoteStatus.ACCEPTED);
        quoteRepository.save(quote);
        return quote;
    }

    private void allocateServiceToEmployer(Employer employer, ServiceProvider serviceProvider) {
        employer.getPerks().add(serviceProvider);
        employerService.updateEmployer(employer);
    }

    private void allocateServiceToEmployee(List<Employee> employees, ServiceProvider serviceProvider) {
        for (Employee employee : employees) {
            employee.getServices().add(serviceProvider);
            employeeService.updateEmployee(employee.getEmployer().getEmployerID(),employee.getEmployeeID(),
                    employee);

        }
    }
}
