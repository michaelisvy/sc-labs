package com.petclinic.invoice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InvoiceService {
    private final RestClient restClient;

    public InvoiceService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Invoice generateInvoice() {
        var invoice = this.restClient.get().uri("http://localhost:8080/visit/1").retrieve().body(Invoice.class);
        return invoice;
    }
}
