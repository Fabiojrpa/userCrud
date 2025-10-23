package com.fabio.userCrud.ports;

import com.fabio.userCrud.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Integer id);
    void delete(User user);
}
