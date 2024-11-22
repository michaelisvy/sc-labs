package com.petclinic.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Invoice {

    private double amount;
    @JsonProperty("date")
    private LocalDate visitDate;
    @JsonProperty("purpose")
    private String visitPurpose;

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
