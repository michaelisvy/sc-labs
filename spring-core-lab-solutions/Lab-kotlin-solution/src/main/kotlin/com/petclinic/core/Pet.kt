package com.petclinic.core

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Pet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  val id: Int?,
    val type: String,
    val name: String,
)
