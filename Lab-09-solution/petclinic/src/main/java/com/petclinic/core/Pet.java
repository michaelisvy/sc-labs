package com.petclinic.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pet {
    @Id @GeneratedValue
    private int id;
    private String type;
    private String name;

    public Pet() {
    }

    public Pet(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
