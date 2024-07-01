package com.petclinic.core

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

@Entity
data class Visit(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val date: LocalDate,
    @NotBlank(message = "Pet name must not be blank")
    val purpose: String,
    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name="owner_id")
    val owner: Owner,
    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name="pet_id")
    val pet: Pet
)
