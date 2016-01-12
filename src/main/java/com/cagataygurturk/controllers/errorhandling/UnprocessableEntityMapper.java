package com.cagataygurturk.controllers.errorhandling;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnprocessableEntityMapper extends AbstractBaseErrorMapper implements ExceptionMapper<NotAcceptableException> {

    @Override
    protected int getStatusCode() {
        return 422;
    }

    public Response toResponse(NotAcceptableException ex) {
        return getResponse(ex);
    }
}