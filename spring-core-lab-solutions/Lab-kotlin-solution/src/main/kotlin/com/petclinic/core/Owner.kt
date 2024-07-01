package com.petclinic.core

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

@Entity
data class Owner(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @field:NotBlank(message = "First name is required")
    val firstName: String,
    @field:NotBlank(message = "Last name is required")
    val lastName: String,
    @field:DecimalMin(value = "0.0", inclusive = false, message = "Account statement must be greater than zero")
    val accountStatement: BigDecimal,

    @OneToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    val pets: List<Pet>?
)
