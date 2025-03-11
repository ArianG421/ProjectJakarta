package org.fungover.jee2025;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
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
import org.fungover.jee2025.Exceptions.ResourceNotFoundException;
import org.fungover.jee2025.Service.CarService;
import org.fungover.jee2025.entity.Car;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Path("/cars")
@Log
public class CarResource {

    private static final Logger logger = Logger.getLogger(CarResource.class.getName());
    @Inject
    private CarService carService;

    @PersistenceContext
    private EntityManager entityManager;



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCar(CreateCarDTO createCarDTO) {
        carService.createCar(createCarDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarById(@PathParam("id") Long id) {
        CarDTO carDTO = carService.getCar(id);
        if (carDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(carDTO).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCar(@PathParam("id") Long id, UpdateCarDTO updateCarDTO) {
        carService.updateCar(id, updateCarDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCar(@PathParam("id") Long id) {
    carService.deleteCar(id);
    return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        // Fetch all Car entities
        List<Car> cars = entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();

        // Convert Car entities to CarDTOs using the mapper
        List<CarDTO> carDTOs = cars.stream()
                .map(CarMapper::toCarDTO)
                .toList();

        return Response.ok().entity(carDTOs).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarDTO> getCars(
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size) {
        return carService.getAllCars(page, size);
    }

}