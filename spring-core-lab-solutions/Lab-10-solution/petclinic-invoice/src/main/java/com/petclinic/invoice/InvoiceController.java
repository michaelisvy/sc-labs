package com.petclinic.invoice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping("/invoice/{id}")
    public Invoice generateInvoice(@PathVariable int id) {
        return this.invoiceService.generateInvoice();
    }
}
