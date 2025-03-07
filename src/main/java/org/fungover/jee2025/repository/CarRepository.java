package org.fungover.jee2025.repository;

import java.util.List;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.fungover.jee2025.entity.Car;

/**
 * Repository interface for Car entity.
 * Query methods for searching and filtering Car objects are defined below.
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Find
    List<Car> findCarById(Long carId);

    @Find
    Car existsById(Long carId);

}