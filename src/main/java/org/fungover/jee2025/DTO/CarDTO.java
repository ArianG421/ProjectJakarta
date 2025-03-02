package org.fungover.jee2025.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate manufactureDate;
    private String registrationNumber;
    private int enginePower;
}