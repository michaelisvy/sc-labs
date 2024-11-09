package com.petclinic.invoice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
class InvoiceService {

    private static final String VISIT_URL = "http://localhost:8080/visit/";
    private final double visitPrice;
    private final RestClient restClient;

    public InvoiceService(RestClient restClient, @Value("${visit.price}") double visitPrice) {
        this.restClient = restClient;
        this.visitPrice = visitPrice;
    }

    public Invoice generateInvoice(int id) {
        var url = VISIT_URL + id;
        var invoice = this.restClient.get().uri(url).retrieve().body(Invoice.class);
        invoice.setAmount(this.visitPrice);
        return invoice;
    }

}
