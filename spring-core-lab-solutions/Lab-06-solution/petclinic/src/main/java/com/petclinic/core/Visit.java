package com.petclinic.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Visit {
    @Id @GeneratedValue
    private int id;
    @Column(unique = true)
    private String referenceNumber;
    private LocalDate date;
    private String purpose;



    public Visit() {
    }

    public Visit(int id, String referenceNumber, LocalDate date, String purpose) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.date = date;
        this.purpose = purpose;
    }

    public int getId() {
        return id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPurpose() {
        return purpose;
    }
}
