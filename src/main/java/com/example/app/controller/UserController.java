package com.example.app.controller;

import com.example.app.config.ResponseHandler;
import com.example.app.model.User;
import com.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @NonNull @RequestBody User user) {
        try {
            Optional<User> result = userService.addUser(user);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.generateResponse("Success!", HttpStatus.OK, userService.getAllUsers());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") UUID id) {
        Optional<User> user = userService.getUserById(id);
        if (!user.isEmpty()) {
            return ResponseHandler.generateResponse("Success!", HttpStatus.OK, user);
        } else {
            return ResponseHandler.generateResponse("This user does not exist", HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User userToUpdate) {
        User result = userService.updateUser(id, userToUpdate);
        if (result != null) {
            return ResponseHandler.generateResponse("Success!", HttpStatus.OK, result);
        } else {
            return ResponseHandler.generateResponse("Update failed", HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") UUID id) {
        Integer result = userService.deleteUser(id);
        if (result == 1) {
            return ResponseHandler.generateResponse("Success!", HttpStatus.OK, null);
        } else {
            return ResponseHandler.generateResponse("Delete failed", HttpStatus.MULTI_STATUS, null);
        }
    }
}
