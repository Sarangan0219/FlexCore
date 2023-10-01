package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.exception.NotFoundException;
import com.flexPerk.flexCore.model.Quote;
import com.flexPerk.flexCore.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuoteControllerTest {

    @InjectMocks
    QuoteController quoteController;

    @Mock
    QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getQuote() {
        Quote quote = new Quote();
        quote.setQuoteID(1L);

        when(quoteService.getQuote(1L)).thenReturn(quote);

        ResponseEntity<Quote> response = quoteController.getQuote(1L);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getQuoteID());
    }

    @Test
    void getQuote_notFound() {
        when(quoteService.getQuote(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> quoteController.getQuote(1L));
    }

    @Test
    void getQuotes() {
        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote());

        when(quoteService.getQuotes()).thenReturn(quotes);

        ResponseEntity<List<Quote>> response = quoteController.getQuotes();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getQuotes_notFound() {
        when(quoteService.getQuotes()).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> quoteController.getQuotes());
    }

    @Test
    void deleteQuote() {
        Quote quote = new Quote();
        quote.setQuoteID(1L);

        when(quoteService.deleteQuote(1L)).thenReturn(quote);

        Quote response = quoteController.deleteQuote(1L);

        assertNotNull(response);
        assertEquals(1L, response.getQuoteID());
    }

    @Test
    void updateQuote() {
        Quote quote = new Quote();
        quote.setQuoteID(1L);

        when(quoteService.updateQuote(quote)).thenReturn(quote);

        Quote response = quoteController.updateQuote(quote);

        assertNotNull(response);
        assertEquals(1L, response.getQuoteID());
    }

    @Test
    void approveQuote() {
        Quote quote = new Quote();
        quote.setQuoteID(1L);

        when(quoteService.approveQuote(1L)).thenReturn(quote);

        ResponseEntity<String> response = quoteController.approveQuote(1L);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("has been approved"));
    }

    @Test
    void approveQuote_notFound() {
        when(quoteService.approveQuote(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> quoteController.approveQuote(1L));
    }

    @Test
    void createQuote() {
        Quote quote = new Quote();

        doNothing().when(quoteService).createQuote(quote);

        ResponseEntity<String> response = quoteController.createQuote(quote);

        assertNotNull(response.getBody());
        assertEquals("Quote created successfully", response.getBody());
    }
}
