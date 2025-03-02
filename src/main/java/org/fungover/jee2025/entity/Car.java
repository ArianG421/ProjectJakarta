package org.fungover.jee2025.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
//Car entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @NotBlank(message = "Name can not be empty")
    private String carName;

    private String description;

    @PastOrPresent(message = "Date can not be in the future")
    private LocalDate Manufacturedate;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[A-Z0-9]$", message = "Ogiltigt registreringsnummer")
    @Column(unique = true)
    private String carRegistrationNumber;

    @Positive(message = "HorsePower must be positive")
    private int horsePower;

    public Car(){

    }

    public Car(String carName, String description, LocalDate Manufacturedate, String carRegistrationNumber, int horsePower) {
        this.carName = carName;
        this.description = description;
        this.Manufacturedate = Manufacturedate;
        this.carRegistrationNumber = carRegistrationNumber;
        this.horsePower = horsePower;
    }

    public Long getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getManufacturedate() {
        return Manufacturedate;
    }

    public void setManufacturedate(LocalDate Manufacturedate) {
        this.Manufacturedate = Manufacturedate;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String toString(){
        return "Car{" +"Car{" +
                "id=" + carId +
                ", name='" + carName + '\'' +
                ", description='" + description + '\'' +
                ", manufactureDate=" + Manufacturedate +
                ", registrationNumber='" + carRegistrationNumber + '\'' +
                ", enginePower=" + horsePower +
                '}';
    }


}