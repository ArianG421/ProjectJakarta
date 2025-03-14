package org.fungover.jee2025;

import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.entity.Car;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    void toCarDTO_ShouldMapCarToCarDTO() {
        // Arrange
        Car car = new Car();
        car.setCarId(1L);
        car.setCarName("Toyota Corolla");
        car.setDescription("A reliable car");
        car.setManufacturedate(LocalDate.of(2020, 1, 1));
        car.setCarRegistrationNumber("ABC123");
        car.setHorsePower(150);

        // Act
        CarDTO carDTO = CarMapper.toCarDTO(car);

        // Assert
        assertNotNull(carDTO);
        assertEquals(1L, carDTO.getId());
        assertEquals("Toyota Corolla", carDTO.getName());
        assertEquals("A reliable car", carDTO.getDescription());
        assertEquals(LocalDate.of(2020, 1, 1), carDTO.getManufactureDate());
        assertEquals("ABC123", carDTO.getRegistrationNumber());
        assertEquals(150, carDTO.getEnginePower());
    }

    @Test
    void toCarDTO_ShouldReturnNullWhenCarIsNull() {
        // Arrange
        Car car = null;

        // Act
        CarDTO carDTO = CarMapper.toCarDTO(car);

        // Assert
        assertNull(carDTO);
    }

    @Test
    void toCar_ShouldMapCreateCarDTOToCar() {
        // Arrange
        CreateCarDTO createCarDTO = new CreateCarDTO();
        createCarDTO.setName("Toyota Corolla");
        createCarDTO.setDescription("A reliable car");
        createCarDTO.setManufactureDate(LocalDate.of(2020, 1, 1));
        createCarDTO.setRegistrationNumber("ABC123");
        createCarDTO.setEnginePower(150);

        // Act
        Car car = CarMapper.toCar(createCarDTO);

        // Assert
        assertNotNull(car);
        assertEquals("Toyota Corolla", car.getCarName());
        assertEquals("A reliable car", car.getDescription());
        assertEquals(LocalDate.of(2020, 1, 1), car.getManufacturedate());
        assertEquals("ABC123", car.getCarRegistrationNumber());
        assertEquals(150, car.getHorsePower());
    }

    @Test
    void toCar_ShouldReturnNullWhenCreateCarDTOIsNull() {
        // Arrange
        CreateCarDTO createCarDTO = null;

        // Act
        Car car = CarMapper.toCar(createCarDTO);

        // Assert
        assertNull(car);
    }

    @Test
    void updateCarFromDTO_ShouldUpdateCar() {
        // Arrange
        Car car = new Car();
        car.setCarName("Old Name");
        UpdateCarDTO updateCarDTO = new UpdateCarDTO();
        updateCarDTO.setName("New Name");

        // Act
        CarMapper.updateCarFromDTO(updateCarDTO, car);

        // Assert
        assertEquals("New Name", car.getCarName());
    }

    @Test
    void updateCarFromDTO_ShouldNotUpdateCarWhenDTOIsNull() {
        // Arrange
        Car car = new Car();
        car.setCarName("Old Name");
        UpdateCarDTO updateCarDTO = null;

        // Act
        CarMapper.updateCarFromDTO(updateCarDTO, car);

        // Assert
        assertEquals("Old Name", car.getCarName());
    }

    @Test
    void updateCarFromDTO_ShouldNotUpdateCarWhenCarIsNull() {
        // Arrange
        Car car = null;
        UpdateCarDTO updateCarDTO = new UpdateCarDTO();
        updateCarDTO.setName("New Name");

        // Act
        CarMapper.updateCarFromDTO(updateCarDTO, car);

        // Assert
        assertNull(car);
    }
}