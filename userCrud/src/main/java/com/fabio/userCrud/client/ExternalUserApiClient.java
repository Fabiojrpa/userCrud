package com.fabio.userCrud.client;

import com.fabio.userCrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

// infrastructure/clients/ExternalUserApiClient.java

@Component
public class ExternalUserApiClient {

    @Autowired
    WebClient webClient;

    public List<User> getAll() {
        return webClient
                .get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();

    }

    public User getById(Integer id) {
        return webClient
                .get()
                .uri("Users/{id}", id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public User save(User user) {
        return webClient
                .post()
                .uri("Users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public User update(Integer id, User user) {
        return webClient
                .put()
                .uri("Users/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public void delete(Integer id) {
        webClient
                .delete()
                .uri("Users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }
}
