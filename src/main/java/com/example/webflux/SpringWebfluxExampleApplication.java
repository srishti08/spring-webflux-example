package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webflux.handler.UserHandler;

@SpringBootApplication
public class SpringWebfluxExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxExampleApplication.class, args);
	}
	
	@Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return RouterFunctions.route(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
                        .and(RequestPredicates.POST("/user")),
                userHandler::saveUser);
    }
}
