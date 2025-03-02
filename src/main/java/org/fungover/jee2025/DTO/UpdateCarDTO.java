package org.fungover.jee2025.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateCarDTO {

    private String name;

    private String description;

    @PastOrPresent(message = "Datum kan inte vara i framtiden")
    private LocalDate manufactureDate;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[A-Z0-9]$", message = "Ogiltigt registreringsnummer")
    private String registrationNumber;

    @Positive(message = "Motorstyrka måste vara positiv")
    private Integer enginePower; // Integer istället för int så att det kan vara null

    // Getters och setters
}