package org.fungover.jee2025.Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
