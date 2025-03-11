package org.fungover.jee2025.Exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class CarAlreadyExistsException extends WebApplicationException {
    public CarAlreadyExistsException(String message) {
        super(Response.status(Response.Status.CONFLICT)
                .entity("{\"error\": \"Car Already Exists\", \"message\": \"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
