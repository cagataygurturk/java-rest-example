package com.cagataygurturk.config;


import com.cagataygurturk.controllers.TransactionsController;
import com.cagataygurturk.controllers.errorhandling.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        // Controllers
        register(TransactionsController.class);

        // Error handling
        register(ServerErrorMapper.class);
        register(NotFoundMapper.class);
        register(BadRequestMapper.class);
        register(JsonMappingErrorHandler.class);
        register(UnprocessableEntityMapper.class);
    }
}