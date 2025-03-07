package org.fungover.jee2025.Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
    }

}
