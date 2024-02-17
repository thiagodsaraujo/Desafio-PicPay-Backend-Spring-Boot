package com.desafiopicpay.controllers;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.UserDTO;
import com.desafiopicpay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user) {
        User newUser = userService.createUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {

        List<User> users = userService.listUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
