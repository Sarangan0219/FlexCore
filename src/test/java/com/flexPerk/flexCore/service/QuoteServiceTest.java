package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.Quote;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuoteServiceTest {

    @Mock
    QuoteRepository quoteRepository;

    @Mock
    EmployerService employerService;

    @Mock
    ServiceProviderService serviceProviderService;

    @InjectMocks
    QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getQuote() {
        Quote quote = new Quote();
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

        Quote result = quoteService.getQuote(1L);

        assertNotNull(result);
        assertEquals(quote, result);
    }

    @Test
    void deleteQuote() {
        Quote quote = new Quote();
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

        Quote result = quoteService.deleteQuote(1L);

        assertNotNull(result);
        assertEquals(quote, result);
        verify(quoteRepository, times(1)).delete(quote);
    }

    @Test
    void updateQuote() {
        Quote quote = new Quote();
        Quote updatedQuote = new Quote();
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));
        when(quoteRepository.save(any())).thenReturn(updatedQuote);

        Quote result = quoteService.updateQuote(updatedQuote);

        assertNotNull(result);
        assertEquals(updatedQuote, result);
    }

    @Test
    void getQuotes() {
        // You should implement this test similar to the others, depending on the behavior you expect
    }

    @Test
    void approveQuote() {
        Quote quote = new Quote();
        quote.setStatus(Quote.QuoteStatus.PENDING);
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));
        when(quoteRepository.save(any())).thenReturn(quote);

        Quote result = quoteService.approveQuote(1L);

        assertNotNull(result);
        assertEquals(Quote.QuoteStatus.ACCEPTED, result.getStatus());
    }
}
