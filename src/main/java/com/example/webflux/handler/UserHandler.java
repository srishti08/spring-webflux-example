package com.example.webflux.handler;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.webflux.entity.User;
import com.example.webflux.model.UserRequestModel;
import com.example.webflux.repo.UserRepository;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    
    @Autowired
    private UserRepository repo;
    
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        Mono<UserRequestModel> monoRequest = request
                .bodyToMono(UserRequestModel.class);
        final String id = UUID.randomUUID().toString();
        URI location = UriComponentsBuilder.fromPath("user/" + id).build()
                .toUri();
        return monoRequest
                .flatMap(userReq -> ServerResponse.created(location)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(addToDB(id, userReq),
                                        User.class));
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        final String id = request.pathVariable("id");
        Mono<User> user = getFromDB(id);
        return user.flatMap(u -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON).body(user, User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    
    private Mono<User> addToDB(final String id, UserRequestModel userReq) {
        return repo.save(new User(userReq, id));
    }
    
    private Mono<User> getFromDB(final String id) {
        return repo.findById(id);
    }
    

}
