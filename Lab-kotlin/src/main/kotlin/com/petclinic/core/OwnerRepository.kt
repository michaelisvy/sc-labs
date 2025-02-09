package com.petclinic.core

import org.springframework.data.jpa.repository.JpaRepository

interface OwnerRepository: JpaRepository<Owner, Long> {
    fun findByFirstName(firstName: String): Owner?
}