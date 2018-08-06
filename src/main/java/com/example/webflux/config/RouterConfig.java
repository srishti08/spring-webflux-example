package com.example.webflux.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webflux.handler.UserHandler;

@Configuration
public class RouterConfig {
    
    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return RouterFunctions.route(POST("/user").and(accept(MediaType.APPLICATION_JSON)),
                        userHandler::saveUser)
                .andRoute(GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), 
                        userHandler::getUser);
    }

}
