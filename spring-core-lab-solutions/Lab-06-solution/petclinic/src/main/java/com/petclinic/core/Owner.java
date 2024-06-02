package com.petclinic.core;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {
    @Id @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private double accountStatement;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Pet> pets = new ArrayList<>();

    public Owner() {
    }

    public Owner(int id, String firstName, String lastName, double accountStatement) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountStatement = accountStatement;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAccountStatement() {
        return accountStatement;
    }

    public void setAccountStatement(double accountStatement) {
        this.accountStatement = accountStatement;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
