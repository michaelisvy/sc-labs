package com.petclinic.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;

@Entity
public class Visit {
    @Id @GeneratedValue
    private Long id;

    @OneToOne @Cascade(CascadeType.ALL)
    private Pet pet;

    @OneToOne @Cascade(CascadeType.ALL)
    private Owner owner;

    private String referenceNumber;
    private LocalDate date;
    private String purpose;



    public Visit() {
    }

    public Visit(Long id, String referenceNumber, LocalDate date, String purpose) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.date = date;
        this.purpose = purpose;
    }

    public Long getId() {
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

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Pet getPet() {
        return pet;
    }

    public Owner getOwner() {
        return owner;
    }
}
