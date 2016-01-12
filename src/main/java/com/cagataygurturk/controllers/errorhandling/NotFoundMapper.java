package com.cagataygurturk.controllers.errorhandling;

import com.cagataygurturk.services.transaction.TransactionNotFoundException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundMapper extends AbstractBaseErrorMapper implements ExceptionMapper<NotFoundException> {

    @Override
    protected int getStatusCode() {
        return 404;
    }

    public Response toResponse(NotFoundException ex) {
        return getResponse(ex);
    }
}