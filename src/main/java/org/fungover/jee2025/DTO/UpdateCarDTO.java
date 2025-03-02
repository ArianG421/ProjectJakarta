package org.fungover.jee2025.DTO;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCarDTO {

    private String name;

    private String description;

    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate manufactureDate;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[A-Z0-9]$", message = "Invalid registration number")
    private String registrationNumber;

    @Positive(message = "Horsepower must be positive")
    private Integer enginePower; // Integer instead of int to allow null values
}