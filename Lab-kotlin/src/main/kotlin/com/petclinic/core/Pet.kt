package com.petclinic.core

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Pet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  var id: Long?,
    var type: String,
    var name: String,
)
