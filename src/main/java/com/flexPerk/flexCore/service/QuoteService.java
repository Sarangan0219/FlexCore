package com.flexPerk.flexCore.service;


import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.exception.NotFoundException;
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
    private  final ServiceProviderService serviceProviderService;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, EmployerService employerService, ServiceProviderService serviceProviderService) {
        this.quoteRepository = quoteRepository;
        this.employerService = employerService;
        this.serviceProviderService = serviceProviderService;
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
            // Update the fields of the existing quote with fields from the updated quote
            // Example: existingQuote.setPerkType(updatedQuote.getPerkType());
            // ... (similarly for other fields)

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

    public Quote approveQuote(long id) {
        Quote quote = getQuote(id);
        quote.setStatus(Quote.QuoteStatus.ACCEPTED);
        quoteRepository.save(quote);
        return quote;
    }
}
