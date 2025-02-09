package com.petclinic.core

import org.springframework.stereotype.Service

@Service
class VisitService(
        val visitRepository:VisitRepository) {
    fun save(visit: Visit): Visit {
        return visitRepository.save(visit)
    }

    fun findById(id: Long): Visit {
        return visitRepository.findById(id).orElseThrow()
    }
}