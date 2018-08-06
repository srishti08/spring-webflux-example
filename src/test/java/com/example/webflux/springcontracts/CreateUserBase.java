package com.example.webflux.springcontracts;

import java.net.URI;
import java.util.UUID;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webflux.SpringWebfluxExampleApplication;
import com.example.webflux.entity.User;
import com.example.webflux.handler.UserHandler;
import com.example.webflux.repo.UserRepository;

import io.restassured.RestAssured;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { SpringWebfluxExampleApplication.class })
public abstract class CreateUserBase {

    @LocalServerPort
    private int port;
    
    @MockBean
    private UserHandler handler;
    
    @MockBean
    private UserRepository repo;

    @Before
    public void before() {
        RestAssured.port = this.port;
        RestAssured.baseURI = "http://localhost";

        final String id = UUID.randomUUID().toString();
        URI location = URI.create("user/" + id);
        User user = new User("Srishti1", "Singh1", id);
        Mono<User> monoUser = Mono.just(user);
        Mono<ServerResponse> response = ServerResponse.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoUser, User.class);
        Mockito.doReturn(monoUser).when(this.repo).save(Mockito.any());
        Mockito.doReturn(response).when(this.handler).saveUser(Mockito.any());
    }

}
