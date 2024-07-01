package com.petclinic.core

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

@Entity
class Owner(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var firstName: String,
    var lastName: String,
    var accountStatement: BigDecimal,

    @OneToMany(cascade = [CascadeType.PERSIST])
    @JoinColumn(name="owner_id")
    var pets: List<Pet>?
)
