package com.petclinic.invoice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoice/{id}")
    public Invoice generateInvoice(@PathVariable int id) {
        return this.invoiceService.generateInvoice(id);
    }
}
