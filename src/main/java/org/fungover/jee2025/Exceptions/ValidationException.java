package org.fungover.jee2025.Exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ValidationException extends WebApplicationException {
    public ValidationException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Validation Error\", \"message\": \"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
