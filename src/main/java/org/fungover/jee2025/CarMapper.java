package org.fungover.jee2025;

import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    // Map CreateCarDTO to Car entity
    @Mapping(target = "carId", ignore = true) // Ignore carId because it's auto-generated
    @Mapping(source = "name", target = "carName")
    @Mapping(source = "manufactureDate", target = "Manufacturedate")
    @Mapping(source = "registrationNumber", target = "carRegistrationNumber")
    @Mapping(source = "enginePower", target = "horsePower")
    Car createCarDTOToCar(CreateCarDTO createCarDTO);

    // Map Car entity to CarDTO
    @Mapping(source = "carId", target = "id")
    @Mapping(source = "carName", target = "name")
    @Mapping(source = "Manufacturedate", target = "manufactureDate")
    @Mapping(source = "carRegistrationNumber", target = "registrationNumber")
    @Mapping(source = "horsePower", target = "enginePower")
    CarDTO carToCarDTO(Car car);
}