package org.fungover.jee2025.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class CarDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate manufactureDate;
    private String registrationNumber;
    private int enginePower;

    // Konstruktor
    public CarDTO(Long id, String name, String description, LocalDate manufactureDate, String registrationNumber, int enginePower) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufactureDate = manufactureDate;
        this.registrationNumber = registrationNumber;
        this.enginePower = enginePower;
    }
}
