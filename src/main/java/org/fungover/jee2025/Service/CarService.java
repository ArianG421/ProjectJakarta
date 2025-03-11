package org.fungover.jee2025.Service;


import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fungover.jee2025.CarMapper;
import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.Exceptions.CarAlreadyExistsException;
import org.fungover.jee2025.Exceptions.ResourceNotFoundException;
import org.fungover.jee2025.Exceptions.ValidationException;
import org.fungover.jee2025.entity.Car;
import org.fungover.jee2025.repository.CarRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CarService {
    @Inject
    private CarRepository carRepository;

    public CarDTO findById(Long id) {
        return carRepository.findById(id)
                .map(CarMapper::toCarDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id" + id + "not found!"));
   }

//    public void deleteCar(Long id) {
//        carRepository.deleteById(id);
//    }

    public void deleteCar(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("Invalid car ID: " + id);
        }
        carRepository.deleteById(id);
    }

//    public CarDTO getCar(Long carId) {
//        Optional<Car> car = carRepository.findById(carId);
//        return car.map(CarMapper::toCarDTO).orElse(null);
//    }

    public CarDTO getCar(Long carId) {
        return carRepository.findById(carId)
                .map(CarMapper::toCarDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Car with ID " + carId + " not found"));
    }


    public void createCar(CreateCarDTO createCarDTO) {
        if (createCarDTO.getRegistrationNumber() == null || createCarDTO.getRegistrationNumber().isEmpty()) {
            throw new ValidationException("Registration number cannot be empty!");
        }

        Optional<Car> existingCar = carRepository.findByRegistrationNumber(createCarDTO.getRegistrationNumber());
        if (existingCar.isPresent()) {
            throw new CarAlreadyExistsException("Car with registration number " + createCarDTO.getRegistrationNumber() + " already exists!");
        }
    }



    public void updateCar(Long carId, UpdateCarDTO updateCarDTO) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car with ID " + carId + " not found!"));

        CarMapper.updateCarFromDTO(updateCarDTO, car);
        carRepository.update(car);
    }

    public List<CarDTO> getAllCars(int page, int size) {
        PageRequest pageRequest = PageRequest.ofPage(page).size(size);
        return carRepository.findAll(pageRequest).stream().map(CarMapper::toCarDTO).toList();
    }

    public List<CarDTO> filterCarsById(Long carId) {
        List<Car> cars = carRepository.filterById(carId);
        return cars.stream()
                .map(CarMapper::toCarDTO)
                .collect(Collectors.toList());


    }

    public List<CarDTO> filterCarsByName(String name) {
        List<Car> cars = carRepository.filterByName(name);
        return cars.stream()
                .map(CarMapper::toCarDTO)
                .collect(Collectors.toList());
    }



//    public List<CarDTO> getAllCars(int page, int size) {
//        if (page < 0 || size <= 0) {
//            throw new ValidationException("Invalid pagination values: page " + page + ", size " + size);
//        }
//
//        PageRequest pageRequest = PageRequest.ofPage(page).size(size);
//        return carRepository.findAll(pageRequest).stream()
//                .map(CarMapper::toCarDTO)
//                .toList();
//    }
//
//    // ValidationException
//    public List<CarDTO> filterCarsById(Long carId) {
//        if (carId == null || carId <= 0) {
//            throw new ValidationException("Invalid car ID: " + carId);
//        }
//
//        List<Car> cars = carRepository.filterById(carId);
//        return cars.stream()
//                .map(CarMapper::toCarDTO)
//                .collect(Collectors.toList());
//    }
//
//    // ValidationException
//    public List<CarDTO> filterCarsByName(String name) {
//        if (name == null || name.trim().isEmpty()) {
//            throw new ValidationException("Car name cannot be empty!");
//        }
//
//        List<Car> cars = carRepository.filterByName(name);
//        return cars.stream()
//                .map(CarMapper::toCarDTO)
//                .collect(Collectors.toList());
//    }



}
