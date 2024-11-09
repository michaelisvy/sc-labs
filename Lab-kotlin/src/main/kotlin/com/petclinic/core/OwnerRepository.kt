package com.petclinic.core

import org.springframework.data.jpa.repository.JpaRepository

interface OwnerRepository: JpaRepository<Owner, Int> {
    fun findByFirstName(firstName: String): Owner?
}