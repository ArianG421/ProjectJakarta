package org.fungover.jee2025.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


@Getter // Lombok: Generates getters for all fields
@Setter // Lombok: Generates setters for all fields
public class CreateCarDTO {

    @NotBlank(message = "Namn får inte vara tomt")
    private String name;

    private String description;

    @PastOrPresent(message = "Datum kan inte vara i framtiden")
    private LocalDate manufactureDate;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[A-Z0-9]$", message = "Ogiltigt registreringsnummer")
    private String registrationNumber;

    @Positive(message = "Motorstyrka måste vara positiv")
    private int enginePower;
}
