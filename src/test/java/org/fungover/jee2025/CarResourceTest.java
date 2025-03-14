package org.fungover.jee2025;

import static org.junit.jupiter.api.Assertions.*;import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarResourceTest {
    @BeforeAll
    public void setup(){
        RestAssured.baseURI = "http://localhost:8080/api/";
    }

    @Test
    public void testCreateCar() {
        CreateCarDTO newCar = CreateCarDTO.builder()
                .description("Automobile")
                .enginePower(222)
                .name("Volkswagen")
                .registrationNumber("HEY456")
                .build();

        given()
                .contentType(ContentType.JSON)
                    .body(newCar)
                .when()
                    .post("/cars")
                .then()
                    .statusCode(201);
    }

    @Test
    public void testGetCarById() {
        Long carId = 9L;

        given()
                .when()
                .get("/cars/" + carId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Audi A6"))
                .body("description", equalTo("The Sporty Performer"))
                .body("registrationNumber", equalTo("AUD321"))
                .body("enginePower", equalTo(300));
    }

    @Test
    public void testUpdateCar() {
        Long carId = 11L;

        UpdateCarDTO updatedCar = UpdateCarDTO.builder()
                .name("Volvo XC90")
                .description("Upgraded luxury SUV")
                .registrationNumber("XYZ99A")
                .enginePower(300)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(updatedCar)
                .when()
                .put("/cars/" + carId)
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteCar() {
        Long carId = 1L;

        given()
                .when()
                .delete("/cars/" + carId)
                .then()
                .statusCode(204);
    }



}