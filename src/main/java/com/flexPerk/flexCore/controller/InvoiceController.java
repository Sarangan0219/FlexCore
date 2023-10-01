package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.model.Invoice;
import com.flexPerk.flexCore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate")
    public ResponseEntity<Invoice> generateInvoice(@RequestBody Invoice invoice) {
        Invoice generatedInvoice = invoiceService.generateInvoice(invoice.getEmployer(), invoice.getServiceProvider(), invoice.getAmount());
        return ResponseEntity.ok(generatedInvoice);
    }

    @PostMapping("/process-payment/{invoiceId}")
    public ResponseEntity<String> processPayment(@PathVariable long invoiceId) {
        // Retrieve the invoice from the database using the invoiceId
        // Here, for simplicity, we're directly passing the invoice object
        Invoice invoice = new Invoice(); // This should be retrieved from the database
        invoiceService.processPayment(invoice);
        return ResponseEntity.ok("Payment processed successfully");
    }

    @PostMapping("/transfer-payment/{invoiceId}")
    public ResponseEntity<String> transferPayment(@PathVariable long invoiceId) {
        // Retrieve the invoice from the database using the invoiceId
        // Here, for simplicity, we're directly passing the invoice object
        Invoice invoice = new Invoice(); // This should be retrieved from the database
        invoiceService.transferPaymentToServiceProvider(invoice);
        return ResponseEntity.ok("Payment transferred to service provider successfully");
    }
}
