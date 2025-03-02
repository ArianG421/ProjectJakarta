package org.fungover.jee2025.entity;

public class CarResponse {

    private final Long carId;
    private final String carName;
    private final String description;
    private final String manufactureDate;
    private final String carRegistrationNumber;
    private final int horsePower;

    public CarResponse(Long carId, String carName, String description, String manufactureDate, String carRegistrationNumber, int horsePower) {
        this.carId = carId;
        this.carName = carName;
        this.description = description;
        this.manufactureDate = manufactureDate;
        this.carRegistrationNumber = carRegistrationNumber;
        this.horsePower = horsePower;
    }

    public Long getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public String getDescription() {
        return description;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public String toString() {
        return "CarResponse{" +
                "carId=" + carId +
                ", carName='" + carName + '\'' +
                ", description='" + description + '\'' +
                ", manufactureDate='" + manufactureDate + '\'' +
                ", carRegistrationNumber='" + carRegistrationNumber + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
