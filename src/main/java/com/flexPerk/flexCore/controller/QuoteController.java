package com.flexPerk.flexCore.controller;


import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Quote;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.service.QuoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable("id") long id) {
        Quote quote = quoteService.getQuote(id);
        if (quote != null) {
            return ResponseEntity.ok(quote);
        } else {
            throw new NotFoundException("Quote with id : " + id + " not found");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Quote>> getQuotes() {
        List<Quote> quoteList = quoteService.getQuotes();
        if (quoteList.size() != 0) {
            return ResponseEntity.ok(quoteList);
        } else {
            throw new NotFoundException("No quotes are registered with this system");
        }
    }

    @DeleteMapping(path = "{id}")
    public Quote deleteQuote(@PathVariable("id") long id) {
        return quoteService.deleteQuote(id);
    }

    @PutMapping
    public Quote updateQuote(@RequestBody Quote quote) {
        return quoteService.updateQuote(quote);
    }


    @PostMapping(path = "{id}/approve-Quote")
    public ResponseEntity<String> approveQuote(@PathVariable("id") long id) {
        Quote quote = quoteService.approveQuote(id);
        if (quote != null) {
            return ResponseEntity.ok("Quote " + quote.getQuoteID() + " has been approved");
        } else {
            throw new NotFoundException("Quote with id: " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<String> createQuote(@RequestBody Quote quote) {
        quoteService.createQuote(quote);
        return ResponseEntity.ok("Quote created successfully");
    }
}

