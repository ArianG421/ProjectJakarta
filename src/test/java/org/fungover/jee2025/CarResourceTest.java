package org.fungover.jee2025;

import static org.junit.jupiter.api.Assertions.*;import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import jakarta.inject.Inject;
import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.Service.CarService;
import org.fungover.jee2025.entity.Car;
import org.jboss.resteasy.core.ResteasyDeploymentImpl;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarResourceTest {

    @Mock
    CarService carService;

    private CarResource carResource;
    private UndertowJaxrsServer server;
    private int port;

    @BeforeEach
    public void setup() throws IOException {
        CarResource carResource = new CarResource(carService);

        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        }

        server = new UndertowJaxrsServer();
        server.start(Undertow.builder().addHttpListener(port, "localhost"));

        ResteasyDeployment deployment = new ResteasyDeploymentImpl();

        deployment.getResources().add(carResource);

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment);
        deploymentInfo.setClassLoader(CarResourceTest.class.getClassLoader());
        deploymentInfo.setDeploymentName("REST API");
        deploymentInfo.setContextPath("/api");

        server.deploy(deploymentInfo);

        // Configure REST-Assured
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/api";

    }
        @AfterEach
        public void tearDown() {
            if (server != null) {
                server.stop();
            }
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
        Long carId = 13L;

        CarDTO carDTO = CarDTO.builder()
                .name("Toyota RAV4")
                .description("The Off-Road Explorer")
                .registrationNumber("TOY987")
                .enginePower(280)
                .build();

        // Mocka carService.getCar()
        when(carService.getCar(carId)).thenReturn(carDTO);


        given()
                .when()
                .get("/cars/" + carId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Toyota RAV4"))
                .body("description", equalTo("The Off-Road Explorer"))
                .body("registrationNumber", equalTo("TOY987"))
                .body("enginePower", equalTo(280));
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