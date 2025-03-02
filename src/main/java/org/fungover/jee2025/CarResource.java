package org.fungover.jee2025;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.fungover.jee2025.entity.Car;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.logging.Logger;

@Path("/cars")
@Log
public class CarResource {


    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getCar() {
        Car newCar = new Car("Sample Car", "A sample description", LocalDate.now(), "ABC12D", 150);
        entityManager.persist(newCar);
        entityManager.flush();

        Long generatedId = newCar.getCarId();
        Car car = entityManager.find(Car.class, generatedId);

        if (car != null) {
            CarResponse carResponse = new CarResponse(
                    car.getCarId(),
                    car.getCarName(),
                    car.getDescription(),
                    car.getManufacturedate().toString(),
                    car.getCarRegistrationNumber(),
                    car.getHorsePower()
            );

            return Response
                    .ok()
                    .entity(carResponse)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Car not found")
                    .build();
        }
    }
}
