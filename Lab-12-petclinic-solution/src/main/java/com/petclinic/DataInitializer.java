package com.petclinic;

import com.petclinic.core.Owner;
import com.petclinic.core.Pet;
import com.petclinic.core.Visit;
import com.petclinic.core.VisitService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Configuration @Profile("dev")
public class DataInitializer {
    private final VisitService visitService;

    public DataInitializer(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostConstruct
    public void initializeSampleData() {
        Owner owner = new Owner(null, "joe", "satriani", 1000);
        var pet1 = new Pet(null, "dog", "luna");
        var visit = new Visit(null, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        visit.setPet(pet1);
        owner.setPets(List.of(pet1));
        visit.setOwner(owner);
        this.visitService.save(visit);
    }
}
