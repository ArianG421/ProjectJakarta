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

//    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
//
//    // Map CreateCarDTO to Car entity
//    @Mapping(target = "carId", ignore = true) // Ignore carId because it's auto-generated
//    @Mapping(source = "name", target = "carName")
//    @Mapping(source = "manufactureDate", target = "Manufacturedate")
//    @Mapping(source = "registrationNumber", target = "carRegistrationNumber")
//    @Mapping(source = "enginePower", target = "horsePower")
//    Car createCarDTOToCar(CreateCarDTO createCarDTO);
//
//    // Map Car entity to CarDTO
//    @Mapping(source = "carId", target = "id")
//    @Mapping(source = "carName", target = "name")
//    @Mapping(source = "Manufacturedate", target = "manufactureDate")
//    @Mapping(source = "carRegistrationNumber", target = "registrationNumber")
//    @Mapping(source = "horsePower", target = "enginePower")
//    CarDTO carToCarDTO(Car car);
}