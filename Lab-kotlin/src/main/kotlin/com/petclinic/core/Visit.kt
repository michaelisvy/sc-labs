package com.petclinic.core

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Visit(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var date: LocalDate,
    var purpose: String,

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name="owner_id")
    var owner: Owner?,
    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name="pet_id")
    var pet: Pet?
)
