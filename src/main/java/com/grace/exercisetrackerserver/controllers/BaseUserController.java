package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.services.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class BaseUserController {

    private final BaseUserService baseUserService;

    @Autowired
    public BaseUserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(baseUserService.getAllUsers());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }

    @PostMapping
    public ResponseEntity<BaseUser> createUser(@RequestBody(required = true) BaseUser baseUser) {
        if (baseUser == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            BaseUser createdUser = baseUserService.create(baseUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseUser);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseUser> getUserById(@PathVariable Long id) {
        return baseUserService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        baseUserService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
