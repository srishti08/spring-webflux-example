package com.example.webflux.handler;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.webflux.entity.User;
import com.example.webflux.repo.UserRepository;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    
    @Autowired
    private UserRepository repo;
    
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        Mono<User> monoRequest = request
                .body(BodyExtractors.toMono(User.class));
        final String id = UUID.randomUUID().toString();

        final User user = new User("Srishti", "Singh", id);

        Mono<User> monoUser = repo.save(user);

        URI location = UriComponentsBuilder.fromPath("user/" + id).build()
                .toUri();
        return ServerResponse.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoUser, User.class);
    }
    

}
