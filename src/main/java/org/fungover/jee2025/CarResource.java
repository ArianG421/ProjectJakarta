package org.fungover.jee2025;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.fungover.jee2025.DTO.CarDTO;
import org.fungover.jee2025.DTO.CreateCarDTO;
import org.fungover.jee2025.DTO.UpdateCarDTO;
import org.fungover.jee2025.entity.Car;

import java.util.List;
import java.util.logging.Logger;

@Path("/cars")
@Log
public class CarResource {

    private static final Logger logger = Logger.getLogger(CarResource.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    private final CarMapper carMapper = CarMapper.INSTANCE;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCar(CreateCarDTO createCarDTO) {
        // Convert CreateCarDTO to Car entity using the mapper
        Car newCar = carMapper.createCarDTOToCar(createCarDTO);

        // Persist the Car entity
        entityManager.persist(newCar);
        entityManager.flush();

        // Convert Car entity to CarDTO using the mapper
        CarDTO carDTO = carMapper.carToCarDTO(newCar);

        return Response
                .status(Response.Status.CREATED)
                .entity(carDTO)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarById(@PathParam("id") Long id) {
        // Fetch the Car entity by ID
        Car car = entityManager.find(Car.class, id);

        if (car != null) {
            // Convert Car entity to CarDTO using the mapper
            CarDTO carDTO = carMapper.carToCarDTO(car);
            return Response.ok().entity(carDTO).build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Car not found")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCar(@PathParam("id") Long id, UpdateCarDTO updateCarDTO) {
        // Fetch the Car entity by ID
        Car car = entityManager.find(Car.class, id);

        if (car != null) {
            // Update the Car entity with values from UpdateCarDTO
            if (updateCarDTO.getName() != null) {
                car.setCarName(updateCarDTO.getName());
            }
            if (updateCarDTO.getDescription() != null) {
                car.setDescription(updateCarDTO.getDescription());
            }
            if (updateCarDTO.getManufactureDate() != null) {
                car.setManufacturedate(updateCarDTO.getManufactureDate());
            }
            if (updateCarDTO.getRegistrationNumber() != null) {
                car.setCarRegistrationNumber(updateCarDTO.getRegistrationNumber());
            }
            if (updateCarDTO.getEnginePower() != null) {
                car.setHorsePower(updateCarDTO.getEnginePower());
            }

            // Persist the updated Car entity
            entityManager.merge(car);
            entityManager.flush();

            // Convert Car entity to CarDTO using the mapper
            CarDTO carDTO = carMapper.carToCarDTO(car);
            return Response.ok().entity(carDTO).build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Car not found")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCar(@PathParam("id") Long id) {
        // Fetch the Car entity by ID
        Car car = entityManager.find(Car.class, id);

        if (car != null) {
            // Remove the Car entity
            entityManager.remove(car);
            entityManager.flush();
            return Response.noContent().build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Car not found")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        // Fetch all Car entities
        List<Car> cars = entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();

        // Convert Car entities to CarDTOs using the mapper
        List<CarDTO> carDTOs = cars.stream()
                .map(carMapper::carToCarDTO)
                .toList();

        return Response.ok().entity(carDTOs).build();
    }
}