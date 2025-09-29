package com.fabio.userCrud.service;

import com.fabio.userCrud.dto.UserDto;
import com.fabio.userCrud.exceptions.UserNotFoundException;
import com.fabio.userCrud.model.User;
import com.fabio.userCrud.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> getAll() {
        List<User> listUsers = userRepository.findAll();
        return listUsers;
    }

    public User getById(Integer id) {
        return findById(id);
    }

    public User save(UserDto dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return userRepository.save(user);
    }


    public void userDelete(Integer id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public void updateByid(Integer id, UserDto dto) {
        User user = findById(id);
        BeanUtils.copyProperties(dto, user);
        userRepository.save(user);
    }
}
