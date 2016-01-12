package com.cagataygurturk.controllers.errorhandling;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

public abstract class AbstractBaseErrorMapper {

    private static final Logger logger = Logger.getLogger(AbstractBaseErrorMapper.class);

    protected int getStatusCode() {
        return 500;
    }

    /**
     * Logs exception
     *
     * @param ex Exception to log
     */
    protected void logError(Exception ex) {
        logger.error(ex);
        ex.printStackTrace();
    }

    public Response getResponse(Exception ex) {
        logError(ex);
        return Response.status(getStatusCode()).
                entity(new Error(ex.getMessage())).
                type("application/json").
                build();
    }
}

