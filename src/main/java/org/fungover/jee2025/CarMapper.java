package org.fungover.jee2025;

import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper

    public class CarMapper {

        public static CarDTO toCarDTO(Car car) {
            if (car == null) {
                return null;
            }
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getCarId());
            carDTO.setName(car.getCarName());
            carDTO.setDescription(car.getDescription());
            carDTO.setManufactureDate(car.getManufacturedate());
            carDTO.setRegistrationNumber(car.getCarRegistrationNumber());
            carDTO.setEnginePower(car.getHorsePower());
            return carDTO;
        }

        public static Car toCar(CreateCarDTO createCarDTO) {
            if (createCarDTO == null) {
                return null;
            }
            Car car = new Car();
            car.setCarName(createCarDTO.getName());
            car.setDescription(createCarDTO.getDescription());
            car.setManufacturedate(createCarDTO.getManufactureDate());
            car.setCarRegistrationNumber(createCarDTO.getRegistrationNumber());
            car.setHorsePower(createCarDTO.getEnginePower());
            return car;
        }

        public static void updateCarFromDTO(UpdateCarDTO updateCarDTO, Car car) {
            if (updateCarDTO == null || car == null) {
                return;
            }
            if (updateCarDTO.getName() != null) {
                car.setCarName(updateCarDTO.getName());
            }
            if (updateCarDTO.getDescription() != null) {
                car.setDescription(updateCarDTO.getDescription());
            }
            if (updateCarDTO.getManufactureDate() != null) {
                car.setManufacturedate(updateCarDTO.getManufactureDate());
            }
            if (updateCarDTO.getRegistrationNumber() != null) {
                car.setCarRegistrationNumber(updateCarDTO.getRegistrationNumber());
            }
            if (updateCarDTO.getEnginePower() != null) {
                car.setHorsePower(updateCarDTO.getEnginePower());
            }
        }
}