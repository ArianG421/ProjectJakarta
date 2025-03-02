package org.fungover.jee2025;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.fungover.jee2025.entity.Car;
import org.fungover.jee2025.entity.CarResponse;

@ApplicationScoped
public class Repository {

    @PersistenceContext
    private EntityManager em;

    public Repository() {}

    @Transactional
    public void saveCar(Car car) {
        em.persist(car);
    }

}

