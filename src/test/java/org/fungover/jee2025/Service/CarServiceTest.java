package org.fungover.jee2025.Service;

import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import org.fungover.jee2025.CarMapper;
import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.Exceptions.CarAlreadyExistsException;
import org.fungover.jee2025.Exceptions.ResourceNotFoundException;
import org.fungover.jee2025.Exceptions.ValidationException;
import org.fungover.jee2025.entity.Car;
import org.fungover.jee2025.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ShouldReturnCarDTO() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        car.setCarId(carId);
        car.setCarName("Toyota Corolla");
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // Act
        CarDTO result = carService.findById(carId);

        // Assert
        assertNotNull(result);
        assertEquals(carId, result.getId());
        assertEquals("Toyota Corolla", result.getName());
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    void deleteCar_ShouldDeleteCar() {
        // Arrange
        Long carId = 1L;

        // Act
        carService.deleteCar(carId);

        // Assert
        verify(carRepository, times(1)).deleteById(carId);
    }


    @Test
    void updateCar_ShouldUpdateCar() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        car.setCarId(carId);
        UpdateCarDTO updateCarDTO = new UpdateCarDTO();
        updateCarDTO.setName("Updated Name");
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // Act
        carService.updateCar(carId, updateCarDTO);

        // Assert
        assertEquals("Updated Name", car.getCarName());
        verify(carRepository, times(1)).update(car);
    }


    @Test
    void filterCarsById_ShouldReturnFilteredCars() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        car.setCarId(carId);
        when(carRepository.filterById(carId)).thenReturn(List.of(car));

        // Act
        List<CarDTO> result = carService.filterCarsById(carId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(carId, result.get(0).getId());
        verify(carRepository, times(1)).filterById(carId);
    }

    @Test
    void filterCarsByName_ShouldReturnFilteredCars() {
        // Arrange
        String name = "Toyota";
        Car car = new Car();
        car.setCarName(name);
        when(carRepository.filterByName(name)).thenReturn(List.of(car));

        // Act
        List<CarDTO> result = carService.filterCarsByName(name);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(name, result.get(0).getName());
        verify(carRepository, times(1)).filterByName(name);
    }

    @Test
    void createCar_CreatesNewCar_WhenRegistrationNumberIsUnique() {
        // Arrange
        CreateCarDTO createCarDTO = CreateCarDTO.builder()
                .name("Tesla Model S")
                .description("Electric car")
                .manufactureDate(LocalDate.of(2022, 1, 1))
                .registrationNumber("ABC123") // Unikt registreringsnummer
                .enginePower(500)
                .build();

        // Mocka att inget bil finns med samma registreringsnummer
        when(carRepository.findByRegistrationNumber("ABC123")).thenReturn(Optional.empty());

        // Mocka save-metoden för att returnera en sparad bil
        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> {
            Car car = invocation.getArgument(0);
            car.setCarId(1L); // Simulera att bilen får ett ID när den sparas
            return car;
        });

        // Act
        assertDoesNotThrow(() -> carService.createCar(createCarDTO));

        // Assert
        // Verifiera att findByRegistrationNumber anropades med rätt argument
        verify(carRepository, times(1)).findByRegistrationNumber("ABC123");

        // Verifiera att save-metoden anropades med en bil som matchar CreateCarDTO
        verify(carRepository, times(1)).save(argThat(car ->
                car.getCarName().equals("Tesla Model S") &&
                        car.getDescription().equals("Electric car") &&
                        car.getManufacturedate().equals(LocalDate.of(2022, 1, 1)) &&
                        car.getCarRegistrationNumber().equals("ABC123") &&
                        car.getHorsePower() == 500
        ));

        // Verifiera att bilen får ett ID när den sparas
        verify(carRepository).save(argThat(car -> car.getCarId() != null));
    }

    @Test
    void getCar_ReturnsCarDTO_WhenCarExists() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        car.setCarId(carId);
        car.setCarName("Tesla Model S");
        car.setDescription("Electric car");
        car.setManufacturedate(LocalDate.of(2022, 1, 1));
        car.setCarRegistrationNumber("ABC123");
        car.setHorsePower(500);

        // Mocka att bilen finns i databasen
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // Act
        CarDTO result = carService.getCar(carId);

        // Assert
        assertNotNull(result);
        assertEquals(carId, result.getId());
        assertEquals("Tesla Model S", result.getName());
        assertEquals("Electric car", result.getDescription());
        assertEquals(LocalDate.of(2022, 1, 1), result.getManufactureDate());
        assertEquals("ABC123", result.getRegistrationNumber());
        assertEquals(500, result.getEnginePower());

        // Verifiera att findById anropades med rätt argument
        verify(carRepository, times(1)).findById(carId);
    }








}
