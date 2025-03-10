package org.fungover.jee2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @NotBlank(message = "Name cannot be empty")
    private String carName;

    private String description;

    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate manufactureDate;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[A-Z0-9]$", message = "Invalid registration number")
    @Column(unique = true)
    private String carRegistrationNumber;

    @Positive(message = "HorsePower must be positive")
    private int horsePower;
}