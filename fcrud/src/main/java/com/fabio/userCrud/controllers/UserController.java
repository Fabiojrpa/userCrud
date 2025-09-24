package com.fabio.userCrud.controllers;

import com.fabio.userCrud.dto.UserDto;
import com.fabio.userCrud.model.User;
import com.fabio.userCrud.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAll(){
        List<User> listUsers = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Integer id){
        Optional user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserDto dto){
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleById(@PathVariable(value = "id") Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateByid(@PathVariable(value = "id") Integer id, @RequestBody UserDto dto){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        var userModel = user.get();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

}
