package com.petclinic.invoice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InvoiceService {
    private final RestClient restClient;
    private final double amount;

    public InvoiceService(RestClient restClient, @Value("${visit.price}") double amount) {
        this.restClient = restClient;
        this.amount = amount;
    }

    public Invoice generateInvoice() {
        var invoice = this.restClient.get().uri("http://localhost:8080/visit/1").retrieve().body(Invoice.class);
        invoice.setAmount(amount);
        return invoice;
    }
}
