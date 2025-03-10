package org.fungover.jee2025.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fungover.jee2025.CarMapper;
import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.entity.Car;
import org.fungover.jee2025.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarService {
    @Inject
    private CarRepository carRepository;

//    public CarDTO findById(Long id) {
//        return carRepository.findById(id)
//                .map(CarMapper::toCarDTO)
//                .orElseThrow(() -> new ResourceNotFoundException("Car with id" + id + "not found!"));
//    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarDTO getCar(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        return car.map(CarMapper::toCarDTO).orElse(null);
    }

    public void createCar(CreateCarDTO createCarDTO) {
        Car car = CarMapper.toCar(createCarDTO);
        carRepository.save(car);
    }


    public void updateCar(Long carId, UpdateCarDTO updateCarDTO) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            CarMapper.updateCarFromDTO(updateCarDTO, car);
            carRepository.update(car);
        }
    }

}

