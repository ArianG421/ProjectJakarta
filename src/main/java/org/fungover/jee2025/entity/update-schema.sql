CREATE TABLE Car
(
    carId                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    carName               VARCHAR(255),
    description           VARCHAR(255),
    Manufacturedate       date,
    carRegistrationNumber VARCHAR(255),
    horsePower            INTEGER                                 NOT NULL,
    CONSTRAINT pk_car PRIMARY KEY (carId)
);

ALTER TABLE Car
    ADD CONSTRAINT uc_car_carregistrationnumber UNIQUE (carRegistrationNumber);