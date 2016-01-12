package com.cagataygurturk.controllers.errorhandling;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestMapper extends AbstractBaseErrorMapper implements ExceptionMapper<BadRequestException> {

    @Override
    protected int getStatusCode() {
        return 400;
    }

    public Response toResponse(BadRequestException ex) {
        return getResponse(ex);
    }
}