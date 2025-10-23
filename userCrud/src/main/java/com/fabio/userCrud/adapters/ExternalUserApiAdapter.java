package com.fabio.userCrud.adapters;

import com.fabio.userCrud.client.ExternalUserApiClient;
import com.fabio.userCrud.exceptions.UserNotFoundException;
import com.fabio.userCrud.model.User;
import com.fabio.userCrud.ports.UserRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository("externalApi")
public class ExternalUserApiAdapter implements UserRepositoryPort {

    private final ExternalUserApiClient client;

    public ExternalUserApiAdapter(ExternalUserApiClient client) {
        this.client = client;
    }

    @Override
    public User save(User user) {
        return client.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(client.getById(id));
        } catch (WebClientResponseException.NotFound e) {
            throw new UserNotFoundException("Usuário não encontrado com id: " + id);
        }
    }

    @Override
    public List<User> findAll() {
        return client.getAll();
    }

    @Override
    public void delete(User user) {
        client.delete(user.getId());
    }
}