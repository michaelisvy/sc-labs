package com.petclinic.invoice;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

@JsonDeserialize(using=InvoiceDeserializer.class)
public class Invoice {
    private LocalDate visitDate;
    private String visitPurpose;
    private String ownerName;
    private String petType;
    private String petName;

    private double amount;

    public Invoice(LocalDate visitDate, String visitPurpose, String ownerName, String petType, String petName, double amount) {
        this.visitDate = visitDate;
        this.visitPurpose = visitPurpose;
        this.ownerName = ownerName;
        this.petType = petType;
        this.petName = petName;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPetType() {
        return petType;
    }

    public String getPetName() {
        return petName;
    }
}
