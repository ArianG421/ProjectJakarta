package org.fungover.jee2025.repository;

import java.time.LocalDate;
import java.util.List;

import jakarta.data.page.Page;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.fungover.jee2025.entity.Car;
import org.fungover.jee2025.pagination.Pageable;

public interface CarRepository extends CrudRepository<Car, Long>{

    @Find
    List<Car> findCarById(Long carId);

    @Find
    Car existsById(Long carId);

    List<Car> findByCarNameAndManufacturedate(String carName, LocalDate manufactureDate);



    Page<Car> findAll(Pageable pageable);

}