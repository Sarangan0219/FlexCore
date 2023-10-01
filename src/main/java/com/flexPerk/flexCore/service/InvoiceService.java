package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.Invoice;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvoiceService {


    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice generateInvoice(Employer employer, ServiceProvider serviceProvider, double amount) {
        Invoice invoice = new Invoice();
        invoice.setEmployer(employer);
        invoice.setServiceProvider(serviceProvider);
        invoice.setAmount(amount);
        invoice.setIssuedDate(new Date());
        invoice.setDueDate(new Date(System.currentTimeMillis() + 2592000000L)); // 2592000000L represents 30 days in milliseconds

        return invoiceRepository.save(invoice);
    }

    public void processPayment(Invoice invoice) {
        // Here, add logic to process the payment
        // This can include validating the payment details, processing the payment, and updating the invoice status

        // Example:
        // invoice.setStatus(InvoiceStatus.PAID);
        // invoiceRepository.save(invoice);
    }

    public void transferPaymentToServiceProvider(Invoice invoice) {
        // Here, add logic to transfer the payment to the service provider's account
        // This can include validating the service provider's bank details and initiating the transfer
    }
}

