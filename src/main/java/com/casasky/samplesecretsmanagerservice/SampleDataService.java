package com.casasky.samplesecretsmanagerservice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class SampleDataService {

    @PersistenceContext
    private final EntityManager em;

    public SampleDataService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(SampleData sampleData) {
        em.persist(sampleData);
    }

}
