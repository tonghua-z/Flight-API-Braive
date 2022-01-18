package com.flight.web.controller;


import com.flight.model.dto.PaginatedResult;
import com.flight.model.entity.User;
import com.flight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(user);

    }

    @GetMapping("/list")
    public ResponseEntity<?> getTotalUsers() {

        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(userService.selectAllUsers()));
    }

}
