package com.petclinic.core

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Owner(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var firstName: String,
    var lastName: String,
    var accountStatement: BigDecimal,

    @OneToMany(cascade = [CascadeType.PERSIST])
    @JoinColumn(name="owner_id")
    var pets: List<Pet>?
)
