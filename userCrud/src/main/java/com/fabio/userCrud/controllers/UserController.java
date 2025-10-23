package com.fabio.userCrud.controllers;

import com.fabio.userCrud.dto.UserDto;
import com.fabio.userCrud.model.User;
import com.fabio.userCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity getAll() {
        List<User> listUsers = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserDto dto) {
        User user = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleById(@PathVariable(value = "id") Integer id) {
        userService.userDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateByid(@PathVariable(value = "id") Integer id, @RequestBody UserDto dto) {
        userService.updateByid(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }

}
