package com.petclinic.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest  @Transactional
public class OwnerServiceTest {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        var owner = new Owner(0, "joe", "satriani", 50);
        owner.setPets(List.of(new Pet(0, "dog", "Luna"), new Pet(0, "cat", "Miro")));
        this.ownerService.save(owner);
        this.entityManager.flush();
        this.entityManager.clear();
    }

    @Test
    public void shouldFindOwnerByFirstName() {
        assertThat(this.ownerService.findByFirstName("joe").getLastName()).isEqualTo("satriani");
    }

    @Test
    public void shouldNotFindOwnerByFirstName() {
        assertThatThrownBy(() -> {
            this.ownerService.findByFirstName("huckleberry");
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void shouldNotFindOwnerById() {
        assertThatThrownBy(() -> {
            this.ownerService.findById(12345);
        }).isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    public void shouldFindOwnerWithPets() {
        List<Pet> pets = this.ownerService.findByFirstNameWithPets("joe").getPets();
        assertThat(pets).extracting(Pet::getName).contains("Luna");
    }
}
