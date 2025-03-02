package org.fungover.jee2025.DTO;

import jakarta.validation.constraints.NotBlank;
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
public class CreateCarDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;

    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate manufactureDate;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[A-Z0-9]$", message = "Invalid registration number")
    private String registrationNumber;

    @Positive(message = "Horsepower must be positive")
    private int enginePower;
}