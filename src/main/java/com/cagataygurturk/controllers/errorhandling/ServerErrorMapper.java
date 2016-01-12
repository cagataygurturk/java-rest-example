package com.cagataygurturk.controllers.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerErrorMapper extends AbstractBaseErrorMapper implements ExceptionMapper<Exception> {

    @Override
    protected int getStatusCode() {
        return 500;
    }

    public Response toResponse(Exception ex) {
        return getResponse(ex);
    }
}