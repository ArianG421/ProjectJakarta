package org.fungover.jee2025.repository;

import java.util.List;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import org.fungover.jee2025.entity.Car;

/**
 * Repository interface for Car entity.
 * Query methods for searching and filtering Car objects are defined below.
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    /**
     * Retrieve all cars with the specified registration number.
     *
     * @param carRegistrationNumber the registration number to search for
     * @return a list of Car objects that match the given registration number
     */
    List<Car> findByCarRegistrationNumber(String carRegistrationNumber);

    /**
     * Retrieve all cars with the specified name.
     *
     * @param carName the name of the car
     * @return a list of Car objects that match the given name
     */
    List<Car> findByCarName(String carName);

    // Additional query methods to search and filter Car objects can be added here.
}