package com.example.app.dao;

import com.example.app.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    Optional<User> insertUser(UUID id, User user);

    default Optional<User> insertUser(User user) {
        return insertUser(UUID.randomUUID(), user);
    }

    List<User> selectAllUsers();

    Optional<User> selectUserById(UUID id);

    int deleteUserById(UUID id);

    User updateUserById(UUID id, User user);
}
