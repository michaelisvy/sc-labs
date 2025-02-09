package com.petclinic.core

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface VisitRepository: JpaRepository<Visit, Long> {
}