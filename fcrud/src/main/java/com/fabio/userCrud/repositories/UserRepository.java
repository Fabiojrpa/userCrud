package com.fabio.userCrud.repositories;

import com.fabio.userCrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Cada entidade que entra em contato com o bd precisa de um repository

public interface UserRepository extends JpaRepository<User, Integer> {
}
